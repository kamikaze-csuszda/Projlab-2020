package Strategy;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Igloo.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//

import Ice.*;

/**
 * A jegtabla strategiaja, akkor fut le, ha van az adott jegtablan iglu.
 */
public class Igloo implements IglooStrategy
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public Igloo()
	{
	}
	/**
	 * Ha van iglu az adott mezon, akkor a vihar azt lerombolja.
	 * De az iglu megvedi a rajta allokat a testhocsokkenestol, es uj horeteg sem rakodik le.
	 * @param i : a jegtabla, melyre vihar erkezik
	 * @throws Exception
	 */
	public void stormEffects(Ice i) throws Exception
	{
		
		i.destroyIgloo();
	
	}

	/**
	 * Direkt nem csinal semmit.
	 * @param i
	 */
	@Override
	public void stepOn(Ice i) {
	}

	/**
	 * Direkt nem csinal semmit
	 * @param i
	 */
	@Override
	public void turnEnd(Ice i) {
	}
}
