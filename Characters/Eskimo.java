package Characters;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Eskimo.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//

import Ice.Ice;

public class Eskimo extends Character
{
	public Eskimo()
	{
		super(5);
	}
	public Eskimo(Ice i)
	{
		super(i, 5);
	}
	public void ability()
	{
		if (getIce().getSnow() > 0) {
			getIce().buildIgloo();			
		}
	}
}
