package Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import Characters.PolarBear;
import Ice.HoleIce;
import Ice.Ice;
import Ice.StableIce;
import Ice.UnstableIce;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Game.java
//  @ Date : 2020-03-28
//  @ Author : 
//
//




public class Game implements Serializable
{
	private static Game game = new Game();
	private Game() {
		
	}
	
	public static Game getInstance() {
		return game;
	}
	private int numOfCharacters = 0;
	private ArrayList<Ice> mapPieces = new ArrayList<Ice>();
	private Map<String, Object> objects = new HashMap<String, Object>();
	private ArrayList<PolarBear> maci = new ArrayList<PolarBear>();
	public ArrayList<PolarBear> getMaci(){
		return maci;
	}
	public ArrayList<Ice> getMapPieces(){
		return mapPieces;
	}
	public Map<String, Object> getObjects()
	{
		return objects;
	}
	
	/**
	 * Vegigmegy az osszes objects beli kulcson, es ha talal egy olyat, aminel 
	 * megegyezik a hozza tartozo objektum a parameterkent kapottal
	 * returnoli a kulcsat. Ha nem talal ilyet, hibat dob;
	 * @param _object A keresett kulcs objektuma
	 * @return a keresett kulcs erteke
	 * @throws Exception ha nincs a taroloban a keresett objektum, sima Exception-t dob
	 */
	public String findName(Object _object) throws Exception 
	{
		String ret = "Not found";
		for(String key: objects.keySet()) {
			if(objects.get(key) == _object)
				ret = key;
		}
		if (ret.equals("Not found"))
			throw new Exception("$Nincs ilyen objektum a jatekban!");
		return ret;
			
	}
	/**
	 * Hozzaad egy uj elempart a tarolohoz
	 * @param o hozzaadando objektum
	 * @param key az objektum neve
	 * @throws Exception ha mar van ilyen nevu objektum, Exception-t dob
	 */
	public void addObject(Object o, String key) throws Exception 
	{
		if(objects.putIfAbsent(key, o)!= null)
			throw new Exception("$Mar van ilyen nevu objektum");
	}
	public void init()
	{
	}
	
	public void generateItems()
	{
	}
	
	public void generateMap(String filename) throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		String s = sc.nextLine();
		String temp[] = s.split("\t");
		int length = temp.length;
		int[][] mapMatrix = new int[length][length];
		for(int i = 0; i<length; i++) {
			s = sc.nextLine();
			temp = s.split("\t");
			for(int j = 0; j < length; j++)
			{
				mapMatrix[i][j] = Integer.parseInt(temp[j]);
			}
		}
		s = sc.nextLine();
		s = sc.nextLine();
		temp = s.split("\t");
		int[] type = new int[length];
		for(int i = 0; i<length; i++) {
			type[i] = Integer.parseInt(temp[i]);
		}
		String name = "";
		int stable = 0, unstable = 0, hole = 0;
		for(int i = 0; i<length; i++)
		{
			switch(type[i])
			{
			case 0:
			{
				name = "st" + stable;
				stable++;
				StableIce st = new StableIce();
				objects.put(name, st);
				mapPieces.add(st);
				break;
			}
			case 1:
			{
				name = "usi" + unstable;
				unstable++;
				UnstableIce usi = new UnstableIce((int)(numOfCharacters / 2));
				objects.put(name, usi);
				mapPieces.add(usi);
				break;
			}
			case 2:
			{
				name = "hi" + hole;
				hole++;
				HoleIce hi = new HoleIce();
				objects.put(name, hi);
				mapPieces.add(hi);
				break;
			}	
			default:
				break;
			}
		}
		for(int i = 0; i < length; i++)
		{
			for(int j = i; j < length; j++)
			{
				if(mapMatrix[i][j] == 1)
				{
					mapPieces.get(i).addNeighbour(mapPieces.get(j));
					mapPieces.get(j).addNeighbour(mapPieces.get(i));
				}
			}
		}
		
		
	}
	
	public void generateCharacters()
	{
	}
	
	
	public void winGame()
	{
		System.out.println("Game Won!");
	}
	
	public void loseGame()
	{
		System.out.println("Game Lost!");
	}

	/**
	 * A karakterek szamanak gettere.
	 * @return a karakterek szama.
	 */
	public int getNumOfCharacters(){
		return numOfCharacters;
	}

	/**
	 * Megnoveli a karakterek szamat eggyel.
	 */
	public void incCharNum(){
		numOfCharacters++;
	}

	/**
	 * Lecsokkenti a karakterek szamat eggyel.
	 */
	public void decCharNum(){
		numOfCharacters--;
	}

	public void turnend() throws Exception 
	{
		
		Random rand = new Random();
		
		for (PolarBear pb : maci) {
			pb.move(rand.nextInt(pb.getIce().getNeighbourNum()));
		}
		for (Ice ice : mapPieces)
		{
			if(rand.nextBoolean())
				ice.stormEffects();
		}
		for (Ice ice : mapPieces)
		{
			ice.turnEnd();
			if(ice.getCharNum() > 0)
				for(int i = 0; i < ice.getCharNum(); i++)
					ice.getCharacter(i).resetAction();
		}
		
		
	}
}
