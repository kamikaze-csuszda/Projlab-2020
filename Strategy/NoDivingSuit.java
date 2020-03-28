package Strategy;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : NoDivingSuit.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//

import Characters.*;
import Ice.*;


public class NoDivingSuit implements WaterStrategy
{
	//Amíg a karaktert nem menti meg valaki, vagy van még szomszédja a lyuknak, amibe beleesett,
	//addig vesszük a következő szomszédot, megnézzük, hogy hány karakter áll rajta,
	//és amíg nem menti meg valaki vagy van még karakter, addig a karakternek meghívjuk a help függvényét,
	//ami megadja, hogy megmentette-e a karaktert, vagy sem.
	//Ha a karakterek és a szomszédok is elfogynak, és senki nem mentette meg, akkor meghal.
	public void fallInWater(Character c)
	{
		Ice hole = c.getIce();
		bool isSaved = false;
		int num = 0;
		while (isSaved == false || num < hole.getNeighbourNum())
		{
			Ice neighbour = hole.getNeighbour(num);
			int charCount = neighbour.getCharNum();
			int currentChar = 0;
			while (isSaved == false || currentChar < charNum)
			{
				Character c2 = neighbour.getCharacter(currentChar);
				isSaved = c2.help(c);
				currentChar++;
			}
			num++;
		}
		if (isSaved == false)
		{
			c.die();
		}
	}
}
