package Graphics;

import java.util.ArrayList;

import Ice.Ice;

public class IceView implements UpdateInterface
{
	private ArrayList<CharacterView> characterView;
	private ArrayList<IceView> neighbours;
	private BearView bearView;
	private Position pos;
	private ArrayList<ItemView> itemView;
	private Ice ice;
	public IceView() {
		characterView = new ArrayList<CharacterView>();
		neighbours = new ArrayList<IceView>();
		itemView = new ArrayList<ItemView>();
	}
	public Ice getIce() {
		return ice;
	}
	public void addCharacterView(CharacterView cv) {
		characterView.add(cv);
	}
	public void removeCharacterView(CharacterView cv) {
		characterView.remove(cv);
	}
	public void addNeighbour(IceView iv) {
		neighbours.add(iv);
	}
	public IceView(Position _pos, Ice _ice) {
		this();
		pos = _pos;
		ice = _ice;
		
	}
	public Position getPos() {
		return pos;
	}
	public void setPosition(Position _pos) {
		pos = _pos;
	}
	public void drawLines() {
		
	}
	public ArrayList<IceView> getNeighbours(){
		return neighbours;
	}
	public BearView getBearView() {
		return bearView;
	}
	public void setBearView(BearView bv) {
		bearView = bv;
	}
	public ArrayList<CharacterView> getCharView(){
		return characterView;
	}
	public ArrayList<ItemView> getItemView(){
		return itemView;
	}
	@Override
	public void update()
	{
		
	}

}
