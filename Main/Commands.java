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
import Item.Item;
import Item.Rope;
import Item.Shovel;
import Item.Tent;
import Strategy.Bear;
import Strategy.BearStrategy;
import Strategy.DivingSuitStrategy;
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
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		temp = br.readLine();
		temp.toLowerCase();
		args = temp.split(" ");
		String command = args[0];
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
				if (args[1] == "game")
					m = mode.GAME;				
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
				throw new IllegalArgumentException("Unexpected value: " + command);
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
				default:
					throw new IllegalArgumentException("Unexpected value: " + command);
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
	 * @param args2 
	 */
	private void breakice(String[] args2)
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
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
				throw new IllegalArgumentException("Sikertelen jegtores!");
		}
		else{
			throw new IllegalArgumentException("Nincs ilyen objektum!");
		}
	}
	
	/**
	 * 
	 * @param args2
	 */
	private void warmup(String[] args2)
	{
		if (args2.length < 2)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
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
			throw new IllegalArgumentException("Nincs ilyen objektum!");
		}
		
	}
	private void item(String[] args2)
	{
		if (args2.length < 2)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
		switch (args2[1])
		{
		case "list":
			if(args2[2] == "all")
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
								String temp = it.getClass().toString();
								String[] temp2 = temp.split(".");
								temp = temp2[temp2.length-1];
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
								String temp = it.getClass().toString();
								String[] temp2 = temp.split(".");
								temp = temp2[temp2.length-1];
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
						String temp = it.getClass().toString();
						String[] temp2 = temp.split(".");
						temp = temp2[temp2.length-1];
						System.out.println("\t" + index + " " + temp);
					}
					System.out.println("}");
						
				}
				else if(Game.getInstance().getObjects().get(args2[2]) instanceof Ice)
				{
					System.out.println("{\n" + args2[2] + " Items:");
					int index = 0;
					for (Item it: ((Character)Game.getInstance().getObjects().get(args2[2])).getEquipment())
					{
						String temp = it.getClass().toString();
						String[] temp2 = temp.split(".");
						temp = temp2[temp2.length-1];
						System.out.println("\t" + index + " " + temp);
						index++;
					}
					System.out.println("}");
				}
				else throw new IllegalArgumentException("Unexpected value: " + args2[2]);
			}
			break;
		case "give":
			if(args2.length < 5)
				throw new IllegalArgumentException("Not enough arguments!");
			String key1 = args2[2], key2 = args2[3];
			int id = Integer.parseInt(args2[4]);
			if(!(Game.getInstance().getObjects().get(key1) instanceof Character) || !(Game.getInstance().getObjects().get(key2) instanceof Character)) 
				throw new IllegalArgumentException("Object is not a Character!");
			if(id < 0 || id > 5)
				throw new IllegalArgumentException("Maximum index: 5. Given index: " + id);
			((Character)Game.getInstance().getObjects().get(key1)).itemGive((Character)(Game.getInstance().getObjects().get(key2)), ((Character)Game.getInstance().getObjects().get(key1)).getItem(id));
			
			
			break;
		case "drop":
		{
			if(args2.length < 4)
				throw new IllegalArgumentException("Not enough arguments!");
			String key = args2[2];
			int index = Integer.parseInt(args2[3]);
			if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
				throw new IllegalArgumentException("Object is not a Character!");
			((Character)Game.getInstance().getObjects().get(key)).itemDiscard(((Character)Game.getInstance().getObjects().get(key)).getItem(index));
		
			break;
			}
		case "pickup":
		{
			if(args2.length < 4)
				throw new IllegalArgumentException("Not enough arguments!");
			String key = args2[2];
			int index = Integer.parseInt(args2[3]);
			if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
				throw new IllegalArgumentException("Object is not a Character!");
			((Character)Game.getInstance().getObjects().get(key)).itemPickup(index);
			break;
		}
			default:
				throw new IllegalArgumentException("Unexpected value: " + args2[1]);
		}
		
	}
	private void dig(String[] args2)
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
		String key = args2[1];
		int snowBefore, snowAfter;
		if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
			throw new IllegalArgumentException("Object is not a Character!");
		snowBefore = ((Character)Game.getInstance().getObjects().get(key)).getIce().getSnow();
		((Character)Game.getInstance().getObjects().get(key)).dig();
		snowAfter = ((Character)Game.getInstance().getObjects().get(key)).getIce().getSnow();
		System.out.println("$A ho vastagsaga " + snowBefore + "rol " + snowAfter+"re csokkent!");
		
	}
	private void assemble(String[] args2)
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
		String key = args2[1];
		if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
			throw new IllegalArgumentException("Object is not a Character!");
		((Character)Game.getInstance().getObjects().get(key)).assembleGun();
	}
	private void use(String[] args2) throws Exception
	{
		if (args2.length < 2)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
		switch(args2[1]) 
		{
		case "ability":
			{
				if(args2.length < 3)
					throw new IllegalArgumentException("Not enough arguments");
				if(args2.length == 3) 
				{
					String key = args2[2];
					if(!(Game.getInstance().getObjects().get(key) instanceof Eskimo)) 
						throw new IllegalArgumentException("Object is not an Eskimo!");
					((Eskimo)Game.getInstance().getObjects().get(key)).ability();
				}
				if(args2.length == 4) 
				{
					int d = Integer.parseInt(args2[3]);
					String key = args2[2];
					if(!(Game.getInstance().getObjects().get(key) instanceof Scientist)) 
						throw new IllegalArgumentException("Object is not an Eskimo!");
					((Scientist)Game.getInstance().getObjects().get(key)).ability(d);
				}
				break;	
			}
		case "item":
			{
				if(args2.length < 4)
					throw new IllegalArgumentException("Not enough arguments");
				String key = args2[2];
				int id = Integer.parseInt(args2[3]);
				if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
					throw new IllegalArgumentException("Object is not a Character!");
				((Character)Game.getInstance().getObjects().get(key)).getItem(id).use();
				break;
			}
		default:
			throw new IllegalArgumentException("Unexpected value: " + args2[1]);
		}
	}
	private void move(String[] args2) throws Exception
	{
		if(args2.length < 3)
			throw new IllegalArgumentException("Not enough arguments");
		String key = args2[1];
		int d = Integer.parseInt(args2[2]);
		if((Game.getInstance().getObjects().get(key) instanceof Character)) 
			((Character)Game.getInstance().getObjects().get(key)).move(d);
		else if((Game.getInstance().getObjects().get(key) instanceof PolarBear)) 
			((PolarBear)Game.getInstance().getObjects().get(key)).move(d);
		else
			throw new IllegalArgumentException("Object is not a Movable Object!");
		
	}
	/**
	 * 
	 * 
	 * @param args2
	 * @throws IllegalArgumentException ha a tomb merete kisebb mint 4
	 * @thorws IllegalArgumentException ha az argumentum 3. eleme nem Ice
	 * @throws IllegalArgumentException ha az argumentum 4. eleme nem Ice
	 * @throws IllegalArgumentException ha az 
	 * @throws IllegalArgumentException ha az 
	 */
	private void set(String[] args2)
	{
		if(args2.length < 4)
			throw new IllegalArgumentException("Not enough arguments"); 
		String attrib = args2[1];
		switch (attrib) 
		{
		case "neighbour":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException(key1 + " is not Ice!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Ice)) 
				throw new IllegalArgumentException(key2 + " is not Ice!");
			((Ice)Game.getInstance().getObjects().get(key1)).addNeighbour((Ice)Game.getInstance().getObjects().get(key2));
			((Ice)Game.getInstance().getObjects().get(key2)).addNeighbour((Ice)Game.getInstance().getObjects().get(key1));
			
			break;
		}
		case "snow":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException(key1 + " is not Ice!");
			int num = Integer.parseInt(args2[3]);
			if(num < 0 || num > 5)
				throw new IllegalArgumentException(num + " is an invalid number for snow!");
			((Ice)Game.getInstance().getObjects().get(key1)).setSnow(num);
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "character":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException(key1 + " is not Ice!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Character)) 
				throw new IllegalArgumentException(key2 + " is not Character!");
			((Ice)Game.getInstance().getObjects().get(key1)).addCharacter(((Character)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			break;
		}
		case "bearstrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException(key1 + " is not Ice!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof BearStrategy)) 
				throw new IllegalArgumentException(key2 + " is not BearStrategy!");
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
				throw new IllegalArgumentException(key2 + " is not Item!");
			((Ice)Game.getInstance().getObjects().get(key1)).addItem(((Item)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			}
			else if(Game.getInstance().getObjects().get(key1) instanceof Character)
			{
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof Item)) 
				throw new IllegalArgumentException(key2 + " is not Item!");
			((Character)Game.getInstance().getObjects().get(key1)).addItem(((Item)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			}
			else
				throw new IllegalArgumentException(key1 + " is not Item or Character!");
			
			break;
		}
		case "igloostrategy":
		{
			String key1 = args2[2]; 
			if(!(Game.getInstance().getObjects().get(key1) instanceof Ice)) 
				throw new IllegalArgumentException(key1 + " is not Ice!");
			String key2 = args2[3];
			if(!(Game.getInstance().getObjects().get(key2) instanceof IglooStrategy)) 
				throw new IllegalArgumentException(key2 + " is not IglooStrategy!");
			((Ice)Game.getInstance().getObjects().get(key1)).setIglooStrategy(((IglooStrategy)Game.getInstance().getObjects().get(key2)));
			System.out.println("$Sikeres beallitas!");
			break;
		}
		
		}
		
	}
	/**
	 * Torli a parameterben megadott tombot.
	 * Kulonbozo keppen jar el ha a kulonbozo tipusu objektumoknal.
	 * @param args2
	 * @throws IllegalArgumentException ha a tomb merete nem 2
	 * @thorws IllegalArgumentException ha az argumentum Ice tipusu es a rajtalevo karakterek szama nagyobb 0
	 * @throws IllegalArgumentException ha az argumentum tipusa nem egyezik az elotte ellenorzottekkel
	 */
	private void delete(String[] args2) throws Exception
	{
		if (args2.length != 2)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
		String key = args2[1];
		if (Game.getInstance().getObjects().get(key) instanceof Character){
			Character character = ((Character)Game.getInstance().getObjects().get(key));
			Game.getInstance().getObjects().remove(character);
			character.getIce().removeCharacter(character);
			System.out.println("$A(z) " + key + " objektum torlese sikeres!");
		}
		else if (Game.getInstance().getObjects().get(key) instanceof Ice){
			Ice ice = ((Ice)Game.getInstance().getObjects().get(key));
			if (ice.getCharNum() > 0)
				throw new IllegalArgumentException("Sikertelen torles!");
			Game.getInstance().getObjects().remove(ice);
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
			Game.getInstance().getObjects().remove(item);
			System.out.println("$A(z) " + key + " objektum torlese sikeres!");

		}
		else{
			throw new IllegalArgumentException("Nincs ilyen objektum!");
		}
	}

	private void create(String[] args2)
	{
		if (args2.length != 3)
			throw new IllegalArgumentException("Nem megfelelo parameterszam!");
		String type = args2[1];
		String name = args2[2];
		switch(type){
			case "Eskimo": Game.getInstance().addObject(new Eskimo(), name);
				break;
			case "Scientist": Game.getInstance().addObject(new Scientist(), name);
				break;
			case "PolarBear": Game.getInstance().addObject(new PolarBear(), name);
				break;
			case "HoleIce": Game.getInstance().addObject(new HoleIce(), name);
				break;
			case "UnstableIce": Game.getInstance().addObject(new UnstableIce(3), name);
				break;
			case "StableIce": Game.getInstance().addObject(new StableIce(), name);
				break;
			case "BreakableShovel": Game.getInstance().addObject(new BreakableShovel(), name);
				break;
			case "Cartridge": Game.getInstance().addObject(new Cartridge(), name);
				break;
			case "DivingSuit": Game.getInstance().addObject(new DivingSuit(), name);
				break;
			case "Flare": Game.getInstance().addObject(new Flare(), name);
				break;
			case "FlareGun": Game.getInstance().addObject(new FlareGun(), name);
				break;
			case "Food": Game.getInstance().addObject(new Food(), name);
				break;
			case "Rope": Game.getInstance().addObject(new Rope(), name);
				break;
			case "Shovel": Game.getInstance().addObject(new Shovel(), name);
				break;
			case "Tent": Game.getInstance().addObject(new Tent(), name);
				break;
			case "Bear": Game.getInstance().addObject(new Bear(), name);
				break;
			case "DivingSuitStrategy": Game.getInstance().addObject(new DivingSuitStrategy(), name);
				break;
			case "Igloo": Game.getInstance().addObject(new Igloo(), name);
				break;
			case "NoBear": Game.getInstance().addObject(new NoBear(), name);
				break;
			case "NoDivingSuit": Game.getInstance().addObject(new NoDivingSuit(), name);
				break;
			case "NoIgloo": Game.getInstance().addObject(new NoIgloo(), name);
				break;
			case "NoRopeHelp": Game.getInstance().addObject(new NoRopeHelp(), name);
				break;
			case "NoShovelDig": Game.getInstance().addObject(new NoShovelDig(), name);
				break;
			case "RopeHelp": Game.getInstance().addObject(new RopeHelp(), name);
				break;
			case "ShovelDig": Game.getInstance().addObject(new ShovelDig(), name);
				break;
			case "TentStrategy": Game.getInstance().addObject(new TentStrategy(), name);
				break;
			default: throw new IllegalArgumentException("$Nincs ilyen objektum!");
				break;
		}
	}
	private void save(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void help(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void load(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void state(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
}
