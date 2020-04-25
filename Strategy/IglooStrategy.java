package Strategy;

import Ice.Ice;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : IglooStrategy.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//


/**
 * A jegtabla iglu strategiajanak interfesze.
 */
public interface IglooStrategy
{
	/**
	 * Meghatarozza, hogy mi tortenik az adott jegtablan a vihar soran.
	 * @param i : a jegtabla melyre vihar erkezik
	 * @throws Exception
	 */
	public void stormEffects(Ice i) throws Exception;
	
	
}
