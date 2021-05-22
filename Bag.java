package tp3_ro;

import java.util.ArrayList;
import java.util.Arrays;

public class Bag {
	/**
	 * poids maximum du sac a dos C
	 */
	int max_weight;
	
	/**
	 * les objets du sac (définis par leur utilités Bi et leur poids Pi)
	 */
	ArrayList<BagItem> items;
	
	/**
	 * min : borne inferieur du PL
	 * max : solution optimale du PL relaxé
	 */
	int min, max;
	
	int[] solution;
	int[] solution_opt;
	
	public Bag (int max_weight, ArrayList<BagItem> items) {
		this.max_weight = max_weight;
		this.items = items;
		this.solution = new int[items.size() + 1];
		for (int  i = 0;i < solution.length - 1;i++) 
			solution[i] = -1;
		solution[items.size()] = 1;
		this.solution_opt = new int[items.size()];
		sort();
		min = find_initial_solution();
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0;i < items.size();i++)
			result += items.get(i) + "\n";
		return "--------------------\n"
				+ "Bag [max_weight=" + max_weight + "\nmin=" + min + "\nitems=\n" + result + "solution=" + Arrays.toString(solution_opt)
				+ "]"
				+ "\n-----------------------";
	}
	
	public int find_min(int n) {
		int temp = 0;
		for (int i = 1;i < n;i++)
			if (items.get(i).lowerThan(items.get(temp)))
				temp = i;
		return temp;
	}
	
	private void sort () {
		BagItem temp;
		int min;
		for (int i = 0;i < items.size();i++) {
			min = find_min(items.size() - i);
			temp = items.get(min);
			items.remove(min);
			items.add(items.size() - i, temp);
		}
	}
	
	private int find_initial_solution () {
		int usefulness_sum = 0;  //valeur de la fonction objetif
		int weight_sum = 0;  //somme des poids des objets
		int i;
		
		for (i = 0;i < items.size();i++) {  //parcours des objets par ordre decroissant
			weight_sum += items.get(i).poids;  //ajout de l'objet a la solution
			if (weight_sum > this.max_weight)  //verification du poids
				break;
			usefulness_sum += items.get(i).utilite;  //sommation des utilités des objets pris
			solution_opt[i] = 1;
		}
		
		//resultat
		this.min = usefulness_sum;
		usefulness_sum += this.items.get(i).utilite * (max_weight - weight_sum) / this.items.get(i).poids;
		this.max = usefulness_sum;
		
		for(;i < items.size();i++)
			solution_opt[i] = 0;
		
		return this.min;
	}

	public int[] evaluate() {
		int usefulness_sum = 0;
		int weight_sum = 0;
		int i;
		
		for (i = 0;i < items.size();i++)  //traitement des valeurs fixés à 1
			if (solution[i] == 1) {
				usefulness_sum += items.get(i).utilite;
				weight_sum += items.get(i).poids;
			}
		
		if (weight_sum > max_weight) {  //cas de solution non réalisable
			solution[items.size()] = -1;  //code de solution non réalisable = -1
			return solution;
		}
		
		if (weight_sum == max_weight) {  //cas de solution réalisable binaire
			if (usefulness_sum > min)  {//actualisation de la borne inferieure
				max = usefulness_sum;
				min = usefulness_sum;
				for (i = 0;i < solution_opt.length;i++) {
					if (solution[i] == -1)
						solution_opt[i] = 0;
					else
						solution_opt[i] = solution[i];
				}
			}
			solution[items.size()] = 0;  //code de solution réalisable binaire = 0
			return solution;
		}
		
		//weight_sum < max_weight : on peut rajouter d'autres objets
		for (i = 0;i < items.size();i++) {
			if (solution[i] == -1) {
				weight_sum += items.get(i).poids;  //ajout de l'objet a la solution
				if (weight_sum > max_weight)  //verification du poids
					break;
				usefulness_sum += items.get(i).utilite;
			}
		}
		
		if (i < items.size()) {  //cas d'arret avant remplissage de tout les objets
			weight_sum -= items.get(i).poids;
			
			if (weight_sum == max_weight) {  //cas de solution binaire
				if (usefulness_sum > min) { //actualisation de la borne inferieure
					max = usefulness_sum;
					min = usefulness_sum;
					weight_sum = 0;
					//reconstitution de la solution
					for (i = 0;i < solution_opt.length;i++) {
						if (solution[i] == 1) {  //traitement les variables = 1 (objets pris)
							weight_sum += items.get(i).poids;
						}
						solution_opt[i] = solution[i];
					}
					for (i = 0;i < solution_opt.length && weight_sum < max_weight;i++) {  //ajout des objets de l'iteration actuelle
						if (solution[i] == -1) {
							weight_sum += items.get(i).poids;
							solution_opt[i] = 1;
						}
					}
					for (i = 0;i < solution_opt.length;i++) {  //traitement des objets non pris
						if (solution_opt[i] == -1)
							solution_opt[i] = 0;
					}
				}
				solution[items.size()] = 0;  //code de solution réalisable binaire = 0
				return solution;
			}
			else {  //cas de solution réalisable non binaire
				//Z += Bi * (c - somme(Pi)/Pi)
				usefulness_sum += items.get(i).utilite * (max_weight - weight_sum) / items.get(i).poids;
				max = usefulness_sum;
				solution[items.size()] = 1;  //code de solution réalisable non binaire
				if (usefulness_sum > min) {  //separation si la borne superieur > borne inferieur
					separate(i);
				}
				return solution;
			}
		}
		else {  //solution réalisable binaire
			if (usefulness_sum > min) { //actualisation de la borne inferieure
				max = usefulness_sum;
				min = usefulness_sum;
				for (i = 0;i < solution_opt.length;i++) {
					if (solution[i] == -1)
						solution_opt[i] = 0;
					else
						solution_opt[i] = solution[i];
				}
			}
			solution[items.size()] = 0;  //code de solution réalisable binaire = 0
			return solution;
		}
	}

	private void separate(int i) {
		solution[i] = 1;
		evaluate();
		
		solution[i] = 0;
		evaluate();
		
		solution[i] = -1;
	}
}
