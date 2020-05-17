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
import Main.Game;

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
		mapView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (IceView item : mapView.getIceView())
				{
					Rectangle rect = new Rectangle(item.getPos().getX(), item.getPos().getY(), item.getPos().getR(), item.getPos().getR());
					if(rect.contains(e.getX(), e.getY())) 
					{
						selectedIce = item.getIce();
					gf.icePanel.setIce(selectedIce);
					}
				}
			}
		});
		gf.addIcePanel(new IceInfoPanel(Game.getInstance().getMapPieces().get(0)));
	}
	public class CommandActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
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
		gf.update();
	}
}
