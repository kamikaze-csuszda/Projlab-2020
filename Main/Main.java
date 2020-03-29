package Main;


import java.util.Scanner;

import Characters.Eskimo;
import Ice.StableIce;

public class Main{

    public static void main(String[] args)
    {
       
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
    	
    }
    static void teszt1() 
    {
    	System.out.println("-->Main.StableIce()");
    	System.out.println("<--");
    	StableIce st1 = new StableIce();
    	System.out.println("-->Main.Eskimo(st1)");
    	System.out.println("<--");
    	Eskimo e = new Eskimo(st1);
    	System.out.println("-->Main.StableIce()");
    	System.out.println("<--");
    	StableIce st2 = new StableIce();
    	System.out.println("st1.addNeighbour(st2)");
    	System.out.println("<--");
    	st1.addNeighbour(st2);
    	System.out.println("st2.addNeighbour(st1)");
    	System.out.println("<--");
    	st2.addNeighbour(st1);
    	System.out.println("-->e.move(0)");
    	e.move(0);
    	System.out.println("<--");
    }
}