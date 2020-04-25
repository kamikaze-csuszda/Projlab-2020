package Strategy;


import Ice.*;


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
		System.out.println("<--");
	}
	@Override
	public void stepOn(Ice i) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void turnEnd(Ice i) {
		// TODO Auto-generated method stub
		
	}
}
