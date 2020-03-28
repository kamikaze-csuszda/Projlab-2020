package Strategy;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : ShovelDig.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//

import Ice.*;
import Character.*;


public class ShovelDig implements DigStrategy
{
	//A karakteren keresztül megkapjuk a mezőt,
	//amin áll, és ezen csökkentjük a hó mennyiségét.
	//Mivel van nála ásó, ezért két egységgel.
	public void dig(Character c)
	{
		Ice ice = c.getIce();
		ice.decSnow();
		ice.decSnow();
		c.decAction();
	}
}
