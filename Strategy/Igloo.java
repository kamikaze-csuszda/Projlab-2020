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

//Ez a stratégia fut le, ha az adott mezőn úgy megy végbe vihar, hogy van rajta iglu.
public class Igloo implements IglooStrategy
{
	//Ha van iglu az adott mezőn, akkor a vihar azt lerombolja.
	//De az iglu megvédi a rajta állókat a testhőcsökkenéstől, és új hóréteg sem rakódik le.
	public void stormEffects(Ice i) throws Exception
	{
		i.destroyIgloo();
	}
}
