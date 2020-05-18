package Graphics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import Ice.Ice;
import Item.Food;
import Item.Item;
import Main.Game;
import Characters.Character;
import Characters.Eskimo;
import Characters.Scientist;

import javax.swing.*;

public class ViewController implements UpdateInterface
{
	public HashMap<Character, CharacterView> characterMap;
	public HashMap<Ice, IceView> iceMap;
	private ArrayList<MyFrame> frames;
	public MapView mapView;
	public Ice selectedIce;
	public Character selectedCharacter;
	CommandActionListener cListener = new CommandActionListener();
	private MenuFrame mf;
	GameFrame gf;
	public ViewController() {
		characterMap = new HashMap<Character, CharacterView>();
		iceMap = new HashMap<Ice, IceView>();
		frames = new ArrayList<MyFrame>();
		mapView = new MapView();
		frames.add(new MenuFrame());
		frames.get(0).setActive(true);
		
		initController();
		mf = (MenuFrame)frames.get(0);
		mf.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		mf.startButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				initGame1();
				System.out.println("Game Init Done");
			}
		});
		
	}
	public void initGame1() {
		
		Game.getInstance().initGame1();
		try
		{
			mapView.init1();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frames.add(new GameFrame());
		gf = (GameFrame)frames.get(1);
		mf.setActive(false);
		gf.setActive(true);
		mf.setVisible(false);
		gf.setVisible(true);
		gf.add(mapView);
		mapView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(gf.move.isSelected())
				{
					int direction = 0;
					for (IceView item : mapView.getIceView())
					{
						Rectangle rect = new Rectangle(item.getPos().getX(), item.getPos().getY(), item.getPos().getR(), item.getPos().getR());
						if(rect.contains(e.getX(), e.getY())) 
						{
							if(selectedCharacter.getIce().getNeighbourArray().contains(item.getIce()))
							{
								direction = selectedCharacter.getIce().getNeighbourArray().indexOf(item.getIce());
								mapView.getIceView(selectedCharacter.getIce()).removeCharacterView(mapView.getCharacterView(selectedCharacter));
								selectedCharacter.move(direction);
								mapView.getCharacterView(selectedCharacter).setIceView(mapView.getIceView(selectedCharacter.getIce()));
								mapView.getIceView(selectedCharacter.getIce()).addCharacterView(mapView.getCharacterView(selectedCharacter));
							}
						}
					}
					if(gf.isVisible())
						update();
					
				}
				else if(gf.itemGive.isSelected())
				{
					int index=999;
					ArrayList<Position> grid = new ArrayList<Position>();
					
						Rectangle rect = new Rectangle(mapView.getIceView(selectedCharacter.getIce()).getPos().getX(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getY(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getR(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getR());
						if(rect.contains(e.getX(), e.getY())) 
						{
							for(int i = 0; i < 3; i++)
							{
								for(int j = 0; j < 3; j++) {
									grid.add(new Position((j*46) + mapView.getIceView(selectedCharacter.getIce()).getPos().getX(), 
											(i*46) + mapView.getIceView(selectedCharacter.getIce()).getPos().getY(), 46));
								}
							}
							for(int i = 0; i < grid.size(); i++) {
								Rectangle rect2 = new Rectangle(grid.get(i).getX(),grid.get(i).getY(), 46, 46);
								if(rect2.contains(e.getX(), e.getY()))
									index = i;
							}
						}
					if(selectedCharacter.getIce().getCharNum() >= index)
					{
						try
						{
							if(!selectedCharacter.equals(selectedCharacter.getIce().getCharacter(index)))
							{
								String[] choices = new String[selectedCharacter.getEquipment().size() + 1];
								String s = "none";
								for(int i = 0; i < choices.length - 1; i++)
								{
									choices[i] = Game.getInstance().findName(selectedCharacter.getEquipment().get(i));
								}
								choices[choices.length-1] = "none";
								s = (String)JOptionPane.showInputDialog(gf, "Melyiket adod at?", "Atadas", JOptionPane.PLAIN_MESSAGE, null,choices, "none");
								if(!s.equals("none"))
									selectedCharacter.itemGive(selectedCharacter.getIce().getCharacter(index), Item.class.cast(Game.getInstance().getObjects().get(s)));
							}
						} catch (Exception e1){}
					}
					update();
				}
				else if(gf.itemPickup.isSelected()) 
				{
					int index=999;
					ArrayList<Position> grid = new ArrayList<Position>();
					
						Rectangle rect = new Rectangle(mapView.getIceView(selectedCharacter.getIce()).getPos().getX(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getY(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getR(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getR());
						if(rect.contains(e.getX(), e.getY())) 
						{
							for(int i = 0; i < 3; i++)
							{
								for(int j = 0; j < 3; j++) {
									grid.add(new Position((j*46) + mapView.getIceView(selectedCharacter.getIce()).getPos().getX(), 
											(i*46) + mapView.getIceView(selectedCharacter.getIce()).getPos().getY(), 46));
								}
							}
							for(int i = 0; i < grid.size(); i++) {
								Rectangle rect2 = new Rectangle(grid.get(i).getX(),grid.get(i).getY(), 46, 46);
								if(rect2.contains(e.getX(), e.getY()))
									index = i;
							}
						}
						index -= selectedCharacter.getIce().getCharNum();
						if(selectedCharacter.getIce().getItemArray().size() >= index)
							selectedCharacter.itemPickup(index);
						update();
				}
				else if(gf.ability.isSelected()) 
				{
					if(selectedCharacter instanceof Scientist)
					{
						for (IceView item : mapView.getIceView(selectedCharacter.getIce()).getNeighbours())
						{
							Rectangle rect = new Rectangle(item.getPos().getX(), item.getPos().getY(), item.getPos().getR(), item.getPos().getR());
							if(rect.contains(e.getX(), e.getY())) 
							{
								
								try
								{
									if(Scientist.class.cast(selectedCharacter).ability(selectedCharacter.getIce().getNeighbourArray().indexOf(item.getIce())) != -444)
										item.explored = true;
								} catch (Exception e1)
								{
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					else
					{
						Rectangle rect = new Rectangle(mapView.getIceView(selectedCharacter.getIce()).getPos().getX(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getY(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getR(), 
								mapView.getIceView(selectedCharacter.getIce()).getPos().getR());
						if(rect.contains(e.getX(), e.getY())) 
						{
							Eskimo.class.cast(selectedCharacter).ability();
						}
					}
					update();
				}
				else {
					for (IceView item : mapView.getIceView())
					{
						Rectangle rect = new Rectangle(item.getPos().getX(), item.getPos().getY(), item.getPos().getR(), item.getPos().getR());
						if(rect.contains(e.getX(), e.getY())) 
						{
							selectedIce = item.getIce();
							gf.icePanel.setIce(mapView.getIceView(selectedIce));
						}
					}
				}
			}
		});
		for (String key : Game.getInstance().getObjects().keySet())
		{
			if(Game.getInstance().getObjects().get(key) instanceof Character)
			{
				selectedCharacter = Character.class.cast(Game.getInstance().getObjects().get(key));
				break;
			}
		}
		gf.addCharPanel(new CharacterInfoPanel(selectedCharacter));
		gf.addIcePanel(new IceInfoPanel(mapView.getIceView(Ice.class.cast(Game.getInstance().getMapPieces().get(0)))));
		
		gf.itemDrop.addActionListener(cListener);
		gf.turnend.addActionListener(cListener);
		gf.shovel.addActionListener(cListener);
		gf.eat.addActionListener(cListener);
		gf.breakIce.addActionListener(cListener);
		gf.assemble.addActionListener(cListener);
		gf.warmup.addActionListener(cListener);
		gf.use.addActionListener(cListener);
		gf.removeGunPart.addActionListener(cListener);
		for (JRadioButtonMenuItem item : gf.characters)
		{
			item.addActionListener(new CharacterSelectionActionListener());
		}
	}
	public class CharacterSelectionActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			for (JRadioButtonMenuItem item : gf.characters)
			{
				if(item.isSelected())
					selectedCharacter = Character.class.cast(Game.getInstance().getObjects().get(item.getText()));
			}
			gf.characterPanel.setCharacter(selectedCharacter);
			
		}
	}
	public class CommandActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("itemDrop")) 
			{
				String[] choices = new String[selectedCharacter.getEquipment().size() + 1];
				String s = "none";
				for(int i = 0; i < choices.length - 1; i++)
				{
					try
					{
						choices[i] = Game.getInstance().findName(selectedCharacter.getEquipment().get(i));
					} catch (Exception e1){}
				}
				choices[choices.length-1] = "none";
				s = (String)JOptionPane.showInputDialog(gf, "Melyiket dobod el?", "Eldobas", JOptionPane.PLAIN_MESSAGE, null,choices, "none");
				int index = 10;
				for(int i = 0; i < choices.length; i++)
					if(s.equals(choices[i]))
						index = i;
				if(!s.equals("none"))
					selectedCharacter.itemDiscard(selectedCharacter.getEquipment().get(index));
					update();
			}
			else if(e.getActionCommand().equals("turnend")) {
				try
				{
					Game.getInstance().turnend();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				update();
			}
			else if(e.getActionCommand().equals("shovel")) {
				selectedCharacter.dig();
				update();
			}
			else if(e.getActionCommand().equals("eat")) {
				for(int i = 0; i < selectedCharacter.getEquipment().size(); i++)
					if(selectedCharacter.getEquipment().get(i) instanceof Food)
						selectedCharacter.getEquipment().get(i).use();
				update();
			}
			else if(e.getActionCommand().equals("breakIce")) {
				selectedCharacter.breakIce();
				update();
			}
			else if(e.getActionCommand().equals("assemble")) {
				selectedCharacter.assembleGun();
				update();
			}
			else if(e.getActionCommand().equals("warmup")) {
				selectedCharacter.warmup();
				update();
			}
			else if(e.getActionCommand().equals("use")) {
				String[] choices = new String[selectedCharacter.getEquipment().size() + 1];
				String s = "none";
				for(int i = 0; i < choices.length - 1; i++)
				{
					try
					{
						choices[i] = Game.getInstance().findName(selectedCharacter.getEquipment().get(i));
					} catch (Exception e1){}
				}
				choices[choices.length-1] = "none";
				s = (String)JOptionPane.showInputDialog(gf, "Melyiket hasznalod?", "Hasznalat", JOptionPane.PLAIN_MESSAGE, null,choices, "none");
				int index = 10;
				for(int i = 0; i < choices.length; i++)
					if(s.equals(choices[i]))
						index = i;
				if(!s.equals("none"))
					selectedCharacter.itemUse(index);
				update();
			}
			else if(e.getActionCommand().equals("remove")) {
				String[] choices = new String[selectedCharacter.getGunParts().size() + 1];
				String s = "none";
				for(int i = 0; i < choices.length - 1; i++)
				{
					try
					{
						choices[i] = Game.getInstance().findName(selectedCharacter.getGunParts().get(i));
					} catch (Exception e1){}
				}
				choices[choices.length-1] = "none";
				s = (String)JOptionPane.showInputDialog(gf, "Melyiket rakod vissza?", "Eltavolitas", JOptionPane.PLAIN_MESSAGE, null,choices, "none");
				int index = 10;
				for(int i = 0; i < choices.length; i++)
					if(s.equals(choices[i]))
						index = i;
				if(!s.equals("none"))
					selectedCharacter.removeGunpart(selectedCharacter.getGunParts().get(index));
				update();
			}
		}
	}
	public void initController() {
		frames.get(0).setVisible(true);
	}
	@Override
	public void update()
	{
		if(Game.getInstance().getLost())
		{
			JOptionPane.showMessageDialog(gf, "Elvesztetted a jatekot!", "Game Lost!", JOptionPane.ERROR_MESSAGE);
			gf.setVisible(false);
			gf.remove(gf.characterPanel);
			gf.remove(gf.icePanel);
			gf.remove(gf.menuBar);
			gf.itemDrop.removeActionListener(cListener);
			gf.turnend.removeActionListener(cListener);
			gf.shovel.removeActionListener(cListener);
			gf.eat.removeActionListener(cListener);
			gf.breakIce.removeActionListener(cListener);
			gf.assemble.removeActionListener(cListener);
			gf.warmup.removeActionListener(cListener);
			gf.use.removeActionListener(cListener);
			gf.removeGunPart.removeActionListener(cListener);
			for(JRadioButtonMenuItem item : gf.characters)
			{
				item.removeActionListener(item.getActionListeners()[0]);
			}
			mapView.removeMouseListener(mapView.getMouseListeners()[0]);
			mf.setVisible(true);
			gf.dispose();
			Game.getInstance().clear();
			mapView.clear();
		}
		else if(Game.getInstance().getWon())
		{
			JOptionPane.showMessageDialog(gf, "Megnyerted a jatekot!", "Game Won!", JOptionPane.PLAIN_MESSAGE);
			gf.setVisible(false);
			mf.setVisible(true);
			gf.itemDrop.removeActionListener(cListener);
			gf.turnend.removeActionListener(cListener);
			gf.shovel.removeActionListener(cListener);
			gf.eat.removeActionListener(cListener);
			gf.breakIce.removeActionListener(cListener);
			gf.assemble.removeActionListener(cListener);
			gf.warmup.removeActionListener(cListener);
			gf.use.removeActionListener(cListener);
			gf.removeGunPart.removeActionListener(cListener);
			for(JRadioButtonMenuItem item : gf.characters)
			{
				item.removeActionListener(item.getActionListeners()[0]);
			}
			mapView.removeMouseListener(mapView.getMouseListeners()[0]);
			gf.remove(gf.characterPanel);
			gf.remove(gf.icePanel);
			gf.remove(gf.menuBar);
			gf.dispose();
			Game.getInstance().clear();
			mapView.clear();
		}
		else {
		gf.update();
		mapView.update();
		}
	}
}
