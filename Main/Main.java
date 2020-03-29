package Main;


import java.util.Scanner;

import Characters.Eskimo;
import Ice.StableIce;

public class Main{

    public static void main(String[] args)
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
                "16) Eszkimó atad eszkozt a sarkkutatonak\n");
        Scanner in = new Scanner(System.in);
        int ans = in.nextInt();
        System.out.println("Bemenet: " + ans);
        switch (ans)
        {
        	//szerintem ez nem lesz így jó - V
            case 1:
            {
            	System.out.println("-->Main.CreateObjectST1()");
            	System.out.println("<--");
            	StableIce st1 = new StableIce();
            	System.out.println("-->Main.CreateObjectE(st1)");
            	System.out.println("<--");
            	Eskimo e = new Eskimo(st1);
            	System.out.println("-->Main.CreateObjectST2()");
            	System.out.println("<--");
            	StableIce st2 = new StableIce();
            	System.out.println("st1.SetAssociation(st2)");
            	System.out.println("<--");
            	st1.addNeighbour(st2);
            	System.out.println("st2.SetAssociation(st1)");
            	System.out.println("<--");
            	st2.addNeighbour(st1);
            	System.out.println("-->e.move(0)");
            	e.move(0);
            	System.out.println("<--");
            	break;
            }

        }
    }
}