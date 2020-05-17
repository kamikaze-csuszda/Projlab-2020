package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class GameFrame extends MyFrame implements UpdateInterface
{
	private JMenuBar menuBar;
	private JMenu characterMenu, actionMenu;
	private ArrayList<JMenuItem> characters;
	private JMenuItem move, warmup, itemGive, itemPickup, itemDrop, shovel;
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

		menuBar = new JMenuBar();
		characterMenu = new JMenu("Karakterek");
		actionMenu = new JMenu("Akciok");

		for(String key : Game.getInstance().getObjects().keySet()){
			if (Game.getInstance().getObjects().get(key) instanceof Character)
				characters.add(new JMenuItem(key));
		}
		move = new JMenuItem("Mozgas");
		warmup = new JMenuItem("Melegedes");
		itemGive = new JMenuItem("Eszkoz atadas");
		itemPickup = new JMenuItem("Eszkoz felvetel");
		itemDrop = new JMenuItem("Eszkoz eldobasa");
		shovel = new JMenuItem("Ho eltakaritas");

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
