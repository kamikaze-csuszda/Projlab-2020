package Graphics;

import Characters.PolarBear;

public class BearView implements UpdateInterface
{
	private PolarBear polarBear;
	private IceView iceView;
	public BearView(IceView i, PolarBear pb) {
		polarBear = pb;
		iceView = i;
	}
	public void setPolarBear(PolarBear pb) {
		polarBear = pb;
	}
	public IceView getIceView() {
		return iceView;
	}
	@Override
	public void update()
	{
		
	}
	

}
