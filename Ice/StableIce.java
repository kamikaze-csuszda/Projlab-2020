package Ice;

import Characters.Character;
import Strategy.Igloo;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : StableIce.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//




public class StableIce extends Ice
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public StableIce()
	{
		super();
	}
	/**
	 * Megepíti az iglut és beallitja az iglustrategiat
	 */
	public void buildIgloo()
	{
		Igloo ig = new Igloo();
		setIglooStrategy(ig);
		decSnow();
	}
	/**
	 * Ide mozgatja a paramterben megadott karaktert.
	 * @param c
	 */
	@Override
	public void moveHere(Character c) 
	{
		addCharacter(c);
	}
	
}
