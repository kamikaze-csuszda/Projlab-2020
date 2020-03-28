package Strategy;

import Ice.Ice;
import Character.*;

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




public class NoIgloo implements IglooStrategy
{
	//Mivel nincs iglu az adott mezőn, így nő eggyel a hó mennyisége.
	//Elkérjük a mezőn álló karakterek számát, majd végigiterálunk rajtuk, és mindegyiknek csökken a testhője eggyel.
	public void stormEffects(Ice i)
	{
		i.incSnow();
		int charNum = i.getCharNum();
		for(int sz = 0; sz < charNum; sz++)
		{
			Character character = i.getCharacter(sz);
			character.warmthDec();
		}
	}
}
