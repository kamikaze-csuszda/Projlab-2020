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
	
	
	public void buildIgloo()
	{
		Igloo ig = new Igloo();
		setIglooStrategy(ig);
	}

	@Override
	public void moveHere(Character c) 
	{
		addCharacter(c);
	}
	
}
