package Main;


import java.util.Scanner;

import Characters.Eskimo;
import Characters.Scientist;
import Ice.StableIce;
import Ice.UnstableIce;

public class Main{

    public static void main(String[] args)
    {
    	menu();
        Scanner in = new Scanner(System.in);
        int ans = in.nextInt();
        System.out.println("Bemenet: " + ans);
        switch (ans)
        {
        	//szerintem ez nem lesz így jó - V
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
            	break;
            }
            case 4:
            {
            	break;
            }
            case 5:
            {
            	break;
            }
            case 6:
            {
            	break;
            }
            case 7:
            {
            	break;
            }
            case 8:
            {
            	break;
            }
            case 9:
            {
            	break;
            }
            case 10:
            {
            	break;
            }
            case 11:
            {
            	break;
            }
            case 12:
            {
            	break;
            }
            case 13:
            {
            	break;
            }
            case 14:
            {
            	break;
            }
            case 15:
            {
            	break;
            }
            case 16:
            {
            	break;
            }
            

        }
    }
    static void menu()
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
                 "16) Eszkimo atad eszkozt a sarkkutatonak\n");
    }
    static void teszt1() 
    {
    	StableIce st1 = new StableIce();
    	Eskimo e = new Eskimo(st1);
    	StableIce st2 = new StableIce();
    	st1.addNeighbour(st2);
    	st2.addNeighbour(st1);
    	e.move(0);
    }
    static void teszt2()
    {
    	StableIce st = new StableIce();
    	Eskimo e = new Eskimo(st);
    	UnstableIce ui = new UnstableIce(1);
    	st.addNeighbour(ui);
    	ui.addNeighbour(st);
    	Scientist sc = new Scientist();
    	ui.addCharacter(sc);
    	sc.setIce(ui);
    	e.move(0);
    	
    }
}