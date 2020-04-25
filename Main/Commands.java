package Main;
import Characters.*;
import Characters.Character;
import Ice.*;
import Item.Item;

import java.io.*;

public class Commands
{
	enum mode {GAME, INIT}
	mode m;
	String[] args;
	void start() throws Exception {
		m = mode.GAME;
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
		else if(m == mode.INIT)
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
	private void breakice(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void warmup(String[] args2)
	{
		String key = args2[1];
	 ((Character) Game.getInstance().getObjects().get(key)).warmup();
		
	}
	private void item(String[] args2)
	{
		switch (args2[1])
		{
		case "list":
			if(args2[2] == "all")
			{
				for(String key: Game.getInstance().getObjects().keySet())
					if(!(Game.getInstance().getObjects().get(key) instanceof Character) || !(Game.getInstance().getObjects().get(key) instanceof Ice))
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
		String key = args2[1];
		if(!(Game.getInstance().getObjects().get(key) instanceof Character)) 
			throw new IllegalArgumentException("Object is not a Character!");
		// TODO Befejezni, ha megvan az assemblegun
	}
	private void use(String[] args2) throws Exception
	{
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
			((Ice)Game.getInstance().getObjects().get(key1)).
			break;
		}
		}
		
	}
	private void delete(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void create(String[] args2)
	{
		// TODO Auto-generated method stub
		
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
