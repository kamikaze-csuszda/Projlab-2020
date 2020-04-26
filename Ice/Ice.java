package Ice;
import java.util.ArrayList;

import Characters.Character;
import Item.Item;
import Strategy.BearStrategy;
import Strategy.IglooStrategy;
import Strategy.NoIgloo;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Ice.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//



/**
 * Ez az osztaly felel a jegtablakert es minden hozzajuk kapcsolodo interakcioert. 
 * Tarolja a szomszedos jegtablakat, a rajta levo ho mennyiseget, 
 * a rajta tartozkodo karakterek szamat, illetve hogy van-e rajta iglu. 
 * Hovihar sujthatja, ekkor eggyel no rajta a ho mennyisege.
 * 
 */
public abstract class Ice
{
	private int snow;
	private int maxCharacters;
	private ArrayList<Character> characters;
	private ArrayList<Ice> neighbours;
	private ArrayList<Item> items;
	private IglooStrategy iglooStrategy;
	private BearStrategy bearStrategy;
	private int neighbourNum;
	
	/**
	 * Az osztaly alapertelmezett konstruktora
	 */
	public Ice()
	{
		snow = 0;
		characters = new ArrayList<Character>();
		neighbours = new ArrayList<Ice>();
		items = new ArrayList<Item>();
		NoIgloo nig = new NoIgloo();
		iglooStrategy = nig;
		neighbourNum = 0;
	}
	
	/**
	 * Az osztaly ho parameterrel ellatott konstruktora
	 * @param snow a ho vastagsaga, max 5
	 */
	public Ice(int snow) 
	{
		this();
		if(snow <= 5)
			this.snow = snow;
		else
			this.snow = 5;
	}
	
	/**
	 * Az osztaly ketparameteres konstruktora
	 * @param maxChar max karakterek szama a mezon
	 * @param snow a ho vastagsaga
	 */
	public Ice(int maxChar, int snow) 
	{
		this(snow);
		maxCharacters = maxChar;
	}
	
	/**
	 * A maxCharacters gettere
	 * @return a mezo teherbirasa
	 */
	public int getMaxCharacters()
	{
		return maxCharacters;
	}
	/**
	 * Meghivja a strategia viharfuggvenyet
	 * @throws Exception indexelesi hiba a fuggvenyen belul
	 */
	public void stormEffects() throws Exception
	{
		iglooStrategy.stormEffects(this);
	}
	/**
	 * A ho vastagsaganak gettere
	 * @return a ho vastagsaga
	 */
	public int getSnow()
	{
		return snow;
	}
	/**
	 * A ho vastagsaganak novelese, max 5 a vastagsag
	 */
	public void incSnow()
	{
		if(snow < 5)
			snow++;
	}
	/**
	 * A ho vastagsaganak novelese, 0-nal nem lehet kisebb
	 */
	public void decSnow()
	{
		if(snow > 0)
			snow--;
	}
	/**
	 * Az iglooStrategy settere. Az iglooStrategy a vihar hatasait befolyasolja.
	 * @param is az uj strategia
	 */
	public void setIglooStrategy(IglooStrategy is)
	{
		iglooStrategy = is;
	}
	/**
	 * Hozzaad egy uj szomszedot a mezohoz
	 * @param i az uj szomszed
	 */
	public void addNeighbour(Ice i)
	{
		neighbours.add(i);
	}

	/**
	 * Kiveszi a mezo egy szomszedjat.
	 * @param i az eltavolitando szomszed
	 */
	public void removeNeighbour(Ice i){
		neighbours.remove(i);
	}
	/**
	 * A szomszedos mezok gettere
	 * @param d a szomszed indexe
	 * @return a szomszed
	 * @throws Exception ha nincs ennyi szomszed, hiba
	 */
	public Ice getNeighbour(int d) throws Exception
	{
		if(d > neighbours.size()-1)
			throw new Exception("Nincs mezo abban az iranyban!");
		Ice neigh = neighbours.get(d);
		return neigh;
	}
	/**
	 * A mezon allo karakterek gettere
	 * @param i a karakter indexe
	 * @return az i-edik karakter
	 * @throws Exception
	 */
	public Character getCharacter(int i) throws Exception
	{
		if(i > characters.size()-1)
			throw new Exception("Nincs ennyi karakter a mezon!");
		Character c = characters.get(i);
		return c;
	}
	/**
	 * A mezon allo karakterek szamanak gettere
	 * @return a mezon allo karakterek szama
	 */
	public int getCharNum()
	{
		int num = characters.size();
		return num;
	}
	/**
	 * Hozzaadja a parameterul kapott karaktert a mezon allo karakterekhez
	 * @param c a hozzaadando karakter
	 */
	public void addCharacter(Character c)
	{
		characters.add(c);
	}
	/**
	 * Kiveszi a mezon allo karakterek kozul a parameterul kapott karaktert
	 * @param c a kivevendo karakter
	 */
	public void removeCharacter(Character c)
	{
		characters.remove(c);
	}
	/**
	 * Visszaadja az i-edik eszkozt a mezon, hibat dob ha tulindexelik
	 * @param i az eszkoz indexe
	 * @return az i-edik eszkoz
	 * @throws Exception hiba, ha tulindexelik
	 */
	public Item getItem(int i) throws Exception
	{
		if(i > characters.size()-1)
			throw new Exception("Nincs ennyi eszkoz a mezon!");
		Item it = items.get(i);
		return it;
	}
	/**
	 * Visszaadja a szomszedok szamat
	 * @return a szomszedok szama
	 */
	public int getNeighbourNum()
	{
		return neighbourNum;
	}
	/**
	 * Kitori a mezon talalhato osszes targyat a jegbol
	 */
	public void breakIce()
	{
		for (Item item : items) 
		{
			item.defrost();
		}
	}
	/**
	 * Hozzaad egy targyat a mezon levo targyakhoz
	 * @param i a hozzaadando targy
	 */
	public void addItem(Item i)
	{
		items.add(i);
	}
	/**
	 * Kivesz egy targyat a mezon levo targyak kozul
	 * @param i a kivevendo targy
	 */
	public void removeItem(Item i)
	{
		items.remove(i);

	}
	/**
	 * Egy karakter erre a mezore mozgatasa. A leszarmazottakban mas mas hatasa van
	 * @param c az erkezo karakter
	 */
	public abstract void moveHere(Character c);
	/**
	 * A buildIgloo() alapertelmezetten semmit nem csinal, csak a leszarmazottakban.
	 */
	public void buildIgloo()
	{
	}
	/**
	 * Az iglooStrategy-t visszaallitja az alaphelyzetbe.
	 */
	public void destroyIgloo()
	{
		NoIgloo nig = new NoIgloo();
		iglooStrategy = nig;
	}

	/**
	 *
	 */
	public void bearHere() {
		bearStrategy.bearHere(this);
	}

	/**
	 * A kor vegevel meghivja a strategia turnEnd-jet, ami megszunteti a satrat, ha a sator talalhato a mezon.
	 */
	public void turnEnd() {
		iglooStrategy.turnEnd(this);
	}

	/**
	 *
	 */
	public void stepOn(){
		iglooStrategy.stepOn(this);
	}

	/**
	 * Ajegen talalhato itemek gettere.
	 * @return a jegen talahato itemek listája
	 */
	public ArrayList<Item> getItemArray(){
		return items;
	}
	
	public void setBearStrategy(BearStrategy bs) {
		bearStrategy = bs;
		
	}
}
