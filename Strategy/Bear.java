package Strategy;

import Ice.*;

public class Bear implements BearStrategy{
    /**
     *
     * @param i
     */
    public void bearHere(Ice i){
        i.stepOn();
    }
}
