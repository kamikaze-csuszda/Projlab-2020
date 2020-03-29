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
/**
 * ice1 StabilJegtabla letrehozasa
 * Eszkimo letrehozasa aki at ice1 tablan all
 * ice2 StabilJegtabla letrehozasa
 * ice2 StabilJegtabla letrehozasa
 * ice1 es ice2 jegtablak szomszedda tetele
 * Eszkimo mozgatasa
 */
    static void teszt1() 
    {
    	StableIce ice1 = new StableIce(); 		
    	Eskimo c = new Eskimo(ice1); 			
    	StableIce ice2 = new StableIce();		
    	ice1.addNeighbour(ice2);				
    	ice2.addNeighbour(ice1);
    	c.move(0);								
    }
    /**
     * ice1 StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice1 tablan all
     * ice2 UnstableIce letrehozasa
     * ice1 es ice2 jegtablak szomszedda tetele
     * Scientist letrehozasa
     * DivingSuitStrategy letrehozasa
     * Scitentist kap DivingSuit-ot
     * Scientist az ice2-re kerul
     * Eszkimo ice2-re mozog
     */
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
    /**
     * ice1 StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice1 tablan all
     * ice2 LukasJegtabla letrehozasa
     * ice1 es ice2 jegtablak szomszedda tetele
     * Scientist letrehozasa aki at ice1 tablan all
     * Kotel letrehozasa
     * Scientist kap kotelet, amivel ki tud mast menteni
     * Eszkimo atmozog a ice2-re
     */
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
    /**
     * ice1 StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice1 tablan all
     * ice2 LukasJegtabla letrehozasa
     * ice1 es ice2 jegtablak szomszedda tetele
     * Scientist letrehozasa aki at ice1 tablan all
     * /Eszkimo atmozog a ice2-re
     */
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
    /**
     * ice StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice tablan all
     * Vihar hatasa eri az ice-ot
     */
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
    /**
     * ice StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice tablan all
     * Igloo letrehozasa es ice-hoz valo hozzaadas 
     * Vihar hatasa eri az ice-ot
     */
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
    /**
     * ice1 StabilJegtabla letrehozasa
     * Scientist letrehozasa aki at ice1 tablan all
     * ice2 UnstableIce letrehozasa
     * ice1 es ice2 jegtablak szomszedda tetele
     * ellenortes miatti valtozo letrehozas
     * Scientist kepesseg hasznalata
     * kapott eredmeny ellenorzese
     */
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
    /**
     * ice StabilJegtabla letrehozasa
     * ho szint novelese ice-on
     * Eszkimo letrehozasa aki at ice tablan all
     * Eszkimo kepessegenek hasznalata
     */
    static void teszt8() 
    {
    	StableIce ice = new StableIce();		
    	ice.incSnow();							
    	Eskimo c = new Eskimo(ice);				
    	c.ability();							
    }
    /**
     * ice StabilJegtabla letrehozasa
     * ho szint novelese ice-on
     * Eszkimo letrehozasa aki at ice tablan all
     * Eszkimo as
     */
    static void teszt9()
    {
    	StableIce ice = new StableIce();		
    	ice.incSnow();							
    	Eskimo c = new Eskimo(ice);				
    	c.dig();								
    }
    /**
     * ice StabilJegtabla letrehozasa
     * ho szint novelese ice-on
     * ho szint novelese ice-on
     * Eszkimo letrehozasa aki at ice tablan all
     * Eszkimo shovelt kap es a strategy ennek megfeleloen valtozik 
     * Eszkimo as
     */
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
    /**
     * ice StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice tablan all
     * DivinSuit letrehozasa
     * Rope letrehozasa
     * Ice megkapja a DivingSuit-ot és rope-ot
     * Eszkimo jeget tor
     */
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
    /**
     * ice StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice tablan all
     * Item letrehozasa ami most egy DivingSuit
     * DivingSuit hozzaadasa a StabilJegtablahoz
     * Eszkimoval jeg feltorese
     * Eszkimoval Item felvetele
     */
    static void teszt12()
    {
    	StableIce ice = new StableIce();		
    	Eskimo c = new Eskimo(ice);				
    	DivingSuit d = new DivingSuit();		
    	ice.addItem(d);							
    	c.breakIce();							
    	c.itemPickup(0);						
    }
    /**
     * Eszkimo letrehozasa 
     * Etel letrehozasa
     * Eszkimo megkapja az Etelt
     * Eszkimo testhojenek csokkentese, hogy ne legyen maxon
     * Etel hasznalata
     */
    static void teszt13()
    {
    	Eskimo c = new Eskimo();				
    	Food f = new Food();					
    	c.addItem(f);							
    	c.warmthDec();							
    	c.getItem(0).use();						
    }
    /**
     * ice StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice tablan all
     * Patron letrehozasa
     * Jelzofeny letrehozasa
     * Jelzopisztoly letrehozasa
     * Patron hozzaadasa az Eszkimohoz
     * Jelzofeny hozzaadasa az Eszkimohoz
     * Jelzopisztoly hozzaadasa az Eszkimohoz
     * Pisztoly osszeszerelese
     */
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
    /**
     * ice StabilJegtabla letrehozasa
     * Eszkimo letrehozasa aki at ice tablan all
     * DivinSuit letrehozasa
     * Eszkimo megkapja a DivingSuit-ot 
     * Eszkimo ledobja a DivingSiut-ot
     */
    static void teszt15()
    {
    	StableIce ice = new StableIce();		
    	Eskimo c = new Eskimo(ice);				
    	DivingSuit ds = new DivingSuit();		
    	c.addItem(ds);							
    	c.itemDiscard(ds);						
    }
    /**
     * Eszkimo letrehozasa 
     * Scientist letrehozasa 
     * DivinSuit letrehozasa
     * Eszkimo megkapja a DivingSuit-ot 
     * Eszkimo atadja a DivingSiut-ot a Scientistnek 
     */
    static void teszt16()
    {
    	Eskimo c1 = new Eskimo();				
    	Scientist c2 = new Scientist();			
    	DivingSuit ds = new DivingSuit();		
    	c1.addItem(ds);							
    	c1.itemGive(c2, ds);					
    }
    
    
    
    
    
    
    
    
    
    
    
    
}