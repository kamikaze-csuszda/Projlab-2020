package Item;
import Characters.Character;


public class Gun extends Item
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public Gun()
	{
	}

	/**
	 * Az alkatresz kikerul a normal itemek kozul, es bekerul az alkatreszek tarolojaba.
	 */
	@Override
	public void use() {
		Character c = getCharacter();
		getCharacter().addGunpart(this);
		getCharacter().removeItem(this);
		setCharacter(c);
		
	}

	@Override
	public String getItemClass()
	{
		return "Gun";
	}
}
