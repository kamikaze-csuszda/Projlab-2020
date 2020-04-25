package Strategy;


import Ice.*;
import Main.Game;


public class TentStrategy implements IglooStrategy
{
	/**
	 * Alapertelmezett konstruktor.
	 */
	public TentStrategy() {
		
		System.out.println("--> Igloo()");
		System.out.println("<--");
		
	}
	
	public void stormEffects(Ice i) throws Exception
	{
		System.out.println("--> stormEffects(i)");
		i.destroyIgloo();
		i.incSnow();
		System.out.println("<--");
	}
	@Override
	public void stepOn(Ice i) {
		i.destroyIgloo();
		int chars = i.getCharNum();
		if (chars > 0)
			Game.getInstance().loseGame();  //dunno hogy hivatkozzuk a game-et, TODO
	}
	@Override
	public void turnEnd(Ice i) {
		i.destroyIgloo();
		
	}
}
