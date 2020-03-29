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
		System.out.println("--> Eskimo()");
		System.out.println("<--");
	}
	public Eskimo(Ice i)
	{
		super(i, 5);
		System.out.println("--> Eskimo(i)");
		System.out.println("<--");
	}
	public void ability()
	{
		System.out.println("--> ability()");
		Ice ice = getIce();
		if (ice.getSnow() > 0) {
			ice.buildIgloo();			
		}
		System.out.println("<--");
	}
}
