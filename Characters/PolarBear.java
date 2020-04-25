package Characters;

import Ice.Ice;
import Startegy.NoBear;
import Strategy.Bear;

public class PolarBear implements Movable {

	private Ice ice;

	/**
	 * A medve atmozog a megadott szomszedos mezore, es a strategiakat is ennek megfeleloen allitja.
	 * A vegen pedig jelzi, hogy lepett, azaz ha van vedtelen karakter a mezon, akkor vege a jateknak.
	 * @param d
	 */
	@Override
	public void move(int d) {
		NoBear nb = new NoBear();
		Ice neighbour = ice.getNeighbour(d);
		ice.setBearStrategy(nb);
		setIce(neighbour);
		Bear b = new Bear();
		neighbour.setBearStrategy(b);
		neighbour.bearHere();
	}
	
	
}
