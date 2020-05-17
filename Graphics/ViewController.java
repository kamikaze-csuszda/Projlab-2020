package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import Ice.Ice;
import Main.Game;

import javax.swing.*;

public class ViewController implements UpdateInterface
{
	public HashMap<Character, CharacterView> characterMap;
	public HashMap<Ice, IceView> iceMap;
	private ArrayList<MyFrame> frames;
	public MapView mapView;
	public Ice selectedIce;
	public Character selectedCharacter;
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
		generateActionListeners();
		mf.setActive(false);
		gf.setActive(true);
		mf.setVisible(false);
		gf.setVisible(true);
		gf.add(mapView);
	}
	public class CommandActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFrame items = new JFrame("Eszkozvalaszto");
			items.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			for(String key : Game.getInstance().getObjects().keySet()){
				if (Game.getInstance().getObjects().get(key) instanceof Item && Game.getInstance().getObjects().get(key).getCharacter().equals(selectedCharacter))
					items.add(new JRadioButton(key));
			}
			items.add(new JButton("Kivalaszt"));
		}
	}
	public void generateActionListeners()
	{
		
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
