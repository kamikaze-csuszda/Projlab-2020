package Strategy;

import Ice.Ice;
import Main.Game;
import Characters.*;
import Characters.Character;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : NoIgloo.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//



/**
 * Az Ice-hoz tartozo strategia, akkor fut le, ha nincs rajta iglu.
 */
public class NoIgloo implements IglooStrategy
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public NoIgloo()
	{
	}
	/**
	 * Mivel nincs iglu az adott mezon, igy no eggyel a ho mennyisege.
	 * Elkerjuk a mezon allo karakterek szamat, majd vegigiteralunk rajtuk, és mindegyiknek csokken a testhoje eggyel.
	 * @param i : a jegtabla, melyre erkezik a vihar
	 */
	public void stormEffects(Ice i) throws Exception
	{
		i.incSnow();
		int charNum = i.getCharNum();
		for(int sz = 0; sz < charNum; sz++)
		{
			Character character = i.getCharacter(sz);
			character.warmthDec();
		}
	}

	/**
	 * Amikor a medve ralep a mezore ezzel a strategiaval, megnezi, hogy van-e karakter a mezon, ha van, akkor jatek vege.
	 * @param i a mezo amire ralep
	 */
	@Override
	public void stepOn(Ice i) {
		int num = i.getCharNum();
		if (num > 0)
			Game.getInstance().loseGame(); 
	}

	/**
	 * Szandekosan nem csinal semmit.
	 * @param i
	 */
	@Override
	public void turnEnd(Ice i) {
	}
}
