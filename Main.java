package tp4_ro;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		simuler_lancer_de();
		
		System.out.println("\n*********************\n");
		
		lancer_jusquau_6();
		
		System.out.println("\n*********************\n");
		
		simuler_lancer_piece();
	}
	
	/**
	 * question 2
	 */
	public static void simuler_lancer_de() {
		Scanner lecteur = new Scanner(System.in);
		De de = new De();
		int nb_lance;
		int experience[];
		
		//lecture du nombre de lancé
		System.out.println("Combien de fois lancer le dé? ");
		nb_lance = lecteur.nextInt();
		experience = new int[nb_lance];
		
		//relancer le dé et sauvegarder les resultats de chaque lancé
		for (int i = 0;i < nb_lance;i++)
			experience[i] = de.relancer();		
		
		//affichage des resultats
		System.out.println("Résultats des lancés : ");
		
		for (int i = 1;i <= nb_lance;i++) {
			System.out.print(experience[i - 1] + "  ");
			if (i % 6 == 0)
				System.out.print("\n");
		}
		
		//calcul du nombre de 6 obtenus
		System.out.println("\nNombre de 6 obtenus = " + calculer_nombre_6(experience));
		
		lecteur.close();
	}
	
	/**
	 * question 3
	 * @param record ensemble de lancés aléatoire
	 * @return nombre de 6 obtenus dans les lancés	 
	 */
	public static int calculer_nombre_6(int [] record) {
		int nb6 = 0;
		for (int i = 0;i < record.length;i++) 
			if (record[i] == 6)
				nb6++;
		return nb6;
	}
	
	/**
	 * question 4
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void lancer_jusquau_6() {
		System.out.println("Lancé de dé jusqu'à obtention d'un 6 : ");
		
		De de = new De();
		ArrayList experience = new ArrayList();
		experience.add(de.face);  //premier lancé
		
		//relancer jusqu'à obtention du 6
		while (de.face != 6) {
			de.relancer();
			experience.add(de.face);
		}
		
		//affichage des resultats
		for (int i = 1;i <= experience.size();i++) {
			System.out.print(experience.get(i - 1) + "  ");
			if (i % 6 == 0)
				System.out.print("\n");
		}
		
		System.out.print("\nNombre d'essais = " + (experience.size() - 1));
	}
	
	/**
	 * question 5
	 */
	public static void simuler_lancer_piece() {
		Scanner lecteur = new Scanner(System.in);
		Piece piece = new Piece();
		int nb_lance;
		String experience[];
		
		//lecture du nombre de lancé
		System.out.println("Combien de fois lancer la piece? ");
		nb_lance = lecteur.nextInt();
		experience = new String[nb_lance];
		
		//relancer la piece et sauvegarder les resultats
		for (int i = 0;i < nb_lance;i++) {
			experience[i] = piece.toString();
			piece.relancer();
		}	
		
		//affichage des resultats
		System.out.println("Résultats des lancés : ");
		
		for (int i = 1;i <= nb_lance;i++) {
			System.out.print(experience[i - 1] + "  ");
			if (i % 6 == 0)
				System.out.print("\n");
		}
		
		lecteur.close();
	}
}
