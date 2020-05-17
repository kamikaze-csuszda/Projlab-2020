package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Characters.Character;
import javax.swing.*;

import Main.Game;

public class GameFrame extends MyFrame implements UpdateInterface
{
	public JMenuBar menuBar;
	public JMenu characterMenu, actionMenu;
	public ArrayList<JRadioButtonMenuItem> characters;
	public JMenuItem warmup, itemDrop, shovel, eat, assemble;
	public JRadioButtonMenuItem move, itemGive, itemPickup, ability;
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
		characters = new ArrayList<JRadioButtonMenuItem>();
		menuBar = new JMenuBar();
		characterMenu = new JMenu("Karakterek");
		actionMenu = new JMenu("Akciok");
		ButtonGroup chars = new ButtonGroup();
		for(String key : Game.getInstance().getObjects().keySet()){
			if (Game.getInstance().getObjects().get(key) instanceof Character) {
				JRadioButtonMenuItem jb =new JRadioButtonMenuItem(key);
				characters.add(jb);
				chars.add(jb);
			}
				
		}
		characters.get(0).setSelected(true);
		
		for (JMenuItem item : characters)
		{
			characterMenu.add(item);
		}
		ButtonGroup actions = new ButtonGroup();
		move = new JRadioButtonMenuItem("Mozgas");
		warmup = new JMenuItem("Melegedes");
		warmup.setActionCommand("warmup");
		eat = new JMenuItem("Eves");
		eat.setActionCommand("eat");
		itemGive = new JRadioButtonMenuItem("Eszkoz atadas");
		itemPickup = new JRadioButtonMenuItem("Eszkoz felvetel");
		itemDrop = new JMenuItem("Eszkoz eldobasa");
		itemDrop.setActionCommand("itemDrop");
		shovel = new JMenuItem("Ho eltakaritas");
		shovel.setActionCommand("shovel");
		assemble = new JMenuItem("Fegyver osszerakasa");
		assemble.setActionCommand("assemble");
		
		ability = new JRadioButtonMenuItem("Kepesseg hasznalata");
		JMenuItem cancel = new JMenuItem("Megse");
		actionMenu.add(warmup);
		actionMenu.add(itemDrop);
		actionMenu.add(shovel);
		actionMenu.add(eat);
		actionMenu.add(assemble);
		actionMenu.addSeparator();
		actionMenu.add(move);
		actions.add(move);
		actionMenu.add(itemGive);
		actions.add(itemGive);
		actionMenu.add(itemPickup);
		actions.add(itemPickup);
		actionMenu.add(ability);
		actions.add(ability);
		actionMenu.add(cancel);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				actions.clearSelection();
			}
		});
		menuBar.add(characterMenu);
		menuBar.add(actionMenu);
		
		setJMenuBar(menuBar);
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
