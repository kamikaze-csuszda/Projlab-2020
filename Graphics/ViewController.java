package Graphics;

import java.util.ArrayList;
import java.util.HashMap;

import Ice.Ice;

public class ViewController implements UpdateInterface
{
	public HashMap<Character, CharacterView> characterMap;
	public HashMap<Ice, IceView> iceMap;
	private ArrayList<MyFrame> frames;
	public MapView mapView;
	public Ice selectedIce;
	public ViewController() {
		characterMap = new HashMap<Character, CharacterView>();
		iceMap = new HashMap<Ice, IceView>();
		frames = new ArrayList<MyFrame>();
		frames.add(new MenuFrame());
		frames.get(0).setActive(true);
	}
	
	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}
}
