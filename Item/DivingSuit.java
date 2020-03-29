package Item;

import Strategy.DivingSuitStrategy;
import Strategy.NoDivingSuit;
import Characters.Character;
import Ice.Ice;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : DivingSuit.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//




public class DivingSuit extends Item
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public DivingSuit()
	{
		super();
		System.out.println("--> DivingSuit()");
		System.out.println("<--");
	}

	/**
	 * A DivingSuit Item hasznalatat allitja be azzal, hogy az Item karakterenek beallitja a strategyjet arra, hogy van nala DivingSuit.
	 */
	@Override
	public void use()
	{
		System.out.println("--> use()");
		DivingSuitStrategy dss = new DivingSuitStrategy();
		Character c = this.getCharacter();
		c.setWaterStrategy(dss);
		System.out.println("<--");
	}

	/**
	 * A DivingSuit Item eldobasa. Ekkor beallitja az Item karakterenek a strategiajat arra, hogy nincs nala DivingSuit.
	 * Utana pedig a Jegtabla Itemjeihez hozzadaja, amin a karakter all es eltavolitja a karaktertol. 
	 */
	@Override
	public void discard()
	{
		System.out.println("--> discard()");
		NoDivingSuit nds = new NoDivingSuit();
		Character c = this.getCharacter();
		c.setWaterStrategy(nds);
		
		Ice i = c.getIce();
		i.addItem(this);
		this.removeCharacter();
		System.out.println("<--");
	}
	
	
}
