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
	public IceView(Position _pos, BearView _bearView, Ice _ice) {
		this();
		pos = _pos;
		bearView = _bearView;
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
