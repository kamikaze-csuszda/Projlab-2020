package Item;

import Ice.*;
import Characters.Character;
import Strategy.TentStrategy;
import Strategy.NoIglooStrategy;


public class Tent extends Item {

    /**
     * Alapertelmezett konstruktor.
     */
    public Tent(){
        super();
    }

    /**
     * Beallitja a mezo strategiajat arra, hogy sator talalhato rajta.
     */
    public void use(){
        TentStrategy ts = new TentStrategy();
        Character c = this.getCharacter();
        Ice i = c.getIce();
        i.setIglooStrategy(ts);
    }

    /**
     * Beallitja a mezo strategiajat arra, hogy nincs rajta sator.
     */
    public void discard(){
        NoIglooStrategy nig = new NoIglooStrategy();
        Character c = this.getCharacter();
        Ice i = c.getIce();
        i.setIglooStrategy(nig);
    }
}