package Strategy;

import Ice.Ice;

public class NoBear implements BearStrategy{
    /**
     * Mivel nincs a mezon medve, igy nem csinal semmit.
     * @param i a jegmezo amit nezunk
     */
    public void bearHere(Ice i){

    }
}
