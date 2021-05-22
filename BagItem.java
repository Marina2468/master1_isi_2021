package tp3_ro;

import java.util.Scanner;

public class BagItem {
	public int utilite;
	public int poids;
	
	public BagItem() {
		this.utilite = -1;
		this.poids = -1;
	}
	
	public BagItem(int utilite, int poids) {
		this.utilite = utilite;
		this.poids = poids;
	}
	
	public boolean equals(BagItem obj) {
		return (this.utilite / this.poids == obj.utilite / obj.poids);
	}
	
	public boolean greaterThan(BagItem obj) {
		return (this.utilite / this.poids > obj.utilite / obj.poids);
	}
	
	public boolean lowerThan(BagItem obj) {
		return (this.utilite / this.poids < obj.utilite / obj.poids);
	}

	@Override
	public String toString() {
		return "BagItem [utilite=" + utilite + ", poids=" + poids + ", rapport=" + utilite/poids+ "]";
	}
	
	public void readItem () {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Utilité = ");
		this.utilite = scan.nextInt();
		System.out.println("Poids = ");
		this.poids = scan.nextInt();
	}
}
