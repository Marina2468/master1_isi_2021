package tp4_ro;

import java.util.Random;

public class Piece {
	
	/**
	 * coté visible actuel de la piece (face=true/pile=false)
	 */
	Boolean cote;
	/**
	 * générateur aléatoire
	 */
	Random rand;
	
	public Piece () {
		rand = new Random(System.currentTimeMillis());
		this.relancer();
	}
	
	@Override
	public String toString () {
		return (cote)? "face" : "pile";
	}
	
	public Boolean relancer () {
		cote = rand.nextBoolean();
		return cote;
	}
}
