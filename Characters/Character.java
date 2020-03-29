package Characters;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;

import com.sun.jdi.Type;

import Ice.Ice;
import Item.Gun;
import Item.Item;
import Main.Game;
import Strategy.DigStrategy;
import Strategy.HelpStrategy;
import Strategy.NoDivingSuit;
import Strategy.NoRopeHelp;
import Strategy.NoShovelDig;
import Strategy.WaterStrategy;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Character.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//




public abstract class Character implements Movable
{
	private int bodywarmth;
	private int action;
	private Ice ice;
	private ArrayList<Item> equipment;
	private HelpStrategy helpStrategy;
	private DigStrategy digStrategy;
	private WaterStrategy waterStrategy; 
	
	public Character()
	{
		bodywarmth = 4;
		action = 4;
		equipment = new ArrayList<Item>();
		NoRopeHelp nrh = new NoRopeHelp();
		helpStrategy = nrh;
		NoShovelDig nsd = new NoShovelDig();
		digStrategy = nsd;
		NoDivingSuit nds = new NoDivingSuit();
		waterStrategy = nds;
	}
	public Character(int bodywarmth)
	{
		this();
		this.bodywarmth = bodywarmth;
	}
	public Character(Ice i, int bodywarmth)
	{
		this(bodywarmth);
		ice = i;
		i.addCharacter(this);
	}
	
	public Ice getIce()
	{
		return ice;
	}
	
	public int getAction()
	{
		return action;
	}
	
	public void resetAction()
	{
		action = 4;
	}
	
	public void decAction()
	{
		action -=1;
	}
	
	public void dig()
	{
		digStrategy.dig(this);
	}
	
	public void breakIce()
	{
		int snow = ice.getSnow();
		
		if(snow==0) 
			ice.breakIce();
		decAction();
	}
	
	public void itemPickup(int i)
	{
		try {
		if(equipment.size()<=4 && ice.getItem(i).getFrozen()==false) {
			addItem(ice.getItem(i));
			ice.removeItem(ice.getItem(i));
				} 
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	public void addItem(Item i)
	{
		equipment.add(i);
	}
	
	public void assembleGun(Gun g1, Gun g2, Gun g3)
	{
			
	}
	
	public void removeItem(Item i)
	{
		equipment.remove(i);
		i.discard();
	}
	
	public void itemDiscard(Item i)
	{
		removeItem(i);
		i.removeCharacter();
		ice.addItem(i);
	}
	
	public void itemGive(Character c, Item i)
	{
		removeItem(i);
		c.addItem(i);
	}
	
	public void moveTo(Ice i)
	{
		ice.removeCharacter(this);
		i.addCharacter(this);
		setIce(i);
	}
	
	public void setIce(Ice i)
	{
		ice = i;
	}
	
	public void setHelpStrategy(HelpStrategy hs)
	{
		helpStrategy = hs;
	}
	
	public void setDigStrategy(DigStrategy ds)
	{
		digStrategy = ds;
	}
	
	public void setWaterStrategy(WaterStrategy ws)
	{
		waterStrategy = ws;
	}
	
	public int getWarmth()
	{
		return bodywarmth;
	}
	
	public void warmup()
	{
		for (int i = 0; i < 3; i++) {
			decAction();
		}
		warmthInc();
	}
	
	public void warmthDec()
	{
		bodywarmth -=1;
		if (bodywarmth == 0) {
			die();
		}
				
	}
	
	public void warmthInc()
	{
		bodywarmth +=1;
	}
	
	public void move(int d)
	{
		
		try {
			System.out.println("	-->ice1.getNeighbour(" + d + ")");
			System.out.println("	<--ice2");
			System.out.println("	-->ice2.moveHere(c)");
			ice.getNeighbour(d).moveHere(this);
			System.out.println("	<--");
			System.out.println("	-->ice1.removeCharacter(c)");
			System.out.println("	<--");
			ice.removeCharacter(this);
			System.out.println("	-->c.setIce(ice2)");
			System.out.println("	<--");
			setIce(ice.getNeighbour(d));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean help(Character c1)
	{
		return	helpStrategy.help(c1, this);
	}
	
	public void fallInWater() throws Exception
	{
		waterStrategy.fallInWater(this);
	}
	
	public void die()
	{
		
	}
}
