package Item;

import Characters.Character;
import Strategy.NoShovelDig;
import Strategy.ShovelDig;

public class BreakableShovel extends Shovel {
    /**
     *  Alapertelmezett konstruktor.
     */
    public BreakableShovel(){
            super(3);
            }
    
    public void use()
	{
		ShovelDig sd = new ShovelDig(this);
		Character c = this.getCharacter();
		c.setDigStrategy(sd);
		decreaseDurability();
	}

	/**
	 * A BreakableShovel Item eldobasat irja le. Azzal, hogy atallitja a karakter asasi startegiajat arra, hogy nincsen nala aso. 
	 * Utana pedig a Jegtabla Itemjeihez hozzadaja, amin a karakter all es eltavolitja a karaktertol.
	 */
	@Override
	public void discard()
	{
		NoShovelDig nsd = new NoShovelDig();
		Character c = this.getCharacter();
		c.setDigStrategy(nsd);
	}
}