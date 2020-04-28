package Strategy;


import Ice.*;
import Main.Game;


public class TentStrategy implements IglooStrategy
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public TentStrategy() {		
	}
	
	public void stormEffects(Ice i) throws Exception
	{
		i.destroyIgloo();
		i.incSnow();
	}
	@Override
	public void stepOn(Ice i) {
		i.destroyIgloo();
		int chars = i.getCharNum();
		if (chars > 0)
			Game.getInstance().loseGame(); 
	}
	@Override
	public void turnEnd(Ice i) {
		i.destroyIgloo();
		
	}
}
