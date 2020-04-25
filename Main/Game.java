package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Ice.Ice;

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




public class Game
{
	private static Game game = new Game();
	private Game() {
		
	}
	public static Game getInstance() {
		return game;
	}
	private ArrayList<Ice> mapPieces;
	private Map<String, Object> objects = new HashMap<String, Object>();
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
			throw new Exception("Nincs ilyen objektum a jatekban!");
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
			throw new Exception("Mar van ilyen nevu objektum");
	}
	public void init()
	{
	}
	
	public void generateItems()
	{
	}
	
	public void generateMap()
	{
	}
	
	public void generateCharacters()
	{
	}
	
	public void randomizeStorm()
	{
	}
	
	public void winGame()
	{
	}
	
	public void loseGame()
	{
	}
}
