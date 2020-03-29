package Main;


import java.util.Scanner;

import Characters.Eskimo;
import Ice.StableIce;

public class Main{

    public static void main(String[] args)
    {
        System.out.println("Válassz egy tesztesetet! Gépeld be a futtatni kívánt eset számát, és egy entert!\n" +
                "1) Eszkimó egy jégtábláról stabil jégtáblára lép\n" +
                "2) Eszkimó egy jégtábláról instabil jégtáblára lép\n" +
                "3) Eszkimó lukas jégtáblára lép, vízbe esik (túléli)\n" +
                "4) Eszkimó lukas jégtáblára lép, vízbe esik (meghal, játék vége)\n" +
                "5) Karakter hóviharba kerül\n" +
                "6) Karakter igluban vészeli át a hóvihart\n" +
                "7) Sarkkutató megnézi a szomszédos mező teherbírását\n" +
                "8) Eszkimó iglut épít\n" +
                "9) Eszkimó havat takarít\n" +
                "10) Eszkimó havat lapátol\n" +
                "11) Eszkimó feltöri a jeget\n" +
                "12) Eszkimó felvesz egy tárgyat\n" +
                "13) Eszkimó elfogyaszt egy élelmet\n" +
                "14) Eszkimó összerakja a pisztolyt\n" +
                "15) Eszkimó összerakja a pisztolyt\n" +
                "16) Eszkimó átad eszközt a sarkkutatónak\n");
        Scanner in = new Scanner(System.in);
        int ans = in.nextInt();
        System.out.println("Bemenet: " + ans);
        switch (ans)
        {
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