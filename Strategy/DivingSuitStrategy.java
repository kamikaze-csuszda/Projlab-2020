package Strategy;
import Characters.Character;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : DivingSuitStrategy.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//


/**
 * Ez a strategia fut le, ha a vizbe esett karakteren van buvarruha.
 */
public class DivingSuitStrategy implements WaterStrategy
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public DivingSuitStrategy()
	{
	}

	/**
	 * Mivel a karakteren van buvarruha, ezert kimaszik a legelso talalhato szomszed mezore.
	 * @param c : a karakter aki vizbe esett
	 * @throws Exception
	 */
	public void fallInWater(Character c) throws Exception
	{
		
		c.move(0);
		
	}
}
