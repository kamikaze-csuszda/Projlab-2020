package Strategy;
import Characters.Character;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : NoRopeHelp.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//



//Ez a stratégia akkor érvényesül, ha a karakternél nincs kötél.
public class NoRopeHelp implements HelpStrategy
{
	//Mivel nem tud segíteni, ezért hamis értéket ad vissza.
	public boolean help(Character c1, Character c2)
	{
		return false;
	}
}
