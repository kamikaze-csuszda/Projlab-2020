package Main;
import Characters.*;
import Characters.Character;
import Ice.*;
import Item.BreakableShovel;
import Item.Cartridge;
import Item.DivingSuit;
import Item.Flare;
import Item.FlareGun;
import Item.Food;
import Item.Gun;
import Item.Item;
import Item.Rope;
import Item.Shovel;
import Item.Tent;
import Strategy.Bear;
import Strategy.BearStrategy;
import Strategy.DigStrategy;
import Strategy.DivingSuitStrategy;
import Strategy.HelpStrategy;
import Strategy.Igloo;
import Strategy.IglooStrategy;
import Strategy.NoBear;
import Strategy.NoDivingSuit;
import Strategy.NoIgloo;
import Strategy.NoRopeHelp;
import Strategy.NoShovelDig;
import Strategy.RopeHelp;
import Strategy.ShovelDig;
import Strategy.TentStrategy;
import Strategy.WaterStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Commands
{
	enum mode {GAME, INIT}
	mode m;
	ArrayList<String> commands = new ArrayList<String>();
	String[] args;
	ArrayList<String> output = new ArrayList<String>();

	/**
	 * A program indulasaval indul el. Default modon INIT modban indul a jatek, ami at inicializacios modja a programnak.
	 * Ez a fuggveny hivja meg a kapott parancsnak megfelelo metodust. 
	 * Mukodes:
	 *  - standard inputrol beolvassunk a br stringbe egy sort, majd kisbetusiti
	 *  - ezt kovetoen feldaraboljuk a parancsot minden whitespace karakternel -> az elso szo a parancs, a tobbi az argumnetuma
	 *  - megvizsgaljuk, hogy milyen modban (GAME/INIT) vagyunk, es ennek megfeleloen milyen parancsok vannak ertelmezve
	 *  - a parancsnak megfelelo fuggvenyt meghivjuk, amennyiben van ilyen, ha nincs, akkor kivetelt dob a program
	 * 
	 * INIT modban hivhato parancsok: state, load, help, start, create, delete, set
	 * GAME modban hivhato parancsok: breakice, move, use, assemble, dig, item, warmup, help, start
	 * 
	 * @throws Exception: nem letezo vagy adott modban nem ertelemezett parancs eseten dobja a kivetelt 
	 */
	void start() throws Exception {
		m = mode.INIT;
		String temp;
		temp = "def. not null";
		
		while(temp != null)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
			temp = br.readLine();
			if(!temp.contains("save state"))
				commands.add(temp);
			temp = temp.toLowerCase();
			args = temp.split(" ");
			execute(args);
			
		
			}
	}
	public void execute(String[] args) throws Exception {
		String command = args[0];
		try {
			if(m == mode.INIT)
				switch (command)
				{
				case "storm":
					storm(args);
					break;
				case "close":
					 System.exit(0);
					 break;
				case "state":
					state(args);
				
					break;
				case "load":
					load(args);
					
					break;
				case "help":
					help(args);
					break;
				case "start":
					if(m.equals(mode.INIT))
					if (args[1].equals("game")) {
						m = mode.GAME;
						 throw new Exception("$----------Game Mode----------");
					}
					else 
						throw new Exception("$Mar Game modban vagyunk!");
					break;
				case "item":
					item(args);
					break;
				case "save":
					save(args);
					break;
				case "create":
					create(args);
					break;
				case "delete":
					delete(args);
					break;
				case "set":
					set(args);
					break;
				
				default:
					throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
				}
			else if(m == mode.GAME)
				switch(command) 
				{
				case "state":
					state(args);
				
					break;
				case "close":
					 System.exit(0);
					 break;
				case "breakice":
					breakice(args);
					break;
				case "move":
					move(args);
					break;
				case "use":
					use(args);
					break;
				case "assemble":
					assemble(args);
					break;
				case "dig":
					dig(args);
					break;
				case "item":
					item(args);
					break;
				case "warmup":
					warmup(args);
					break;
				case "help":
					help(args);
					break;
				case "turnend":
					turnend(args);
					break;
				case "start":
					if(m.equals(mode.GAME))
					if (args[1].equals("INIT")) {
						m = mode.INIT;	
						throw new Exception("$----------Init Mode----------");
					}
					else
						throw new Exception("$Mar Init modban vagyunk!");
					break;
						
					default:
						throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		}
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
				output.add(e.getMessage());
			}
	}
	
	private void storm(String[] args2) throws Exception
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help breakeice' parancsot tovabbi informacioert!");
		if(!(Game.getInstance().getObjects().get(args2[1]) instanceof Ice))
			throw new IllegalArgumentException(args2[1] + " nem Ice!");
		((Ice)Game.getInstance().getObjects().get(args2[1])).stormEffects();
		throw new Exception("$Sikeres hovihar!");
		
	}
	/**
	 * A karakter feltori a jeget, azon a jegtablan ahol all. Ennek eredmenye, hogy minden targy ami azon a jegtablan volt kiolvad, es felvehetove valik.
	 * Miutan a targyakat kitortuk a jegbol, ki is listazzuk a felhasznalonak. 
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ha nem sikerult a jeget feltorni
	 * - nincs ilyen objektum 
	 * 
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza 
	 * @throws Exception 
	 */
	private void breakice(String[] args2) throws Exception
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help breakeice' parancsot tovabbi informacioert!");
	 	String key = args2[1];
		if (Game.getInstance().getObjects().get(key) instanceof Ice){
			if (((Ice)Game.getInstance().getObjects().get(key)).getSnow() == 0){
				String print = new String("$Sikeresen kitorted a jegbol a(z)");
				for (Item it: ((Ice)Game.getInstance().getObjects().get(key)).getItemArray()){
					it.defrost();
					String temp = it.getClass().toString();
					String[] temp2 = temp.split(".");
					temp = temp2[temp2.length-1];
					print = print.concat(" " + temp + ",");
				}
				print = print.concat("eszkozoket!");
				throw new Exception(print);
			}
			else
				throw new IllegalArgumentException("$Sikertelen jegtores!");
		}
		else{
			throw new IllegalArgumentException("$Nincs ilyen objektum!");
		}
	}
	
	/**
	 * Az adott karakter 3 akcio pontot felhasznal es noveli a testhojet 1-gyel, ha van hianyzo testhoje
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ha nem sikerult a jeget feltorni
	 * - nincs ilyen objektum 
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza 
	 * @throws Exception 
	 */
	private void warmup(String[] args2) throws Exception
	{
		if (args2.length < 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help warmup' parancsot tovabbi informacioert!");
		String key = args2[1];
		if (Game.getInstance().getObjects().get(key) instanceof Character){
			int warmthbefore = ((Character) Game.getInstance().getObjects().get(key)).getWarmth();
			((Character) Game.getInstance().getObjects().get(key)).warmup();
			int warmthafter = ((Character) Game.getInstance().getObjects().get(key)).getWarmth();
			if (warmthbefore < warmthafter)
				throw new Exception("$Sikeresen melegedtel! Jelenlegi testhod: " + warmthafter + "!");
			else{
				throw new Exception("$Mar maximum testhon vagy, nem kell melegedned!");
			}

		}
		else{
			throw new IllegalArgumentException("$Nincs ilyen objektum!");
		}
		
	}
	/**
	 * Minden targyal kapcsolatos interakcio
	 * Elfogadott (meta) parancsok:
	 * - item list all: minden targy kilistazasa ami a jatekban jelen van, barhol (jegen/karakternel)
	 * - item list <<ice>>: a megnevezett jegtablan levo targyak kilistazasa 
	 * - item list <<character>>: a megnevezett karakternel levo targyak kilistazasa 
	 * - item give <<character1>> <<character2>> <<int>>: az egyik karakter atad egy masik karakternek egy targyat a sajat targyai kezul
	 * - item drop <<character>> <<int>>: az egyik karakter eldob egy targyat a sajat targyai kozul a jegtablara, ahol all 
	 * - item pickup <<character>> <<int>>: az egyik karakter felvesz egy targyat a jegrol es berakja a sajatjai koze, ha van helye
	 * a <<>>-vel jelolt parameterek meta parameterek, helyukre valamilyen letezo objektum neve, vagy ertek kell keruljon,
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ismeretlen parancsot kap
	 * - nem megfelelo a parameter tipusa
	 * - nincs olyan objektum amire hivatkozik
	 * - tulindexeles lep fel
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws Exception 
	 */
	private void item(String[] args2) throws Exception
	{
		if (args2.length < 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help item' parancsot tovabbi informacioert!");
		switch (args2[1])
		{
		case "list":
			if(args2[2].equals("all"))
			{
				for(String key: Game.getInstance().getObjects().keySet())
					if(!(Game.getInstance().getObjects().get(key) instanceof Character) && !(Game.getInstance().getObjects().get(key) instanceof Ice))
						;
					else
					{
						if(Game.getInstance().getObjects().get(key) instanceof Ice)
						{
							String print = new String("{\n" + key + " Items:");
							int index = 0;
							for (Item it: ((Ice)Game.getInstance().getObjects().get(key)).getItemArray())
							{
								String temp = it.getItemClass();
								print = print.concat("\t" + index + " " + temp);
								index++;
							}
							throw new Exception(print + "\n}");
						}
						if(Game.getInstance().getObjects().get(key) instanceof Character)
						{
							String printString = new String("{\n" + key + "s Items:");
							int index = 0;
							for (Item it: ((Character)Game.getInstance().getObjects().get(key)).getEquipment())
							{
								String temp = it.getItemClass();
								printString = printString.concat("\n\t" + index + " " + temp);
								index++;
							}
							throw new Exception(printString + "\n}");
						}
					}
			}
			else 
			{
				if(Game.getInstance().getObjects().get(args2[2]) instanceof Ice)
				{
					
					String printString = new String("{\n" + args2[2] + " Items:");
					int index = 0;
					for (Item it: ((Ice)Game.getInstance().getObjects().get(args2[2])).getItemArray())
					{
						String temp = it.getItemClass();
						printString = printString.concat("\n\t" + index + " " + temp);
					}
					throw new Exception(printString + "\n}");
						
				}
				else if(Game.getInstance().getObjects().get(args2[2]) instanceof Character)
				{
					String printString = new String("{\n" + args2[2] + " Items:");
					int index = 0;
					for (Item it: ((Character)Game.getInstance().getObjects().get(args2[2])).getEquipment())
					{
						String temp = it.getItemClass();
						printString = printString.concat("\n\t" + index + " " + temp);
						index++;
					}
					throw new Exception(printString + "\n}");
				}
				else throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
			}
			break;
		case "give":
			if(args2.length < 5)
				throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help item' parancsot tovabbi informacioert!");
			String key1 = args2[2], key2 = args2[3];
			int id = Integer.parseInt(args2[4]);
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character) || !(Game.getInstance().getObjects().get(key2) instanceof Character)) 
				throw new IllegalArgumentException("$Nem karakter objektum!");
			if(id < 0 || id > 5)
				throw new IllegalArgumentException("$Nincs ennyi eszkozod! Hasznald az 'item list' parancsot az eszkozeid listazasahoz!");
			else {
			((Character)Game.getInstance().getObjects().get(key1)).itemGive((Character)(Game.getInstance().getObjects().get(key2)), ((Character)Game.getInstance().getObjects().get(key1)).getItem(id));
			throw new Exception("$Sikeresen atadtad a " + ((Character)Game.getInstance().getObjects().get(key1)).getItem(id).getItemClass() + " eszkozt " + key2 + "nek!");
			}
			
			
		case "drop":
		{
			if(args2.length < 4)
				throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help item' parancsot tovabbi informacioert!");
			String key = args2[2];
			int index = Integer.parseInt(args2[3]);
			if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
				throw new IllegalArgumentException("$"+key+" nem Karakter!");
			String item = Game.getInstance().findName(((Character)Game.getInstance().getObjects().get(key)).getItem(index));
			((Character)Game.getInstance().getObjects().get(key)).itemDiscard(((Character)Game.getInstance().getObjects().get(key)).getItem(index));
			throw new Exception("$Ledobtad a(z) " + item + " eszkozt a " +  Game.getInstance().findName(((Character)Game.getInstance().getObjects().get(key)).getIce()) + " mezore!");
			}
		case "pickup":
		{
			if(args2.length < 4)
				throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help item' parancsot tovabbi informacioert!");
			String key = args2[2];
			int index = Integer.parseInt(args2[3]);
			if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
				throw new IllegalArgumentException("$"+key+" nem Karakter!");
			String item = Game.getInstance().findName(((Character)Game.getInstance().getObjects().get(key)).getIce().getItem(index));
			((Character)Game.getInstance().getObjects().get(key)).itemPickup(index);
			throw new Exception("$Sikeresen felvetted a(z) " + item + " eszkozt!");
		}
			default:
				throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		}
		
	}
	
	/**
	 * A karakter havat as lapattal, vagy kezzel.
	 * Az asas a havat eltunteti, ha lapattal, akkor 2 egysegnyi, ha kezzel akkor 1 egysegnyit.
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ismeretlen parancsot kap
	 * - nem megfelelo a parameter tipusa
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws Exception 
	 */
	private void dig(String[] args2) throws Exception
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help dig' parancsot tovabbi informacioert!");
		String key = args2[1];
		int snowBefore, snowAfter;
		if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
			throw new IllegalArgumentException("$"+key+" nem Karakter!");
		snowBefore = ((Character)Game.getInstance().getObjects().get(key)).getIce().getSnow();
		((Character)Game.getInstance().getObjects().get(key)).dig();
		snowAfter = ((Character)Game.getInstance().getObjects().get(key)).getIce().getSnow();
		throw new Exception("$A ho vastagsaga " + snowBefore + "rol " + snowAfter+"re csokkent!");
		
	}
	
	/**Osszeallitja a fegyvert a fegyverdarabokbol. 
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - nem megfelelo a parameter tipusa
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws Exception 
	 */
	private void assemble(String[] args2) throws Exception
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help assemble' parancsot tovabbi informacioert!");
		String key = args2[1];
		if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
			throw new IllegalArgumentException("$"+key+" nem Karakter!");
		((Character)Game.getInstance().getObjects().get(key)).assembleGun();
		throw new Exception("$Sikeres assembleGun parancs!");
	}
	/**A targyak vagy kepessegek hasznalata. 
	 * Eszkimo iglut epit, a sarkkutato 
	 * 
	 * Elfogadott parancsok:
	 * - use ability <<character>> (<<int>>): karakter kepesseget hasznalja, az utolso parameter csak akkor kell, ha sarkkutatorol van szo
	 * - use <<item>> <<character>> <<int>>: a karakter a megadot sorszamu targyat hasznalja a nala levok kozul
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ismeretlen parancsot kap
	 * - nem megfelelo a parameter tipusa
	 * - nincs olyan objektum amire hivatkozik
	 * - tulindexeles lep fel
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws Exception
	 */
	private void use(String[] args2) throws Exception
	{
		if (args2.length < 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help use' parancsot tovabbi informacioert!");
		switch(args2[1]) 
		{
		case "ability":
			{
				if(args2.length < 3)
					throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help use' parancsot tovabbi informacioert!");
				if(args2.length == 3) 
				{
					String key = args2[2];
					if(!(Game.getInstance().getObjects().get(key) instanceof Eskimo)) 
						throw new IllegalArgumentException("$"+key+" nem Eskimo!");
					((Eskimo)Game.getInstance().getObjects().get(key)).ability();
					throw new Exception("$Sikeres igluepites!");
				}
				if(args2.length == 4) 
				{
					int d = Integer.parseInt(args2[3]);
					String key = args2[2];
					if(!(Game.getInstance().getObjects().get(key) instanceof Scientist)) 
						throw new IllegalArgumentException("$"+key+" nem Sarkkutato!");
					int teher = ((Scientist)Game.getInstance().getObjects().get(key)).ability(d);
					throw new Exception("$" + d + " iranyban levo jegtabla " + teher + " embert bir el!");
				}
				break;	
			}
		case "item":
			{
				if(args2.length < 4)
					throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help use' parancsot tovabbi informacioert!");
				String key = args2[2];
				int id = Integer.parseInt(args2[3]);
				if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
					throw new IllegalArgumentException("$"+key+" nem Karakter!");
				((Character)Game.getInstance().getObjects().get(key)).getItem(id).use();
				break;
			}
		default:
			throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		}
	}
	/**
	 * Mozgatja a karaktereket es a jegyesmedvet.
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws IllegalArgumentException ha kevesebb mint 3 elem van a tombben
	 * @throws IllegalArgumentException ha az objektum nem mozgathato
	 */
	private void move(String[] args2) throws Exception
	{
		if(args2.length < 3)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help move' parancsot tovabbi informacioert!");
		String key = args2[1];
		int d = Integer.parseInt(args2[2]);
		if((Game.getInstance().getObjects().get(key) instanceof Character)) 
			((Character)Game.getInstance().getObjects().get(key)).move(d);
		else if((Game.getInstance().getObjects().get(key) instanceof PolarBear)) 
			((PolarBear)Game.getInstance().getObjects().get(key)).move(d);
		else
			throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		throw new Exception("$A " + key + " objektum sikeresen mozgott " + d + " iranyba!");
		
	}
	/**
	 * Bealltja a megadott mezo tulajdonsagait:
	 * - szomszedokat
	 * - ho mennyiseget
	 * - hozzaadhat karaktert vagy targyakat
	 * - beallitja a strategiakat
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws Exception 
	 * @throws IllegalArgumentException ha a tomb merete kisebb mint 4
	 * @thorws IllegalArgumentException ha az argumentum 3. eleme nem Ice
	 * @throws IllegalArgumentException ha az argumentum 4. eleme nem megfelelo az argumentum tipusa
	 */
	private void set(String[] args2) throws Exception
	{
		if(args2.length < 4)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help set' parancsot tovabbi informacioert!");
		String attrib = args2[1];
		switch (attrib) 
		{
		case "neighbour":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " nem Jeg!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key2 + " nem Jeg!");
			((Ice)Game.getInstance().getObjects().get(key1)).addNeighbour((Ice)Game.getInstance().getObjects().get(key2));
			((Ice)Game.getInstance().getObjects().get(key2)).addNeighbour((Ice)Game.getInstance().getObjects().get(key1));
			
			break;
		}
		case "snow":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " nem Jeg!");
			int num = Integer.parseInt(args2[3]);
			if(num < 0 || num > 5)
				throw new IllegalArgumentException("$"+num + " nem elfogadhato homagassag");
			((Ice)Game.getInstance().getObjects().get(key1)).setSnow(num);
			throw new Exception("$Sikeres beallitas!");
		}
		case "character":
		{
			String key1 = args2[2]; 
			if((Game.getInstance().getObjects().get(key1) instanceof Ice)) 
			{
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Character)) 
				throw new IllegalArgumentException("$"+key2 + " nem Karakter!");
			((Ice)Game.getInstance().getObjects().get(key1)).addCharacter(((Character)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
			}
			if((Game.getInstance().getObjects().get(key1) instanceof Item)) 
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Character)) 
					throw new IllegalArgumentException("$"+key2 + " nem Karakter!");
				((Item)Game.getInstance().getObjects().get(key1)).setCharacter(((Character)Game.getInstance().getObjects().get(key2)));
				throw new Exception("$Sikeres beallitas!");
			}
			else
				throw new IllegalArgumentException("$"+key1 + "nem Jeg vagy Targy!");
		}
		case "bearstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " nem Jeg!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof BearStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " nem BearStrategy!");
			((Ice)Game.getInstance().getObjects().get(key1)).setBearStrategy(((BearStrategy)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
		}
		case "item":
		{
			String key1 = args2[2]; 
			if(Game.getInstance().getObjects().get(key1) instanceof Ice)
			{
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Item)) 
				throw new IllegalArgumentException("$"+key2 + " nem Targy!");
			((Ice)Game.getInstance().getObjects().get(key1)).addItem(((Item)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
			}
			else if(Game.getInstance().getObjects().get(key1) instanceof Character)
			{
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Item)) 
				throw new IllegalArgumentException("$"+key2 + " nem Targy!");
			((Character)Game.getInstance().getObjects().get(key1)).addItem(((Item)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
			}
			else
				throw new IllegalArgumentException("$"+key1 + " nem Jeg vagy Targy!");
		}
		case "igloostrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " nem Jeg");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof IglooStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " nem IglooStrategy!");
			((Ice)Game.getInstance().getObjects().get(key1)).setIglooStrategy(((IglooStrategy)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
		}
		case "bodywarmth":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " nem Karakter!");
			int num = Integer.parseInt(args2[3]);
			((Character)Game.getInstance().getObjects().get(key1)).setBodywarmth(num);
			throw new Exception("$Sikeres beallitas!");
		}
		case "action":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " nem Karakter!");
			int num = Integer.parseInt(args2[3]);
			((Character)Game.getInstance().getObjects().get(key1)).setAction(num);
			throw new Exception("$Sikeres beallitas!");
		}
		case "ice":
		{
			String key1 = args2[2];
			if((Game.getInstance().getObjects().get(key1) instanceof Character)) 
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
					throw new IllegalArgumentException("$"+key2 + " nem Jeg!");
				((Character)Game.getInstance().getObjects().get(key1)).setIce(((Ice)Game.getInstance().getObjects().get(key2)));
				throw new Exception("$Sikeres beallitas!");
			}
			else if((Game.getInstance().getObjects().get(key1) instanceof Item)) 
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
					throw new IllegalArgumentException("$"+key2 + " nem Jeg!");
				((Item)Game.getInstance().getObjects().get(key1)).setIce(((Ice)Game.getInstance().getObjects().get(key2)));
				throw new Exception("$Sikeres beallitas!");
			}
			else if((Game.getInstance().getObjects().get(key1) instanceof PolarBear))
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
					throw new IllegalArgumentException("$"+key2 + " nem Jeg");
				((PolarBear)Game.getInstance().getObjects().get(key1)).setIce(((Ice)Game.getInstance().getObjects().get(key2)));
				throw new Exception("$Sikeres beallitas!");
			}
			else throw new IllegalArgumentException("$"+key1 + " nem Karakter vagy Targy!");
		}
		case "gunpart":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " nem Karakter");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get("$"+key2) instanceof Gun)) 
				throw new IllegalArgumentException(key2 + " nem Fegyver!");
			((Character)Game.getInstance().getObjects().get(key1)).addGunpart(((Gun)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
		}
		case "digstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " nem Karakter!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof DigStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " nem Karakter!");
			((Character)Game.getInstance().getObjects().get(key1)).setDigStrategy(((DigStrategy)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
		}
		case "waterstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + "nem Karakter!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof WaterStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " nem WaterStrategy!");
			((Character)Game.getInstance().getObjects().get(key1)).setWaterStrategy(((WaterStrategy)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
		}
		case "helpstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " nem Karakter!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof HelpStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " nem HelpStrategy!");
			((Character)Game.getInstance().getObjects().get(key1)).setHelpStrategy(((HelpStrategy)Game.getInstance().getObjects().get(key2)));
			throw new Exception("$Sikeres beallitas!");
		}
		case "durability":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Item)) 
				throw new IllegalArgumentException("$"+key1 + " nem Targy!");
			int num = Integer.parseInt(args[3]);
			((Item)Game.getInstance().getObjects().get(key1)).setDurability(num);
			throw new Exception("$Sikeres beallitas!");
		}
		case "frozen":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Item)) 
				throw new IllegalArgumentException("$"+key1 + " nem Targy!");
			if(args2[3].equals("false"))
				((Item)Game.getInstance().getObjects().get(key1)).setFrozen(false);
			else
				((Item)Game.getInstance().getObjects().get(key1)).setFrozen(true);
			throw new Exception("$Sikeres beallitas!");
		}
		default:
			throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		
		}
		
	}
	/**
	 * Torli a parameterben megadott tombot.
	 * Kulonbozo keppen jar el ha a kulonbozo tipusu objektumoknal.
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws IllegalArgumentException ha a tomb merete nem 2
	 * @thorws IllegalArgumentException ha az argumentum Ice tipusu es a rajtalevo karakterek szama nagyobb 0
	 * @throws IllegalArgumentException ha az argumentum tipusa nem egyezik az elotte ellenorzottekkel
	 */
	private void delete(String[] args2) throws Exception
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help delete' parancsot tovabbi informacioert!");
		String key = args2[1];
		if (Game.getInstance().getObjects().get(key) instanceof Character){
			Character character = ((Character)Game.getInstance().getObjects().get(key));
			Game.getInstance().getObjects().remove(key);
			character.getIce().removeCharacter(character);
			throw new Exception("$A(z) " + key + " objektum torlese sikeres!");
		}
		else if (Game.getInstance().getObjects().get(key) instanceof Ice){
			Ice ice = ((Ice)Game.getInstance().getObjects().get(key));
			if (ice.getCharNum() > 0)
				throw new IllegalArgumentException("$Sikertelen torles!");
			Game.getInstance().getObjects().remove(key);
			for (int i = 0; i < ice.getNeighbourNum(); i++){
				ice.getNeighbour(i).removeNeighbour(ice);
			}
		}
		else if (Game.getInstance().getObjects().get(key) instanceof Item){
			Item item = ((Item)Game.getInstance().getObjects().get(key));
			if (item.getCharacter() == null){
				item.getIce().removeItem(item);

			}
			else{
				item.getCharacter().removeItem(item);

			}
			Game.getInstance().getObjects().remove(key);
			throw new Exception("$A(z) " + key + " objektum torlese sikeres!");

		}
		else{
			throw new IllegalArgumentException("$Nincs ilyen objektum!");
		}
	}
	
	/**
	 * Letrehozza a parameterben megadott agrumentumot.
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws IllegalArgumentException ha a parameterben megadott tombben kevesebb mint 3 elem van
	 * @throws IllegalArgumentException ha a paramerteben megadott tombbel megegyezo objektum nem letezik
	 */
	private void create(String[] args2) throws Exception
	{
		if (args2.length != 3)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help create' parancsot tovabbi informacioert!");
		String type = args2[1];
		String name = args2[2];
		switch(type){
			case "eskimo": Game.getInstance().addObject(new Eskimo(), name);
				break;
			case "scientist": Game.getInstance().addObject(new Scientist(), name);
				break;
			case "polarbear":{
				PolarBear pb = new PolarBear();
				Game.getInstance().addObject(pb, name);
				Game.getInstance().getMaci().add(pb);
				break;
				}
			case "holeice":{
				HoleIce hi = new HoleIce();
				Game.getInstance().addObject(hi, name);
				Game.getInstance().getMapPieces().add(hi);
				break;
				}
			case "unstableice": {
				UnstableIce usi = new UnstableIce(1);
				Game.getInstance().addObject(usi, name);
				Game.getInstance().getMapPieces().add(usi);
				break;
				}
			case "stableice": {
				StableIce st = new StableIce();
				Game.getInstance().addObject(st, name);
				Game.getInstance().getMapPieces().add(st);
				break;
				}
			case "breakableshovel": Game.getInstance().addObject(new BreakableShovel(), name);
				break;
			case "cartridge": Game.getInstance().addObject(new Cartridge(), name);
				break;
			case "divingsuit": Game.getInstance().addObject(new DivingSuit(), name);
				break;
			case "flare": Game.getInstance().addObject(new Flare(), name);
				break;
			case "flaregun": Game.getInstance().addObject(new FlareGun(), name);
				break;
			case "food": Game.getInstance().addObject(new Food(), name);
				break;
			case "rope": Game.getInstance().addObject(new Rope(), name);
				break;
			case "shovel": Game.getInstance().addObject(new Shovel(), name);
				break;
			case "tent": Game.getInstance().addObject(new Tent(), name);
				break;
			case "bear": Game.getInstance().addObject(new Bear(), name);
				break;
			case "divingsuitstrategy": Game.getInstance().addObject(new DivingSuitStrategy(), name);
				break;
			case "igloo": Game.getInstance().addObject(new Igloo(), name);
				break;
			case "nobear": Game.getInstance().addObject(new NoBear(), name);
				break;
			case "nodivingsuit": Game.getInstance().addObject(new NoDivingSuit(), name);
				break;
			case "noigloo": Game.getInstance().addObject(new NoIgloo(), name);
				break;
			case "noropehelp": Game.getInstance().addObject(new NoRopeHelp(), name);
				break;
			case "noshoveldig": Game.getInstance().addObject(new NoShovelDig(), name);
				break;
			case "ropehelp": Game.getInstance().addObject(new RopeHelp(), name);
				break;
			case "shoveldig": Game.getInstance().addObject(new ShovelDig(new Shovel(3)), name);
				break;
			case "tentstrategy": Game.getInstance().addObject(new TentStrategy(), name);
				break;
			default: throw new IllegalArgumentException("$Nincs ilyen objektum!");
		}
		throw new Exception("$Sikeresen letrehozta a " + name + " objektumot!");
	}
	/**
	* Lementi a jatekot.
	* @param args2 String tomb ami a fuggveny parametereit tartalmazza
	* @throws Exception ha sikertelen a mentes
	*/
	private void save(String[] args2) throws Exception
	{
		switch (args2[1])
		{
		case "output":
		{
			try {
			File myFile = new File(args2[2]);
			myFile.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));
			for (String string : output)
			{
				bw.write(string);
				bw.newLine();
			}
			bw.close();
			} catch(Exception e)
			{
				throw new Exception("$Sikertelen mentes!");
			}
			throw new Exception("$Sikeres mentes!");
		}
		case "state":
		{
			try
			{
				File myFile = new File(args2[2]);
				myFile.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));
				for (String string : commands)
				{
					bw.write(string);
					bw.newLine();
				}
				bw.close();
				throw new Exception("$Sikeres mentes!");
			} catch (Exception e)
			{
				throw new Exception("$Sikertelen mentes!");
			}
		}
		case "map":
		{
			File myFile = new File(args2[2]);
			try
			{
				myFile.createNewFile();
			} catch (IOException e)
			{
				throw new Exception("$Sikertelen mentes!");
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));
			int size = Game.getInstance().getMapPieces().size();
			int[] type = new int[size];
			for(int i = 0; i < size; i++)
			{
				if(Game.getInstance().getMapPieces().get(i) instanceof StableIce)
					type[i] = 0;
				else if(Game.getInstance().getMapPieces().get(i) instanceof UnstableIce)
					type[i] = 1;
				else if(Game.getInstance().getMapPieces().get(i) instanceof HoleIce)
					type[i] = 2;
			}
			int map[][] = new int[size][size];
			for(int i = 0; i < size; i++)
			{
				for(int j = i; j < size; j++)
				{
					if(i == j)
						map[i][j] = 0;
					else 
					{
						if(Game.getInstance().getMapPieces().get(i).isNeighbour(Game.getInstance().getMapPieces().get(j)))
						{
							map[i][j] = 1; map[j][i] = 1;
						}
						else
						{
							map[i][j] = 0; map[j][i] = 0;
						}
					}
				}
			}
			for(int i = 0; i<size; i++)
			{
				bw.write(String.valueOf(i));
				if(i < size-1)
					bw.write("\t");
			}
			bw.newLine();
			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					bw.write(String.valueOf(map[i][j]));
					if(j<size-1)
						bw.write("\t");
				}
				bw.newLine();
			}
			bw.write("$");
			bw.newLine();
			for(int i = 0; i < size; i++)
			{
				bw.write(String.valueOf(type[i]));
				if (i<size-1)
					bw.write("\t");
			}
			bw.close();
			break;
		}
		default: throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		}
		
	}
	/**
	* A paracstol fuggoen kiirja a segitseget. Minden parancshoz tarozik legalbb 1 segitseg.
	* @param args2 String tomb ami a fuggveny parametereit tartalmazza
	* @throws Exception
	* @throws IllegalArgumentException ha a parameter szam nem 2 
	*/
	private void help(String[] args2) throws Exception
	{
		if (args2.length > 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help' parancsot tovabbi informacioert!");
		if (args2.length == 1){
			throw new Exception("Lehetseges parancsok ( tobb informacioert hasznalja a 'help <parancs>' parancsot ):"
			+ "\tload\n"
			+ "\tsave\n"
			+ "\tcreate\n"
			+ "\tdelete\n"
			+ "\tstart\n"
			+ "\tstate\n"
			+ "\tset\n"
			+ "\tmove\n"
			+ "\tuse\n"
			+ "\tassemble\n"
			+ "\tdig\n"
			+ "\tbreakice\n"
			+ "\titem\n"
			+ "\twarmup\n"
			+ "\tturnend\n");
		}
		else {
			String command = args2[1];
			String printString = new String("$Lehetseges parameterek ( a felsorolt parameterek minden esetben kotelezoek! ):");
			switch(command){
				case "load":
					printString = printString.concat("$\tstate <fajlnev> : Betolt egy elore elkeszitett jatekallast a megadott fajlbol. A fajlnevnek tartalmaznia kell a .txt kiterjesztest.");
					printString = printString.concat("$\tlevel <fajlnev> : Betolt egy elore elkeszitett palyat a megadott fajlbol. A fajlnevnek tartalmaznia kell a .txt kiterjesztest.");
					break;
				case "save":
					printString = printString.concat("$\t<fajlnev> : Kimenti a megadott fajlba az osszes letezo objektumot. A fajlnevnek tartalmaznia kell a .txt kiterjesztest.");
					break;
				case "create":
					printString = printString.concat("$\t<objektum> <nev> : Letrehoz egy peldanyt a megadott típussal es a megadott nevvel.");
					printString = printString.concat("$\t\tobjektum: Barmelyik peldanyosithato osztaly neve kerulhet ide.");
					printString = printString.concat("$\t\tnev: A nev, amit az adott peldanynak adni akarunk, kesobb ezzel hivatkozhato. Ha tartalmaz whitespace karaktert, akkor csak az elso whitespacig lesz eltarolva.");
					break;
				case "delete":
					printString = printString.concat("$\t<objektum neve> : Kitorli a megadott nevvel rendelkezo peldanyt a jatekbol. Ha nem letezik a megadott objektum, ertesitest kapunk.");
					break;
				case "start":
					printString = printString.concat("$\tgame : Jatek modba valt. A jatekban hasznalatos parancsokat lehet kiadni.");
					printString = printString.concat("$\tinit : Inicializalas modba valt. Az inicializalasnal hasznalatos parancsok adhatok ki.");
					break;
				case "state":
					printString = printString.concat("$\tall : Minden objektumrol keszit egy teljes leirast.");
					printString = printString.concat("$\t<objektum> : A megadott objektumrol keszit egy teljes leirast.");
					printString = printString.concat("$\t\tobjektum: Az objektum neve, aminek az alalpotat le szeretnenk kerdezni. Ha nem letezik az objektum, ertesitest kapunk.");
					break;
				case "set":
					printString = printString.concat("$\tneighbour <jegtabla> <jegtabla> : Ket jegtabla szomszedossagat allitja, igy a ket megadott objektum csak valamilyen jegtabla lehet.");
					printString = printString.concat("$\t<attributum> <objektum> <ertek> : A megadott objektum megadott attributumanak erteket allitja be a megadott ertekre.");
					break;
				case "move":
					printString = printString.concat("$\t<objektum> <irany> : A nevevel hivatkozott objektumot elmozgatja a megadott iranyba.");
					printString = printString.concat("$\t\tobjektum: Csak Character illetve PolarBear tipusu lehet.");
					printString = printString.concat("$\t\tirany: Egesz szam, azt reprezentalja, hogy a jelenlegi jegtabla hanyadik szomszedjara lepunk.");
					break;
				case "use":
					printString = printString.concat("$\titem <karakter> <index> : A megadott karakter hasznalja a nala levo targyat.");
					printString = printString.concat("$\t\tkarakter: Barmelyik letezo karakter neve.");
					printString = printString.concat("$\t\tindex: A hasznalni kivant targy indexe a karakter eszkoztaraban. 0-4-ig terjedo egesz szam lehet.");
					break;
				case "assemble":
					printString = printString.concat("$\t<karakter> : A karakter megprobalja osszeszerelni a pisztolyt a reszekbol.");
					printString = printString.concat("$\t\tkarakter: Csak a ket letezo karaktertipus egyike lehet, Eskimo vagy Scientist.");
					break;
				case "dig":
					printString = printString.concat("$\t<karakter> : A megadott karakter havat lapatol a jelenlegi tartozkodasi helyen.");
					printString = printString.concat("$\t\tkarakter: Csak a ket letezo karaktertipus egyike lehet, Eskimo vagy Scientist.");
					break;
				case "breakice":
					printString = printString.concat("$\t<karakter> : A megadott karakter feltori a jeget a jelenlegi tartozkodasi helyen.");
					printString = printString.concat("$\t\tkarakter: Csak a ket letezo karaktertipus egyike lehet, Eskimo vagy Scientist.");
					break;
				case "item":
					printString = printString.concat("$\tlist all : Az osszes jatekban letezo targyat kilistazza.");
					printString = printString.concat("$\tlist <objektum> : A megadott objektumhoz kapcsolodo targyakat listazza ki.");
					printString = printString.concat("$\t\tobjektum: Lehet Eskimo, Scientist, ekkor a karakter eszkoztaraban talalhato targyakat listazza ki, illetve barmilyen jegtabla tipus, ekkor pedig a jegen talalhato targyakat listazza");
					printString = printString.concat("$\tgive <karakter> <karakter> <index> : Egy karakter atad egy targyat egy masiknak.");
					printString = printString.concat("$\t\tkarakter: Lehet Eskimo vagy Scientist, az elso argumentum adja, a masodik kapja a targyat.");
					printString = printString.concat("$\t\tindex: Az atadni kivant targy indexe az atado karakter eszkoztaraban. 0-4-ig terjedo egesz szam lehet.");
					printString = printString.concat("$\tdrop <karakter> <index> : A karakter ledob egy targyat a jegtablara.");
					printString = printString.concat("$\t\tkarakter: Lehet Eskimo vagy Scientist, a nevevel hivatkozva.");
					printString = printString.concat("$\t\tindex: Az eldobni kivant targy indexe a karakter eszkoztaraban. 0-4-ig terjedo egesz szam lehet.");
					printString = printString.concat("$\tpickup <karakter> <index> : A karakter felveszi a megadott elemet a jegtablarol.");
					printString = printString.concat("$\t\tkarakter: A karakter akivel fel akarjuk venni a targyat.");
					printString = printString.concat("$\t\tindex: Az index, hogy hanyadik targyat akarjuk felvenni a jegtablarol.");
					break;
				case "warmup":
					printString = printString.concat("$\t<karakter> : A megadott karakter 3 akciopont felhasznalasaval noveli eggyel a testhojet, amennyiben nincs meg maximumon.");
					printString = printString.concat("$\t\tkarakter: Lehet Eskimo vagy Scientist.");
					break;
				case "turnend":
					printString = printString.concat("$\tturnend: Befejezi a karakterek koret, visszaallnak az akicopontjaik az alap ertekre, ekkor erkeznek a hoviharok, es ekkor lep a medve.");
			}
			throw new Exception(printString);
		}

		
	}
	/**
	* Betolti a parameterben megadott terkepet vagy state-et.
	* @param args2 String tomb ami a fuggveny parametereit tartalmazza
	* @throws Exception ha sikertelen a betoltes
	* @throws IllegalArgumentException
	*/
	private void load(String[] args2) throws Exception
	{
		switch(args2[1])
		{
		case "map":
		{
			String filename = args2[2];
			try
			{
				Game.getInstance().generateMap(filename);
				throw new Exception("$Sikeres betoltes!");
			} catch (FileNotFoundException e)
			{
				throw new Exception("$Sikertelen palyabetoltes!");
			}
			
		}
		case "state":
		{
			try
			{
				ArrayList<String> loaded = new ArrayList<String>();
				Scanner loadedScanner = new Scanner(new FileReader(args2[2]));
				while(loadedScanner.hasNextLine())
				{
					loaded.add(loadedScanner.nextLine());
				}
				String[] ldargs;
				for(int i = 0; i < loaded.size(); i++)
				{
					ldargs = loaded.get(i).split(" ");
					execute(ldargs);
				}
				throw new Exception("$Sikeres betoltes!");
			} catch (Exception e)
			{
				throw new Exception("$Sikertelen betoltes!");
			}

		}
		default:
			throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		}
		
	}
	/**
	*
	* @param args2 String tomb ami a fuggveny parametereit tartalmazza
	* @throws Exception 
	* @throws IllegalArgumentException
	*/
	private void state(String[] args2) throws Exception
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help state' parancsot tovabbi informacioert!");
		String argument = args2[1];
		if (argument.equals("all")){
			for (String key : Game.getInstance().getObjects().keySet()){
				if (Game.getInstance().getObjects().get(key) instanceof Character || Game.getInstance().getObjects().get(key) instanceof Ice || 
						Game.getInstance().getObjects().get(key) instanceof Item || Game.getInstance().getObjects().get(key) instanceof PolarBear) {
					execute(new String[] {"state", key});
				}

			}
		}
		else if (Game.getInstance().getObjects().get(argument) instanceof Eskimo){
			String message = new String();
			message = message.concat("Eskimo:\n { \n");
			message = message.concat("\tEskimo: " + argument + ":\n");
			message = message.concat("\t\tbodywarmth: " + ((Eskimo)Game.getInstance().getObjects().get(argument)).getWarmth());
			message = message.concat("\t\taction: " + ((Eskimo)Game.getInstance().getObjects().get(argument)).getAction());
			message = message.concat("\t\tIce: " + Game.getInstance().findName(((Eskimo)Game.getInstance().getObjects().get(argument)).getIce() + "\n"));
			message = message.concat("\t\tItems:");
			for (int i = 0; i < ((Eskimo)Game.getInstance().getObjects().get(argument)).getEquipment().size(); i++){
				message = message.concat(Game.getInstance().findName(((Eskimo)Game.getInstance().getObjects().get(argument)).getItem(i)) + ", ");
			}
			message = message.concat("\n\t\tGunParts:");
			for (int i = 0; i < ((Eskimo)Game.getInstance().getObjects().get(argument)).getGunParts().size(); i++){
				message = message.concat(Game.getInstance().findName(((Eskimo)Game.getInstance().getObjects().get(argument)).getGunPart(i)) + ", ");
			}
			message = message.concat("\n\t\tWaterStrategy:");
			try{

			}catch (Exception e)
			{
				
			}

			message = message.concat("\t\tDigStrategy:");
			message = message.concat("\t\tHelpStrategy:");
			message = message.concat("}");

		}
		else if (Game.getInstance().getObjects().get(argument) instanceof Scientist){

		}
		else if (Game.getInstance().getObjects().get(argument) instanceof PolarBear){

		}
		else if (Game.getInstance().getObjects().get(argument) instanceof Ice) {

		}
		else if (Game.getInstance().getObjects().get(argument) instanceof Item){

		}
	}
	/**
	*
	* @param args2 String tomb ami a fuggveny parametereit tartalmazza
	* @throws Exception
	* @throws IllegalArgumentException
	*/
	private void turnend(String[] args2) throws Exception
	{
		if (args2.length != 1)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help turnend' parancsot tovabbi informacioert!");
		Game.getInstance().turnend();
		throw new Exception("$Sikeres korvege");
		
	}
	
}
