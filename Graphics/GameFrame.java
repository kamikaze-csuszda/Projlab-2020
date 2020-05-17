package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameFrame extends MyFrame implements UpdateInterface
{

	public GameFrame()
	{
		super("Jegmezo - Jatek");
		setLayout(new BorderLayout());
		setSize(1200, 800);
		
		JPanel jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(200, 800));
		JPanel jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(200, 800));
		jp1.setBackground(Color.cyan);
		jp2.setBackground(Color.cyan);
		add(jp1, BorderLayout.EAST);
		add(jp2, BorderLayout.WEST);
		setBackground(Color.cyan);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
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
