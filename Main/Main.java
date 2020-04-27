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
import Item.Shovel;
import Strategy.DivingSuitStrategy;
import Strategy.Igloo;
import Strategy.RopeHelp;
import Strategy.ShovelDig;

public class Main{

    public static void main(String[] args)
    {
    	Commands com = new Commands();
    	try
		{
			com.start();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    
}