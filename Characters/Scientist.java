package Characters;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Scientist.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//




public class Scientist extends Character
{
	public int ability(int d) throws Exception
	{
		return getIce().getNeighbour(d).getMaxCharacters();
	}
}
