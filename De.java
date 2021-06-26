package tp4_ro;

import java.util.Random;

public class De {
	
	/**
	 * coté visible actuel du dé (1-2-3-4-5-6)
	 */
	int face;
	/**
	 * générateur aléatoire
	 */
	Random rand;
	
	public De () {
		rand = new Random(System.currentTimeMillis());  //la graine est initialisée au temps (en millisecondes) du systeme
		this.relancer();
	}
	
	@Override
	public String toString() {
		return "" + face;
	}
	
	public int relancer () {
		//générer un nombre aléatoire de -5 jusqua 5
		face = (rand.nextInt() % 6);
		//lever a la valeur absolue
		face = (face < 0)? -face: face;
		//ajouter 1 pour avoir un nombre de 1 jusqua 6
		face++;
		return face;
	}
}
