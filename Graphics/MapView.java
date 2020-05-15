package Graphics;

import java.util.ArrayList;

public class MapView implements UpdateInterface
{
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
	@Override
	public void update()
	{
		
	}
}
