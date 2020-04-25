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
	void start() throws IOException {
		m = mode.GAME;
		String temp;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		temp = br.readLine();
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
					if(Game.getInstance().getObjects().get(key) instanceof PolarBear)
						;
					else
					{
						if(Game.getInstance().getObjects().get(key) instanceof Ice)
						{
							System.out.println(key + " Items:");
							int index = 0;
							for (Item it: ((Ice)Game.getInstance().getObjects().get(key)).getItemArray())
							{
								String temp = it.getClass().toString();
								String[] temp2 = temp.split(".");
								temp = temp2[temp2.length-1];
								System.out.println("\t" + index + " " + temp);
								index++;
							}
						}
						if(Game.getInstance().getObjects().get(key) instanceof Character)
						{
							System.out.println(key + " Items:");
							int index = 0;
							for (Item it: ((Character)Game.getInstance().getObjects().get(key)).getEquipment())
							{
								String temp = it.getClass().toString();
								String[] temp2 = temp.split(".");
								temp = temp2[temp2.length-1];
								System.out.println("\t" + index + " " + temp);
								index++;
							}
						}
					}
			}
			else 
			{
				if(Game.getInstance().getObjects().get(args2[2]) instanceof Ice)
				{
					
				}
				else if(Game.getInstance().getObjects().get(args2[2]) instanceof Ice)
				{
					
				}
				else throw new IllegalArgumentException("Unexpected value: " + args2[2]);
			}
			break;
		case "give":
			break;
		case "drop":
			break;
		case "pickup":
			break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + args2[1]);
		}
		
	}
	private void dig(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void assemble(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void use(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void move(String[] args2)
	{
		// TODO Auto-generated method stub
		
	}
	private void set(String[] args2)
	{
		// TODO Auto-generated method stub
		
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
