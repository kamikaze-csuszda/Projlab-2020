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

public class Commands
{
	enum mode {GAME, INIT}
	mode m;
	String[] args;
	
	/**A program indulasaval indul el. Default modon INIT modban indul a jatek, ami at inicializacios modja a programnak. 
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
			temp = temp.toLowerCase();
			args = temp.split(" ");
			String command = args[0];
			try {
			if(m == mode.INIT)
				switch (command)
				{
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
					if (args[1] == "game") {
						m = mode.GAME;
						System.out.println("$Mar Game modban vagyunk!");
					}
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
				case "start":
					if (args[1] == "init")
						m = mode.INIT;	
						System.out.println("$Mar Init modban vagyunk!");
					default:
						throw new IllegalArgumentException("$Nincs ilyen parancs! A teljes parancslistahoz hasznalja a help parancsot!");
		}
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		
			}
	}
	
	/**A karakter feltori a jeget, azon a jegtablan ahol all. Ennek eredmenye, hogy minden targy ami azon a jegtablan volt kiolvad, es felvehetove valik. 
	 * Miutan a targyakat kitortuk a jegbol, ki is listazzuk a felhasznalonak. 
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ha nem sikerult a jeget feltorni
	 * - nincs ilyen objektum 
	 * 
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza 
	 */
	private void breakice(String[] args2)
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
					print.concat(" " + temp + ",");
				}
				print.concat("eszkozoket!");
				System.out.println(print);
			}
			else
				throw new IllegalArgumentException("$Sikertelen jegtores!");
		}
		else{
			throw new IllegalArgumentException("$Nincs ilyen objektum!");
		}
	}
	
	/**Az adott karakter 3 akci� pontot felhasznal �s noveli a testhojet 1-gyel, ha van hianyzo testhoje 
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ha nem sikerult a jeget feltorni
	 * - nincs ilyen objektum 
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza 
	 */
	private void warmup(String[] args2)
	{
		if (args2.length < 2)
			throw new IllegalArgumentException("$A parancs nem hasznalhato ennyi parameterrel! Hasznalja a 'help warmup' parancsot tovabbi informacioert!");
		String key = args2[1];
		if (Game.getInstance().getObjects().get(key) instanceof Character){
			int warmthbefore = ((Character) Game.getInstance().getObjects().get(key)).getWarmth();
			((Character) Game.getInstance().getObjects().get(key)).warmup();
			int warmthafter = ((Character) Game.getInstance().getObjects().get(key)).getWarmth();
			if (warmthbefore < warmthafter)
				System.out.println("$Sikeresen melegedtel! Jelenlegi testhod: " + warmthafter + "!");
			else{
				System.out.println("$Mar maximum testhon vagy, nem kell melegedned!");
			}

		}
		else{
			throw new IllegalArgumentException("$Nincs ilyen objektum!");
		}
		
	}
	/**Minden targyal kapcsolatos interakcio
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
	 */
	private void item(String[] args2)
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
							System.out.println("{\n" + key + " Items:");
							int index = 0;
							for (Item it: ((Ice)Game.getInstance().getObjects().get(key)).getItemArray())
							{
								String temp = it.getItemClass();
								System.out.println("\t" + index + " " + temp);
								index++;
							}
							System.out.println("}");
						}
						if(Game.getInstance().getObjects().get(key) instanceof Character)
						{
							System.out.println("{\n" + key + "s Items:");
							int index = 0;
							for (Item it: ((Character)Game.getInstance().getObjects().get(key)).getEquipment())
							{
								String temp = it.getItemClass();
								System.out.println("\t" + index + " " + temp);
								index++;
							}
							System.out.println("}");
						}
					}
			}
			else 
			{
				if(Game.getInstance().getObjects().get(args2[2]) instanceof Ice)
				{
					
					System.out.println("{\n" + args2[2] + " Items:");
					int index = 0;
					for (Item it: ((Ice)Game.getInstance().getObjects().get(args2[2])).getItemArray())
					{
						String temp = it.getItemClass();
						System.out.println("\t" + index + " " + temp);
					}
					System.out.println("}");
						
				}
				else if(Game.getInstance().getObjects().get(args2[2]) instanceof Character)
				{
					System.out.println("{\n" + args2[2] + " Items:");
					int index = 0;
					for (Item it: ((Character)Game.getInstance().getObjects().get(args2[2])).getEquipment())
					{
						String temp = it.getItemClass();
						System.out.println("\t" + index + " " + temp);
						index++;
					}
					System.out.println("}");
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
			System.out.println("$Sikeresen atadtad a " + ((Character)Game.getInstance().getObjects().get(key1)).getItem(id) + " eszkozt" + ((Character)Game.getInstance().getObjects().get(key1)).getItem(id) + "nek!");
			}
			
			break;
		case "drop":
		{
			if(args2.length < 4)
				throw new IllegalArgumentException("$Not enough arguments!");
			String key = args2[2];
			int index = Integer.parseInt(args2[3]);
			if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
				throw new IllegalArgumentException("$"+key+" is not a Character!");
			((Character)Game.getInstance().getObjects().get(key)).itemDiscard(((Character)Game.getInstance().getObjects().get(key)).getItem(index));
		
			break;
			}
		case "pickup":
		{
			if(args2.length < 4)
				throw new IllegalArgumentException("$Not enough arguments!");
			String key = args2[2];
			int index = Integer.parseInt(args2[3]);
			if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
				throw new IllegalArgumentException("$"+key+" is not a Character!");
			((Character)Game.getInstance().getObjects().get(key)).itemPickup(index);
			break;
		}
			default:
				throw new IllegalArgumentException("$Unexpected value: " + args2[1]);
		}
		
	}
	
	/**A karakter havat as lapattal, vagy kezzel.
	 * Az asas a havat eltunteti, ha lapattal, akkor 2 egysegnyi, ha kezzel akkor 1 egysegnyit.
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - ismeretlen parancsot kap
	 * - nem megfelelo a parameter tipusa
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 */
	private void dig(String[] args2)
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$Nem megfelelo parameterszam!");
		String key = args2[1];
		int snowBefore, snowAfter;
		if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
			throw new IllegalArgumentException("$"+key+" is not a Character!");
		snowBefore = ((Character)Game.getInstance().getObjects().get(key)).getIce().getSnow();
		((Character)Game.getInstance().getObjects().get(key)).dig();
		snowAfter = ((Character)Game.getInstance().getObjects().get(key)).getIce().getSnow();
		System.out.println("$A ho vastagsaga " + snowBefore + "rol " + snowAfter+"re csokkent!");
		
	}
	
	/**Osszeallitja a fegyvert a fegyverdarabokbol. 
	 * 
	 * Hibat dob, ha:
	 * - nem megfelelo szamu argumentum van 
	 * - nem megfelelo a parameter tipusa
	 * 
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 */
	private void assemble(String[] args2)
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$Nem megfelelo parameterszam!");
		String key = args2[1];
		if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
			throw new IllegalArgumentException("$"+key+" is not a Character!");
		((Character)Game.getInstance().getObjects().get(key)).assembleGun();
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
			throw new IllegalArgumentException("$Nem megfelelo parameterszam!");
		switch(args2[1]) 
		{
		case "ability":
			{
				if(args2.length < 3)
					throw new IllegalArgumentException("$Not enough arguments");
				if(args2.length == 3) 
				{
					String key = args2[2];
					if(!(Game.getInstance().getObjects().get(key) instanceof Eskimo)) 
						throw new IllegalArgumentException("$"+key+" is not an Eskimo!");
					((Eskimo)Game.getInstance().getObjects().get(key)).ability();
				}
				if(args2.length == 4) 
				{
					int d = Integer.parseInt(args2[3]);
					String key = args2[2];
					if(!(Game.getInstance().getObjects().get(key) instanceof Scientist)) 
						throw new IllegalArgumentException("$"+key+" is not an Eskimo!");
					((Scientist)Game.getInstance().getObjects().get(key)).ability(d);
				}
				break;	
			}
		case "item":
			{
				if(args2.length < 4)
					throw new IllegalArgumentException("$Not enough arguments");
				String key = args2[2];
				int id = Integer.parseInt(args2[3]);
				if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
					throw new IllegalArgumentException("$"+key+" is not a Character!");
				((Character)Game.getInstance().getObjects().get(key)).getItem(id).use();
				break;
			}
		default:
			throw new IllegalArgumentException("$Unexpected value: " + args2[1]);
		}
	}
	/**
	 * Mozgatja a karaktereket �s a jegyesmedvet.
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws IllegalArgumentException ha kevesebb mint 3 elem van a tombben
	 * @throws IllegalArgumentException ha az objektum nem mozgathato
	 */
	private void move(String[] args2) throws Exception
	{
		if(args2.length < 3)
			throw new IllegalArgumentException("$Not enough arguments");
		String key = args2[1];
		int d = Integer.parseInt(args2[2]);
		if((Game.getInstance().getObjects().get(key) instanceof Character)) 
			((Character)Game.getInstance().getObjects().get(key)).move(d);
		else if((Game.getInstance().getObjects().get(key) instanceof PolarBear)) 
			((PolarBear)Game.getInstance().getObjects().get(key)).move(d);
		else
			throw new IllegalArgumentException("$Object is not a Movable Object!");
		
	}
	/**
	 * Bealltja a megadott mezo tulajdonsagait:
	 * - szomszedokat
	 * - ho mennyiseget
	 * - hozzaadhat karaktert vagy targyakat
	 * - beallitja a strategiakat
	 * @param args2 String tomb ami a fuggveny parametereit tartalmazza
	 * @throws IllegalArgumentException ha a tomb merete kisebb mint 4
	 * @thorws IllegalArgumentException ha az argumentum 3. eleme nem Ice
	 * @throws IllegalArgumentException ha az argumentum 4. eleme nem megfelelo az argumentum tipusa
	 */
	private void set(String[] args2)
	{
		if(args2.length < 4)
			throw new IllegalArgumentException("$Not enough arguments"); 
		String attrib = args2[1];
		switch (attrib) 
		{
		case "neighbour":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " is not Ice!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key2 + " is not Ice!");
			((Ice)Game.getInstance().getObjects().get(key1)).addNeighbour((Ice)Game.getInstance().getObjects().get(key2));
			((Ice)Game.getInstance().getObjects().get(key2)).addNeighbour((Ice)Game.getInstance().getObjects().get(key1));
			
			break;
		}
		case "snow":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " is not Ice!");
			int num = Integer.parseInt(args2[3]);
			if(num < 0 || num > 5)
				throw new IllegalArgumentException("$"+num + " is an invalid number for snow!");
			((Ice)Game.getInstance().getObjects().get(key1)).setSnow(num);
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "character":
		{
			String key1 = args2[2]; 
			if((Game.getInstance().getObjects().get(key1) instanceof Ice)) 
			{
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Character)) 
				throw new IllegalArgumentException("$"+key2 + " is not Character!");
			((Ice)Game.getInstance().getObjects().get(key1)).addCharacter(((Character)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			}
			if((Game.getInstance().getObjects().get(key1) instanceof Item)) 
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Character)) 
					throw new IllegalArgumentException("$"+key2 + " is not Character!");
				((Item)Game.getInstance().getObjects().get(key1)).setCharacter(((Character)Game.getInstance().getObjects().get(key2)));
				System.out.println("$Sikeres beallitas!");
			}
			else
				throw new IllegalArgumentException("$"+key1 + " is not Ice or Item!");
			break;
		}
		case "bearstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " is not Ice!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof BearStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " is not BearStrategy!");
			((Ice)Game.getInstance().getObjects().get(key1)).setBearStrategy(((BearStrategy)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			
			break;
		}
		case "item":
		{
			String key1 = args2[2]; 
			if(Game.getInstance().getObjects().get(key1) instanceof Ice)
			{
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Item)) 
				throw new IllegalArgumentException("$"+key2 + " is not Item!");
			((Ice)Game.getInstance().getObjects().get(key1)).addItem(((Item)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			}
			else if(Game.getInstance().getObjects().get(key1) instanceof Character)
			{
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Item)) 
				throw new IllegalArgumentException("$"+key2 + " is not Item!");
			((Character)Game.getInstance().getObjects().get(key1)).addItem(((Item)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			}
			else
				throw new IllegalArgumentException("$"+key1 + " is not Item or Character!");
			
			break;
		}
		case "igloostrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException("$"+key1 + " is not Ice!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof IglooStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " is not IglooStrategy!");
			((Ice)Game.getInstance().getObjects().get(key1)).setIglooStrategy(((IglooStrategy)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "bodywarmth":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " is not Character!");
			int num = Integer.parseInt(args2[3]);
			((Character)Game.getInstance().getObjects().get(key1)).setBodywarmth(num);
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "action":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " is not Character!");
			int num = Integer.parseInt(args2[3]);
			((Character)Game.getInstance().getObjects().get(key1)).setAction(num);
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "ice":
		{
			String key1 = args2[2];
			if((Game.getInstance().getObjects().get(key1) instanceof Character)) 
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
					throw new IllegalArgumentException("$"+key2 + " is not Ice!");
				((Character)Game.getInstance().getObjects().get(key1)).setIce(((Ice)Game.getInstance().getObjects().get(key2)));
				System.out.println("$Sikeres beallitas!");
			}
			else if((Game.getInstance().getObjects().get(key1) instanceof Item)) 
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
					throw new IllegalArgumentException("$"+key2 + " is not Ice!");
				((Item)Game.getInstance().getObjects().get(key1)).setIce(((Ice)Game.getInstance().getObjects().get(key2)));
				System.out.println("$Sikeres beallitas!");
			}
			else if((Game.getInstance().getObjects().get(key1) instanceof PolarBear))
			{
				String key2 = args2[3];
				if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
					throw new IllegalArgumentException("$"+key2 + " is not Ice!");
				((PolarBear)Game.getInstance().getObjects().get(key1)).setIce(((Ice)Game.getInstance().getObjects().get(key2)));
				System.out.println("$Sikeres beallitas!");
			}
			else throw new IllegalArgumentException("$"+key1 + " is not Character or Item!");
			break;
		}
		case "gunpart":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " is not Character!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get("$"+key2) instanceof Gun)) 
				throw new IllegalArgumentException(key2 + " is not Gun!");
			((Character)Game.getInstance().getObjects().get(key1)).addGunpart(((Gun)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "digstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " is not Character!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof DigStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " is not DigStrategy!");
			((Character)Game.getInstance().getObjects().get(key1)).setDigStrategy(((DigStrategy)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "waterstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " is not Character!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof WaterStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " is not WaterStrategy!");
			((Character)Game.getInstance().getObjects().get(key1)).setWaterStrategy(((WaterStrategy)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "helpstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character)) 
				throw new IllegalArgumentException("$"+key1 + " is not Character!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof HelpStrategy)) 
				throw new IllegalArgumentException("$"+key2 + " is not HelpStrategy!");
			((Character)Game.getInstance().getObjects().get(key1)).setHelpStrategy(((HelpStrategy)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "durability":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Item)) 
				throw new IllegalArgumentException("$"+key1 + " is not Item!");
			int num = Integer.parseInt(args[3]);
			((Item)Game.getInstance().getObjects().get(key1)).setDurability(num);
			System.out.println("$Sikeres beallitas!");
			break;
		}
		default:
			throw new IllegalArgumentException("$Unexpected value: " + args2[1]);
		
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
			throw new IllegalArgumentException("$Nem megfelelo parameterszam!");
		String key = args2[1];
		if (Game.getInstance().getObjects().get(key) instanceof Character){
			Character character = ((Character)Game.getInstance().getObjects().get(key));
			Game.getInstance().getObjects().remove(key);
			character.getIce().removeCharacter(character);
			System.out.println("$A(z) " + key + " objektum torlese sikeres!");
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
			System.out.println("$A(z) " + key + " objektum torlese sikeres!");

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
			throw new IllegalArgumentException("$Nem megfelelo parameterszam!");
		String type = args2[1];
		String name = args2[2];
		switch(type){
			case "eskimo": Game.getInstance().addObject(new Eskimo(), name);
				break;
			case "scientist": Game.getInstance().addObject(new Scientist(), name);
				break;
			case "polarbear": Game.getInstance().addObject(new PolarBear(), name);
				break;
			case "holeice": Game.getInstance().addObject(new HoleIce(), name);
				break;
			case "unstableice": Game.getInstance().addObject(new UnstableIce(3), name);
				break;
			case "stableice": Game.getInstance().addObject(new StableIce(), name);
				break;
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
	}
	private void save(String[] args2)
	{
		switch (args2[1])
		{
		case "state":
		{
			try
			{
				FileOutputStream fout = new FileOutputStream(args2[2]);
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				oout.writeObject(Game.getInstance());
				oout.close();
				fout.close();
				System.out.println("$Sikeres mentes!");
			} catch (Exception e)
			{
				System.out.println("$Sikertelen mentes!");
			}
			break;
		}
		case "map":
		{
			
			break;
		}
		default: throw new IllegalArgumentException("$Unexpected value: " + args2[1]);
		}
		
	}
	/**
	* A paracstol fuggoen kiirja a segitseget. Minden parancshoz tarozik legalbb 1 segitseg.
	* @param args2 String tomb ami a fuggveny parametereit tartalmazza
	* @throws IllegalArgumentException ha a parameter szam nem 2 
	*/
	private void help(String[] args2)
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$Nem megfelelo parameterszam!");
		String command = args2[1];
		System.out.println("$Lehetseges parameterek ( a felsorolt parameterek minden esetben kotelezoek! ):");
		switch(command){
			case "load":
				System.out.println("$\tstate <fajlnev> : Betolt egy elore elkeszitett jatekallast a megadott fajlbol. A fajlnevnek tartalmaznia kell a .txt kiterjesztest.");
				System.out.println("$\tlevel <fajlnev> : Betolt egy elore elkeszitett palyat a megadott fajlbol. A fajlnevnek tartalmaznia kell a .txt kiterjesztest.");
				break;
			case "save":
				System.out.println("$\t<fajlnev> : Kimenti a megadott fajlba az osszes letezo objektumot. A fajlnevnek tartalmaznia kell a .txt kiterjesztest.");
				break;
			case "create":
				System.out.println("$\t<objektum> <nev> : Letrehoz egy peldanyt a megadott típussal es a megadott nevvel.");
				System.out.println("$\t\tobjektum: Barmelyik peldanyosithato osztaly neve kerulhet ide.");
				System.out.println("$\t\tnev: A nev, amit az adott peldanynak adni akarunk, kesobb ezzel hivatkozhato. Ha tartalmaz whitespace karaktert, akkor csak az elso whitespacig lesz eltarolva.");
				break;
			case "delete":
				System.out.println("$\t<objektum neve> : Kitorli a megadott nevvel rendelkezo peldanyt a jatekbol. Ha nem letezik a megadott objektum, ertesitest kapunk.");
				break;
			case "start":
				System.out.println("$\tgame : Jatek modba valt. A jatekban hasznalatos parancsokat lehet kiadni.");
				System.out.println("$\tinit : Inicializalas modba valt. Az inicializalasnal hasznalatos parancsok adhatok ki.");
				break;
			case "state":
				System.out.println("$\tall : Minden objektumrol keszit egy teljes leirast.");
				System.out.println("$\t<objektum> : A megadott objektumrol keszit egy teljes leirast.");
				System.out.println("$\t\tobjektum: Az objektum neve, aminek az alalpotat le szeretnenk kerdezni. Ha nem letezik az objektum, ertesitest kapunk.");
				break;
			case "set":
				System.out.println("$\tneighbour <jegtabla> <jegtabla> : Ket jegtabla szomszedossagat allitja, igy a ket megadott objektum csak valamilyen jegtabla lehet.");
				System.out.println("$\t<attributum> <objektum> <ertek> : A megadott objektum megadott attributumanak erteket allitja be a megadott ertekre.");
				break;
			case "move":
				System.out.println("$\t<objektum> <irany> : A nevevel hivatkozott objektumot elmozgatja a megadott iranyba.");
				System.out.println("$\t\tobjektum: Csak Character illetve PolarBear tipusu lehet.");
				System.out.println("$\t\tirany: Egesz szam, azt reprezentalja, hogy a jelenlegi jegtabla hanyadik szomszedjara lepunk.");
				break;
			case "use":
				System.out.println("$\titem <karakter> <index> : A megadott karakter hasznalja a nala levo targyat.");
				System.out.println("$\t\tkarakter: Barmelyik letezo karakter neve.");
				System.out.println("$\t\tindex: A hasznalni kivant targy indexe a karakter eszkoztaraban. 0-4-ig terjedo egesz szam lehet.");
				break;
			case "assemble":
				System.out.println("$\t<karakter> : A karakter megprobalja osszeszerelni a pisztolyt a reszekbol.");
				System.out.println("$\t\tkarakter: Csak a ket letezo karaktertipus egyike lehet, Eskimo vagy Scientist.");
				break;
			case "dig":
				System.out.println("$\t<karakter> : A megadott karakter havat lapatol a jelenlegi tartozkodasi helyen.");
				System.out.println("$\t\tkarakter: Csak a ket letezo karaktertipus egyike lehet, Eskimo vagy Scientist.");
				break;
			case "breakice":
				System.out.println("$\t<karakter> : A megadott karakter feltori a jeget a jelenlegi tartozkodasi helyen.");
				System.out.println("$\t\tkarakter: Csak a ket letezo karaktertipus egyike lehet, Eskimo vagy Scientist.");
				break;
			case "item":
				System.out.println("$\tlist all : Az osszes jatekban letezo targyat kilistazza.");
				System.out.println("$\tlist <objektum> : A megadott objektumhoz kapcsolodo targyakat listazza ki.");
				System.out.println("$\t\tobjektum: Lehet Eskimo, Scientist, ekkor a karakter eszkoztaraban talalhato targyakat listazza ki, illetve barmilyen jegtabla tipus, ekkor pedig a jegen talalhato targyakat listazza");
				System.out.println("$\tgive <karakter> <karakter> <index> : Egy karakter atad egy targyat egy masiknak.");
				System.out.println("$\t\tkarakter: Lehet Eskimo vagy Scientist, az elso argumentum adja, a masodik kapja a targyat.");
				System.out.println("$\t\tindex: Az atadni kivant targy indexe az atado karakter eszkoztaraban. 0-4-ig terjedo egesz szam lehet.");
				System.out.println("$\tdrop <karakter> <index> : A karakter ledob egy targyat a jegtablara.");
				System.out.println("$\t\tkarakter: Lehet Eskimo vagy Scientist, a nevevel hivatkozva.");
				System.out.println("$\t\tindex: Az eldobni kivant targy indexe a karakter eszkoztaraban. 0-4-ig terjedo egesz szam lehet.");
				System.out.println("$\tpickup <karakter> <index> : A karakter felveszi a megadott elemet a jegtablarol.");
				System.out.println("$\t\tkarakter: A karakter akivel fel akarjuk venni a targyat.");
				System.out.println("$\t\tindex: Az index, hogy hanyadik targyat akarjuk felvenni a jegtablarol.");
				break;
			case "warmup":
				System.out.println("$\t<karakter> : A megadott karakter 3 akciopont felhasznalasaval noveli eggyel a testhojet, amennyiben nincs meg maximumon.");
				System.out.println("$\t\tkarakter: Lehet Eskimo vagy Scientist.");
				break;
		}
		
	}
	private void load(String[] args2)
	{
		switch(args2[1])
		{
		case "map":
		{
			String filename = args2[2];
			try
			{
				Game.getInstance().generateMap(filename);
			} catch (FileNotFoundException e)
			{
				System.out.println("$Sikertelen palyabetoltes!");
			}
			break;
		}
		case "state":
		{
			try
			{
				
			} catch (Exception e)
			{
				System.out.println("$Sikertelen betoltes!");
			}
			break;
		}
		default:
			throw new IllegalArgumentException("$Unexpected value: " + args2[1]);
		}
		
	}
	private void state(String[] args2)
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("$Nem megfelelo paramterszam!");
		String argument = args2[1];
		if (argument.equals("all")){

		}
		else if (Game.getInstance().getObjects().get(argument) instanceof Eskimo){

		}
		else if (Game.getInstance().getObjects().get(argument) instanceof Scientist){

		}
		else if (Game.getInstance().getObjects().get(argument) instanceof PolarBear){

		}
	}
}
