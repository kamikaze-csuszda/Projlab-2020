package Main;

import java.util.Scanner;
import Characters.Eskimo;
import Characters.Scientist;
import Ice.HoleIce;
import Ice.StableIce;
import Ice.UnstableIce;
import Item.Cartridge;
import Item.DivingSuit;
import Item.Flare;
import Item.FlareGun;
import Item.Food;
import Item.Rope;
import Strategy.DivingSuitStrategy;
import Strategy.Igloo;
import Strategy.RopeHelp;
import Strategy.ShovelDig;

public class Main{

    public static void main(String[] args)
    {
    	
    	int ans = -1;
        while(ans != 0) {
        ans = menu();
        System.out.println("Bemenet: " + ans);
        switch (ans)
        {
            case 1:
            {
            	teszt1();
            	break;
            }
            case 2:
            {
            	teszt2();
            	break;
            }
            case 3:
            {
            	teszt3();
            	break;
            }
            case 4:
            {
            	teszt4();
            	break;
            }
            case 5:
            {
            	teszt5();
            	break;
            }
            case 6:
            {
            	teszt6();
            	break;
            }
            case 7:
            {
            	teszt7();
            	break;
            }
            case 8:
            {
            	teszt8();
            	break;
            }
            case 9:
            {
            	teszt9();
            	break;
            }
            case 10:
            {
            	teszt10();
            	break;
            }
            case 11:
            {
            	teszt11();
            	break;
            }
            case 12:
            {
            	teszt12();
            	break;
            }
            case 13:
            {
            	teszt13();
            	break;
            }
            case 14:
            {
            	teszt14();
            	break;
            }
            case 15:
            {
            	teszt15();
            	break;
            }
            case 16:
            {
            	teszt16();
            	break;
            }
        }

        }
    }
    static int menu()
    {
    	 System.out.println("Valassz egy tesztesetet! Gepeld be a futtatni kivant eset szamat, es egy entert!\n" +
                 "1) Eszkimo egy jegtablarol stabil jegtablara lep\n" +
                 "2) Eszkimo egy jegtablarol instabil jegtablara lep\n" +
                 "3) Eszkimo lukas jegtablara lep, vizbe esik (tuleli)\n" +
                 "4) Eszkimo lukas jegtablara lep, vizbe esik (meghal, jatek vege)\n" +
                 "5) Karakter hoviharba kerul\n" +
                 "6) Karakter igluban veszeli at a hovihart\n" +
                 "7) Sarkkutato megnezi a szomszedos mezo teherbirasat\n" +
                 "8) Eszkimo iglut epit\n" +
                 "9) Eszkimo havat takarit\n" +
                 "10) Eszkimo havat lapatol\n" +
                 "11) Eszkimo feltori a jeget\n" +
                 "12) Eszkimo felvesz egy targyat\n" +
                 "13) Eszkimo elfogyaszt egy elelmet\n" +
                 "14) Eszkimo osszerakja a pisztolyt\n" +
                 "15) Eszkimo eldob egy itemet\n" +
                 "16) Eszkimo atad eszkozt a sarkkutatonak\n" + 
                 "0) Kilépés\n");
    	 Scanner in = new Scanner(System.in);
         return in.nextInt();
         
    }
    static void teszt1() 
    {
    	StableIce ice1 = new StableIce();
    	Eskimo c = new Eskimo(ice1);
    	StableIce ice2 = new StableIce();
    	ice1.addNeighbour(ice2);
    	ice2.addNeighbour(ice1);
    	c.move(0);
    }
    static void teszt2()
    {
    	StableIce ice1 = new StableIce();
    	Eskimo c1 = new Eskimo(ice1);
    	UnstableIce ice2 = new UnstableIce(1);
    	ice1.addNeighbour(ice2);
    	ice2.addNeighbour(ice1);
    	Scientist c2 = new Scientist();
    	DivingSuitStrategy dvs = new DivingSuitStrategy();
    	c2.setWaterStrategy(dvs);
    	ice2.addCharacter(c2);
    	c2.setIce(ice2);
    	c1.move(0);
    	
    }
    static void teszt3() 
    {
    	StableIce ice1 = new StableIce();
    	Eskimo c1 = new Eskimo(ice1);
    	HoleIce ice2 = new HoleIce();
    	ice1.addNeighbour(ice2);
    	ice2.addNeighbour(ice1);
    	Scientist c2 = new Scientist(ice1);
    	RopeHelp rh = new RopeHelp();
    	c2.setHelpStrategy(rh);
    	c1.move(0);
    	
    }
    static void teszt4() 
    {
    	StableIce ice1 = new StableIce();
    	Eskimo c1 = new Eskimo(ice1);
    	HoleIce ice2 = new HoleIce();
    	ice1.addNeighbour(ice2);
    	ice2.addNeighbour(ice1);
    	Scientist c2 = new Scientist(ice1);
    	c1.move(0);
    }
    static void teszt5() 
    {
    	StableIce ice = new StableIce();
    	Eskimo c = new Eskimo(ice);
    	try
		{
			ice.stormEffects();
		} catch (Exception e)
		{
		}
    }
    static void teszt6()
    {
    	StableIce ice = new StableIce();
    	Eskimo c = new Eskimo(ice);
    	Igloo igloo = new Igloo();
    	ice.setIglooStrategy(igloo);
    	try
		{
			ice.stormEffects();
		} catch (Exception e)
		{
		}
    }
    static void teszt7()
    {
    	StableIce ice1 = new StableIce();
    	Scientist c = new Scientist(ice1);
    	UnstableIce ice2 = new UnstableIce(3);
    	ice1.addNeighbour(ice2);
    	ice2.addNeighbour(ice1);
    	int teherbiras = 0;
    	try
		{
			teherbiras = c.ability(0);
		} catch (Exception e)
		{
		}
    	if(teherbiras == 3)
    		System.out.println("Elvart: 3, Kapott: " + teherbiras + " Sikeres");
    	else 
    		System.out.println("Elvart: 3, Kapott: " + teherbiras + " Sikertelen");
    }
    static void teszt8() 
    {
    	StableIce ice = new StableIce();
    	ice.incSnow();
    	Eskimo c = new Eskimo(ice);
    	c.ability();
    }
    static void teszt9()
    {
    	StableIce ice = new StableIce();
    	ice.incSnow();
    	Eskimo c = new Eskimo(ice);
    	c.dig();
    }
    static void teszt10()
    {
    	StableIce ice = new StableIce();
    	ice.incSnow();
    	ice.incSnow();
    	Eskimo c = new Eskimo(ice);
    	ShovelDig sDig = new ShovelDig();
    	c.setDigStrategy(sDig);
    	c.dig();
    }
    
