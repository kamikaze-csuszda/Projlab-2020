package Ice;
import java.util.ArrayList;

import Characters.Character;
import Item.Item;
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
	public int neighbourNum;
	
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
	 */
	public void stormEffects()
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
	 * A szomszedos mezok gettere
	 * @param d a szomszed indexe
	 * @return a szomszed
	 * @throws Exception ha nincs ennyi szomszed, hiba
	 */
	public Ice getNeighbour(int d) throws Exception
	{
		if(d > neighbours.size()-1)
			throw new Exception("Nincs mezo abban az iranyban!");
		return neighbours.get(d);
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
		return characters.get(i);
	}
	/**
	 * 
	 * @return
	 */
	public int getCharNum()
	{
		return characters.size();
	}
	/**
	 * 
	 * @param c
	 */
	public void addCharacter(Character c)
	{
		characters.add(c);
	}
	/**
	 * 
	 * @param c
	 */
	public void removeCharacter(Character c)
	{
		characters.remove(c);
	}
	/**
	 * 
	 * @param i
	 * @return
	 * @throws Exception 
	 */
	public Item getItem(int i) throws Exception
	{
		if(i > characters.size()-1)
			throw new Exception("Nincs ennyi eszkoz a mezon!");
		return items.get(i);
	}
	/**
	 * 
	 */
	public void breakIce()
	{
		for (Item item : items) 
		{
			item.defrost();
		}
	}
	/**
	 * 
	 * @param i
	 */
	public void addItem(Item i)
	{
		items.add(i);
	}
	/**
	 * 
	 * @param i
	 */
	public void removeItem(Item i)
	{
		items.remove(i);
	}
	/**
	 * 
	 * @param c
	 * @throws Exception
	 */
	public abstract void moveHere(Character c) throws Exception;
	/**
	 * 
	 */
	public void buildIgloo()
	{
	}
	/**
	 * 
	 */
	public void destroyIgloo()
	{
		NoIgloo nig = new NoIgloo();
		iglooStrategy = nig;
	}
}
