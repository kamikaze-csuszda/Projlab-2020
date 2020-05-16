package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import Ice.Ice;
import Main.Game;

public class ViewController implements UpdateInterface
{
	public HashMap<Character, CharacterView> characterMap;
	public HashMap<Ice, IceView> iceMap;
	private ArrayList<MyFrame> frames;
	public MapView mapView;
	public Ice selectedIce;
	private MenuFrame mf;
	public ViewController() {
		characterMap = new HashMap<Character, CharacterView>();
		iceMap = new HashMap<Ice, IceView>();
		frames = new ArrayList<MyFrame>();
		frames.add(new MenuFrame());
		frames.get(0).setActive(true);
		frames.add(new GameFrame());
		frames.get(1).add(new MapView());
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
				initGame();
				System.out.println("Game Init Done");
			}
		});
		
	}
	public void initGame() {
		
	}
	public void initController() {
		frames.get(0).setVisible(true);
	}
	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}
}
