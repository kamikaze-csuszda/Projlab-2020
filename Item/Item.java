package Item;
import Characters.Character;
import Ice.Ice;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Item.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//




public abstract class Item
{
	private boolean frozen;
	private int durability;
	private Character character;
	private Ice ice;

	/**
	 * Alapertelemezett konstruktor.
	 */
	public Item(){
		durability = -1;
		frozen = true;
	}

	/**
	 * A durability allithatosagat biztosito konstruktor.
	 * @param dur a durability adattag kivant erteke
	 */
	public Item(int dur)
	{
		frozen = true;
		durability = dur;
	}
	/**
	 * Beallitja, melyik tablan van az item
	 * @param i tabla
	 */
	public void setIce(Ice i) {
		ice = i;
	}
	/**
	 * Megnezi, melyik tablan van az item
	 * @return tabla, amin van
	 */
	public Ice getIce() {
		return ice;
	}
	
	
	/**
	 * A fuggveny visszater az Item birtokosaval.  
	 * @return character
	 */
	public Character getCharacter()
	{
		return character;
	}
	
	/**
	 * Beallitja az Item tulajdonosat a parameterben kapott karakterre.
	 * @param c A karakter, ami az Item tulajdonosa lesz.
	 */
	public void setCharacter(Character c)
	{
		this.character = c;
	}
	
	/**
	 * Eltavolitja az Item karakteret azzal, hogy nullara allitja.
	 */
	public void removeCharacter()
	{
		this.character = null;
	}

	/**
	 *  A durability adattag gettere.
	 * @return az adott Item hany hasznalatot bir meg ki
	 */
	public int getDurability(){
		return durability;
	}

	/**
	 *  Csokkenti eggyel a durability adattagot amikor hasznaljak a targyat.
	 */
	public void decreaseDurability(){ ///TODO: itt fog valszeg eltorni is ha lecsokken nullara
		durability--;
	}
	
	/**
	 * Absztrakt fuggveny, amit a leszarmazottak valositanak meg. A kulonbozo eszkozok hasznalatat irja le. 
	 */
	public abstract void use();
	
	/**
	 * Igazzal vagy hamissal ter vissza az Item fagyottsagi allapotat illetoen. Ha igaz, akkor be van fagyva, ha pedig hamis, akkor nincs.
	 * @return frozen Az Item fagyottsaga
	 */
	public boolean getFrozen()
	{
		return frozen;
	}
	
	/**
	 * Atallitja az Item fagyottsagat hamisra, ami azt jelenti, hogy nem lesz befagyva. 
	 */
	public void defrost()
	{
		this.frozen = false; 
	}
	
	/**
	 * A fuggveny az Itemek eldobasaert felel. Hozzaadja a jegtabla Itemjei koze, amin a karakter all es a karaktertol eltavolitja. 
	 */
	public void discard() {
		this.removeCharacter();
	}
}
