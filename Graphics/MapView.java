package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MapView extends JPanel implements UpdateInterface
{
	private static final long serialVersionUID = 1L;
	private ArrayList<ItemView> itemView;
	private ArrayList<CharacterView> characterView;
	private ArrayList<IceView> iceView;
	private BearView bearView;
	private GameFrame gameFrame;
	public MapView() {
		itemView = new ArrayList<ItemView>();
		characterView = new ArrayList<CharacterView>();
		iceView = new ArrayList<IceView>();
		bearView = null;
		this.setBackground(Color.cyan);
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
	public void paintComponent(Graphics g) {
		for (IceView item : iceView)
		{
			if(item instanceof StableView)
				g.setColor(Color.white);
			else if(item instanceof UnstableView)
				g.setColor(Color.gray);
			else if(item instanceof HoleView)
				g.setColor(Color.red);
			g.fillOval(item.getPos().getX(), item.getPos().getY(), item.getPos().getR(), item.getPos().getR());
		}
	}
	@Override
	public void update()
	{
		
	}
}
