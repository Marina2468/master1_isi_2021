package tp_ro;

import ilog.cplex.*;
import ilog.concert.*;

public class Exercice2 {
	public static void calcul (float [][] productivite){
		try {
			IloCplex simplexe = new IloCplex ();
			
			// Variables de décision
			IloIntVar var_decis [][] = new IloIntVar [6][6];
			for (int i = 0;i < 6;i++)
				for (int j = 0;j < 6;j++)
					var_decis[i][j]= simplexe.intVar(0, 1);
			
			//fonction objectif
			IloLinearNumExpr objectif = simplexe.linearNumExpr();
			
			//pondération des variables booleaine
			for (int i = 0;i < 6;i++)
				for (int j = 0;j < 6;j++)
					objectif.addTerm(productivite[i][j], var_decis[i][j]);
				
			// Définir le type d'optimisation de la fonction (max ou min )
			simplexe.addMaximize(objectif);
			
			//les contraintes
			IloLinearNumExpr contrainte_ligne [] = new IloLinearNumExpr[6];
			IloLinearNumExpr contrainte_colonne [] = new IloLinearNumExpr[6];
			
			for (int i = 0;i < 6;i++) {
				contrainte_ligne[i] = simplexe.linearNumExpr();
				contrainte_colonne[i] = simplexe.linearNumExpr();
				for (int j = 0;j < 6;j++) {
					contrainte_ligne[i].addTerm(1, var_decis[i][j]);
					contrainte_colonne[i].addTerm(1, var_decis[j][i]);
				}
				simplexe.addEq(contrainte_ligne[i], 1);
				simplexe.addEq(contrainte_colonne[i], 1);
			}
			
			//résolution
			simplexe.solve();
			
			//résultat
			System.out.println("La valeur de la fonction objectif est: "+ simplexe.getObjValue());
			System.out.println("Les valeurs des variables de décision sont: ") ;
			for (int i = 0;i < 6;i++) {
				for (int j = 0;j < 6;j++) 
					System.out.print(simplexe.getValue(var_decis[i][j])+"\t");
				System.out.println("");
			}		
		} catch (IloException e){
			System.out.print("Exception levée " + e);
		}
	} 
	public static void main(String[] args) {
		float cout[][];
		cout = new float[6][6];
		
		cout[0][0] = 13; cout[0][1] = 24; cout[0][2] = 31;
		cout[0][3] = 19; cout[0][4] = 40; cout[0][5] = 29;
		
		cout[1][0] = 18; cout[1][1] = 25; cout[1][2] = 30;
		cout[1][3] = 15; cout[1][4] = 43; cout[1][5] = 22;
		
		cout[2][0] = 20; cout[2][1] = 20; cout[2][2] = 27;
		cout[2][3] = 25; cout[2][4] = 34; cout[2][5] = 33;
		
		cout[3][0] = 23; cout[3][1] = 26; cout[3][2] = 28;
		cout[3][3] = 18; cout[3][4] = 37; cout[3][5] = 30;
		
		cout[4][0] = 28; cout[4][1] = 33; cout[4][2] = 34;
		cout[4][3] = 17; cout[4][4] = 38; cout[4][5] = 20;
		
		cout[5][0] = 19; cout[5][1] = 36; cout[5][2] = 25;
		cout[5][3] = 27; cout[5][4] = 45; cout[5][5] = 24;
		
		calcul (cout);
	}
}
