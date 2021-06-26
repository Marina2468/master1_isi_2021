package tp4_ro_exo2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		int taille_matrice;
		int puissance;
		
		System.out.println("Donnez la taille de la matrice : ");
		taille_matrice = lecteur.nextInt();
		
		float[][] matrice = new float[taille_matrice][taille_matrice];
		
		for (int i = 0;i < matrice.length;i++)
			for (int j = 0;j < matrice.length;j++) {
				System.out.print("matrice[" + i + "][" + j + "] = ");
				matrice[i][j] = lecteur.nextFloat();
			}
		
		System.out.println((est_matrice_stochastique(matrice)?
				"La matrice est stochastique"
				:"La matrice n'est pas stochastique"));
		
		if (!est_matrice_stochastique(matrice)) {
			lecteur.close();
			return;
		}
		System.out.println("\n****************************\n");
		
		System.out.print("Lever la matrice à quelle puissance? :");
		puissance = lecteur.nextInt();
		
		float [][] test = puissance_matrice(matrice, puissance);
		
		System.out.println("Matrice résultante : ");
		
		for (int i = 0;i < test.length;i++) {
			for (int j = 0;j < test[0].length;j++)
				System.out.print(test[i][j] + "  ");
			System.out.println("");
		}
		
		lecteur.close();
	}
	
	/**
	 * question 1
	 * @return true : vecteur stochastique / false vecteur non stochastique
	 */
	public static Boolean est_vecteur_stochastique (float[] vecteur) {
		float somme_vecteur = 0;
		
		for (int i = 0;i < vecteur.length;i++) {
			//verification des valeurs du vecteur (0 <= valeur <= 1)
			if (vecteur[i] < 0 || vecteur[i] > 1)
				return false;
			
			//sommation des valeurs du vecteurs
			somme_vecteur += vecteur[i];
		}
		
		//verification de la somme des valeurs (somme = 1)
		if (somme_vecteur != 1.0)
			return false;
		return true;
	}
	
	/**
	 * question 2 
	 * @param matrice matrice carré 
	 * @return true : matrice stochastique / false : matrice non stochastique
	 */
	public static Boolean est_matrice_stochastique (float[][] matrice) {
		//matrice rectangulaire : non stochastique
		if (matrice.length != matrice[0].length)
			return false;
		
		// verification de la somme de chaque ligne de la matrice
		for (int i = 0;i < matrice.length;i++)
			if (!est_vecteur_stochastique(matrice[i]))
				return false;
		return true;
	}
	
	/**
	 * question 3
	 * @param matrice matrice varré à lever en puissance
	 * @param puissance nombre de fois ou la matrice doit etre multiplié
	 * @return la matrice resultante de matrice^puissance
	 */
	public static float[][] puissance_matrice (float[][] matrice, int puissance) {
		if (puissance < 1)
			return null;
		
		//initialisation
		float[][] resultat = new float [matrice.length][matrice[0].length];
		
		for (int i = 0;i < matrice.length;i++)
			for (int j = 0;j < matrice[0].length;j++)
				resultat[i][j] = matrice[i][j];
		
		//repeter le produit matricielle "resultat" x "matrice" autant de fois que "puissance"
		for (int compt = 2;compt <= puissance;compt++) {
			for (int i = 0;i < matrice.length;i++)
				for (int j = 0;j < matrice[0].length;j++)
					resultat[i][j] = produit_scalaire(resultat[i], matrice[j]);
		}
		
		return resultat;
	}
	
	public static float produit_scalaire (float[] v1, float[] v2) {
		float produit = 0;
		
		if (v1.length != v2.length) {
			System.err.println("Tailles de vecteurs différentes");
			System.exit(1);
		}
		
		for (int i = 0;i < v1.length;i++) 
			produit += v1[i] * v2[i];
		
		return produit;
	}
}
