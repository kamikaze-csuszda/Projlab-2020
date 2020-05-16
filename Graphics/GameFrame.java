package Graphics;

import java.awt.BorderLayout;

public class GameFrame extends MyFrame implements UpdateInterface
{

	public GameFrame()
	{
		super();
		setLayout(new BorderLayout());
		
	}
	public void addMapView(MapView m) {
		add(m, BorderLayout.CENTER);
	}
	public void addIcePanel(IceInfoPanel ip) {
		add(ip, BorderLayout.EAST);
	}
	public void addCharPanel(CharactersInfoPanel cp) {
		add(cp, BorderLayout.WEST);
	}
	@Override
	public void update()
	{
		
	}

}
