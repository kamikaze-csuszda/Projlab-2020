package Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Characters.Character;

import javax.swing.JPanel;

import Ice.HoleIce;
import Ice.Ice;
import Ice.StableIce;
import Ice.UnstableIce;
import Item.Item;
import Main.Game;

public class MapView extends JPanel implements UpdateInterface
{
	private static final long serialVersionUID = 1L;
	private ArrayList<ItemView> itemView;
	private ArrayList<CharacterView> characterView;
	private ArrayList<IceView> iceView;
	private BearView bearView;
	private GameFrame gameFrame;
	public MapView() {
		super();
		itemView = new ArrayList<ItemView>();
		characterView = new ArrayList<CharacterView>();
		iceView = new ArrayList<IceView>();
		bearView = null;
		setBackground(new Color(138, 210, 255));
		setOpaque(true);
		setPreferredSize(new Dimension(800, 800));
	}

	public MapView(GameFrame g)
	{
		this();
		gameFrame = g;
	}
	public MapView(GameFrame g, BearView b)
	{
		this(g);
		bearView = b;
	}
	public ArrayList<ItemView> getItemView() {
		return itemView;
	}
	public ArrayList<CharacterView> getCharacterView() {
		return characterView;
	}
	public BearView getBearView() {
		return bearView;
	}
	public ArrayList<IceView> getIceView() {
		return iceView;
	}
	public GameFrame getGameFrame() {
		return gameFrame;
	}
	public void addIceView(IceView iv) {
		iceView.add(iv);
	}
	public void removeIceView(IceView iv) {
		iceView.remove(iv);
	}
	public void addCharacterView(CharacterView cv) {
		characterView.add(cv);
	}
	public void removeCharacterView(CharacterView cv) {
		characterView.remove(cv);
	}
	public void setBearView(BearView bv) {
		bearView = bv;
	}
	public void removeBearView() {
		bearView = null;
	}
	public void addItemView(ItemView iv) {
		itemView.add(iv);
	}
	public void removeItemView(ItemView iv) {
		itemView.remove(iv);
	}
	public IceView findIceView(Ice ice) {
		for(int i = 0; i < iceView.size(); i++) {
			if(iceView.get(i).getIce().equals(ice))
				return iceView.get(i);
		}
		return null;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ArrayList<Position> grid = new ArrayList<Position>();
		Graphics2D g2 = (Graphics2D)g;
		for (IceView item : iceView)
		{
			g2.setColor(Color.PINK);
			g2.setStroke(new BasicStroke(5));
			for (IceView iv : item.getNeighbours())
			{
				g2.drawLine(item.getPos().getX() + (item.getPos().getR()/2) , item.getPos().getY() + (item.getPos().getR()/2), iv.getPos().getX() + (iv.getPos().getR()/2) , iv.getPos().getY() + (iv.getPos().getR()/2));
			}
			repaint(0, 0, getWidth(), getHeight());
		}
		for (IceView item : iceView)
		{
			grid.clear();
			int gridsTaken = 0;
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++) {
					grid.add(new Position((j*46) + item.getPos().getX(), (i*46) + item.getPos().getY(), 46));
				}
			}
			if(item instanceof StableView)
				g.setColor(Color.white);
			else if(item instanceof UnstableView)
				g.setColor(Color.gray);
			else if(item instanceof HoleView)
				g.setColor(Color.red);
			
			g.fillOval(item.getPos().getX(), item.getPos().getY(), item.getPos().getR(), item.getPos().getR());
			for (CharacterView chv: item.getCharView())
			{
				chv.paint(g, grid.get(gridsTaken));
				gridsTaken++;
			}
			for (ItemView itv : item.getItemView())
			{
				itv.paint(g, grid.get(gridsTaken));
				gridsTaken++;
			}
			repaint(0, 0, getWidth(), getHeight());
		}
		
		
	}
	public void init1() throws FileNotFoundException {
		//Az IceView-k legeneralasa a mar betoltott palya alapjan
		File file = new File("map1_coords.txt");
		Scanner s = new Scanner(file);
		String[] temp;
		ArrayList<Position> pos = new ArrayList<Position>();
		for(int i = 0; i < Game.getInstance().getMapPieces().size(); i++)
		{
			temp = s.nextLine().split("\t");
			pos.add(new Position(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), 140));
		}
		int j = 0;
		for (Ice item : Game.getInstance().getMapPieces())
		{
			if(item instanceof StableIce)
				iceView.add(new StableView(pos.get(j), item));
			if(item instanceof UnstableIce)
				iceView.add(new UnstableView(pos.get(j), item));
			if(item instanceof HoleIce)
				iceView.add(new HoleView(pos.get(j), item));
			j++;
		}
		for (IceView item : iceView)
		{
			for (Ice ice : item.getIce().getNeighbourArray())
			{
				item.addNeighbour(findIceView(ice));
			}
			for(Item it : item.getIce().getItemArray()) 
			{
				ItemView iv = new ItemView(item, it);
				item.addItemView(iv);
				itemView.add(iv);
			}
			for (Character ch: item.getIce().getCharacterArray())
			{
				CharacterView cv = new CharacterView(item, ch);
				item.addCharacterView(cv);
				characterView.add(cv);
			}
		}
	}
	@Override
	public void update()
	{
		
	}
}
