package tp3_ro;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String choix = "";
		
		//PL par défaut
		ArrayList<BagItem> test = new ArrayList<BagItem>();
		test.add(new BagItem(8,3));
		test.add(new BagItem(16,7));
		test.add(new BagItem(20,9));
		test.add(new BagItem(12,6));
		test.add(new BagItem(6,3));
		test.add(new BagItem(9,5));
		int max = 17;
		
		Bag bag = new Bag(max, test);
		bag.evaluate();
		System.out.println(bag);
		
		do {
			System.out.println("Entrer un nouveau jeu de données? (oui/non)");
			choix = scan.nextLine();
		} while (!choix.equals("oui") && !choix.equals("non"));
		
		while(choix.equals("oui")) {
			//PL personnalisé
			BagItem temp;
			int nb_objets;
			
			test = new ArrayList<BagItem>();
			bag = null;
			
			System.out.println("Poids maximum = ");
			max = scan.nextInt();
			System.out.println("Nombre d'objets = ");
			nb_objets = scan.nextInt();
			for (int i = 0;i < nb_objets;i++) {
				System.out.println("Objet n°" + (i + 1));
				temp = new BagItem();
				temp.readItem();
				test.add(temp);
			}
			
			bag = new Bag(max, test);
			bag.evaluate();
			System.out.println(bag);
			
			scan.nextLine();
			do {
				System.out.println("Entrer un nouveau jeu de données? (oui/non)");
				choix = scan.nextLine();
			} while (!choix.equals("oui") && !choix.equals("non"));
		} 
	}
}