    static void teszt11()
    {
    	StableIce ice = new StableIce();
    	Eskimo c = new Eskimo(ice);
    	DivingSuit d = new DivingSuit();
    	Rope r = new Rope();
    	ice.addItem(d);
    	ice.addItem(r);
    	c.breakIce();
    }
    static void teszt12()
    {
    	StableIce ice = new StableIce();
    	Eskimo c = new Eskimo(ice);
    	DivingSuit d = new DivingSuit();
    	c.breakIce();
    	c.itemPickup(0);
    }
    static void teszt13()
    {
    	Eskimo c = new Eskimo();
    	Food f = new Food();
    	c.addItem(f);
    	c.warmthDec();
    	c.getItem(0).use();
    }
    static void teszt14()
    {
    	StableIce ice = new StableIce();
    	Eskimo c = new Eskimo(ice);
    	Cartridge cartridge = new Cartridge();
    	Flare flare = new Flare();
    	FlareGun flareGun = new FlareGun();
    	c.addItem(cartridge);
    	c.addItem(flareGun);
    	c.addItem(flare);
    	c.assembleGun(cartridge, flare, flareGun);
    }
    static void teszt15()
    {
    	StableIce ice = new StableIce();
    	Eskimo c = new Eskimo(ice);
    	DivingSuit ds = new DivingSuit();
    	c.addItem(ds);
    	c.itemDiscard(ds);
    }
    static void teszt16()
    {
    	Eskimo c1 = new Eskimo();
    	Scientist c2 = new Scientist();
    	DivingSuit ds = new DivingSuit();
    	c1.addItem(ds);
    	c1.itemGive(c2, ds);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}