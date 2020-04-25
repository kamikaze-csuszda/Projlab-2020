package Characters;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;


import Ice.Ice;
import Item.Gun;
import Item.Item;
import Main.Game;
import Strategy.DigStrategy;
import Strategy.HelpStrategy;
import Strategy.NoDivingSuit;
import Strategy.NoRopeHelp;
import Strategy.NoShovelDig;
import Strategy.WaterStrategy;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Character.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//




public abstract class Character implements Movable
{
	private int bodywarmth;
	private int action;
	private Ice ice;
	private ArrayList<Item> equipment;
	private ArrayList<Gun> gunParts;
	private HelpStrategy helpStrategy;
	private DigStrategy digStrategy;
	private WaterStrategy waterStrategy; 
	
	/**
	 * default konstruktora a Characternek
	 */
	public Character()
	{
		bodywarmth = 4;
		action = 4;
		equipment = new ArrayList<Item>();
		gunParts = new ArrayList<Gun>();
		NoRopeHelp nrh = new NoRopeHelp();
		helpStrategy = nrh;
		NoShovelDig nsd = new NoShovelDig();
		digStrategy = nsd;
		NoDivingSuit nds = new NoDivingSuit();
		waterStrategy = nds;
	}
	/**
	 * Character konstruktora, ha mas a testho kezdeti erteke
	 * @param bodywarmth  testho erteke
	 */
	public Character(int bodywarmth)
	{
		this();
		this.bodywarmth = bodywarmth;
	}
	/**
	 * Character kostruktora 
	 * @param i  az Ice erteke ahol a Character kezd 
	 * @param bodywarmth  testho erteke
	 */
	public Character(Ice i, int bodywarmth)
	{
		this(bodywarmth);
		ice = i;
		i.addCharacter(this);
	}
	/**
	 * Getter az aktualis jegtablara
	 * @return  Visszaadja az aktualis jegtablat, amin a Character all 
	 */
	public Ice getIce()
	{
		return ice;
	}
	/**
	 * Getter a action ertekere
	 * @return  aktualis action szamaval ter vissza
	 */
	public int getAction()
	{
		return action;
	}
	/**
	 * visszaallitja alapertekre az action pontok szamat
	 */
	public void resetAction()
	{
		action = 4;
	}
	/**
	 * csokkenti az action pontok szamat 
	 */
	public void decAction()
	{
		action -=1;
	}
	/**
	 * meghivja a karakterhet tartozo aktualis strategy-t
	 */
	public void dig()
	{
		digStrategy.dig(this);
	}
	/**
	 * feltori a jegbe fagyott itemeket, ha a jegtablan nincs ho
	 */
	public void breakIce()
	{
		int snow = ice.getSnow();
		
		if(snow==0) 
			ice.breakIce();
		decAction();
	}
	/**
	 * felesz i a hobol az i-dik itemet, amennyiben az nincs befagyva �s van m�g hely az equipmentben
	 * @param i  az item sorszama a mit fel akarunk venni
	 */
	public void itemPickup(int i)
	{
		try {
		if(equipment.size()<=4 && ice.getItem(i).getFrozen()==false) {
			addItem(ice.getItem(i));
			ice.removeItem(ice.getItem(i));
				} 
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	/**
	 * kozzaadja egy itemet egy karakterhez
	 * @param i  az item  amit hozz� akarunk adni 
	 */
	public void addItem(Item i)
	{
		equipment.add(i);
		i.setCharacter(this);
	}
	/**
	 * osszerakja a fegyver 3 alkatreszet, es megnyerik a jatekor, amennyiben mindenki ugyan azon a mezon van
	 */
	public void assembleGun()
	{
		if (Game.getInstance().getNumOfCharacters() == ice.getCharNum() && gunParts.size() == 3){
			Game.getInstance().winGame();
		}
	}
	/**
	 * eltavolit egy itemet a Character equipment-jebol
	 * @param i  az Item amit eltavolitunk
	 */
	public void removeItem(Item i)
	{
		i.discard();
		equipment.remove(i);

	}
	/**
	 * az item jegre valo ledobasakor meghivodo fuggveny
	 * @param i az item a mit a jegre dobunk 
	 */
	public void itemDiscard(Item i)
	{
		ice.addItem(i);
		removeItem(i);
	}
	/**
	 * Item-et adunk at egyik Characternek a masiktol
	 * @param c  A Character akinek az itemet atadjuk 
	 * @param i  Az Item amit atadunk
	 */
	public void itemGive(Character c, Item i)
	{
		removeItem(i);
		c.addItem(i);
	}
	/**
	 * A Charactert atmozgatjuk egy masik jegteblara, szomszedossag vizsgalat nelkul 
	 * @param i  a Jegtabla, amire moztgatjuk 
	 */
	public void moveTo(Ice i)
	{
		ice.removeCharacter(this);
		i.addCharacter(this);
		setIce(i);
	}
	/**
	 * Setter az akutalis jegtabla beallitasara 
	 * @param i  az jegtabla, amire athelyezzuk a Character-t 
	 */
	public void setIce(Ice i)
	{
		ice = i;
	}
	/**
	 * settet a helpstrategyre 
	 * @param hs  a helpstrategy uj allapota
	 */
	public void setHelpStrategy(HelpStrategy hs)
	{
		helpStrategy = hs;
	}
	/**
	 * settet a DigStrategy 
	 * @param ds  a DigStrategy uj allapota
	 */
	public void setDigStrategy(DigStrategy ds)
	{
		digStrategy = ds;
	}
	/**
	 * settet a WaterStrategy 
	 * @param ws a WaterStrategy uj allapota
	 */
	public void setWaterStrategy(WaterStrategy ws)
	{
		waterStrategy = ws;
	}
	/**
	 * getter a bodywarmth-ra
	 * @return aktualis bodywarmth-ot adja vissza
	 */
	public int getWarmth()
	{
		return bodywarmth;
	}
	/**
	 * getter az Itemre, ami a Characternel van
	 * @param i az item sorszama
	 * @return i-dik Item
	 */
	public Item getItem(int i)
	{
		return equipment.get(i);
	}
	/**
	 * 3 akcioert novel a bodywarmth-on egyet 
	 */
	
	public void warmup()
	{
		for (int i = 0; i < 3; i++) {
			decAction();
		}
		warmthInc();
	}
	/**
	 * csokkenti a bodywarmth erteket eggyel 
	 */
	public void warmthDec()
	{
		bodywarmth -=1;
		if (bodywarmth == 0) {
			die();
		}
	}
	/**
	 * noveli a bodywarmth erteket eggyel 
	 */
	public void warmthInc()
	{
		bodywarmth +=1;
	}
	/**
	 * a Character-t mozgatjuk a fugvennyel
	 * @param d  a mozgas iranyat hatarozza meg 
	 */
	public void move(int d)
	{
		try {
		
			ice.getNeighbour(d).moveHere(this);
			ice.removeCharacter(this);
			setIce(ice.getNeighbour(d));
			ice.bearHere();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Characternek a helpstrategy-jet meghivja, ami eldonti hogy tud-e segiten a masik karakternek
	 * @param c1  Character aki segiteni akar
	 * @return  visszater hogy tud-e segiteni (true), vagy sem (false)
	 */
	public boolean help(Character c1)
	{
		boolean result = helpStrategy.help(c1, this);
		return result;
	}
	/**
	 * a Character viselkedese a waterstrategy alapjan 
	 * @throws Exception
	 */
	public void fallInWater() throws Exception
	{
		waterStrategy.fallInWater(this);
	}
	/**
	 * a Character meghal, es a jeteknak vege
	 */
	public void die()
	{
		Game.getInstance().loseGame();
	}

	/**
	 * Kiszedi a megadott alkatreszt az alkatreszek kozul.
	 * @param g a kiszedni kivant alkatresz
	 */
	public void removeGunpart(Gun g) {
		gunParts.remove(g);
	}

	/**
	 * Hozzaadja a megadott alkatreszt az alkatreszekhez.
	 * @param g
	 */
	public void addGunpart(Gun g) {
		gunParts.add(g);
	}

	/**
	 * A karakternel talalhato targyak gettere.
	 * @return a targyak listaja
	 */
	public ArrayList<Item> getEquipment(){
		return equipment;
	}

	/**
	 * A karakternel talalhato alkatreszek gettere.
	 * @return az alkatreszek listaja
	 */
	public ArrayList<Gun> getGunParts(){
		return gunParts;
	}
}
