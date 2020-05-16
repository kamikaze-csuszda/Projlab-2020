package Graphics;

import java.awt.LayoutManager;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import Ice.Ice;
import Main.Game;

public class IceInfoPanel extends Panel implements UpdateInterface
{
	private Ice ice;
	private JLabel name;
	private JLabel characters;
	private JLabel items;
	private JLabel snow;
	private JLabel igloo;
	private JLabel bear;
	private JLabel maxchar;
	public IceInfoPanel()
	{
		super();
		name = new JLabel();
		characters = new JLabel();
		items = new JLabel();
		snow = new JLabel();
		igloo = new JLabel();
		bear = new JLabel();
		maxchar = new JLabel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(name);
		add(characters);
		add(items);
		add(snow);
		add(igloo);
		add(bear);
		add(maxchar);
	}

	public IceInfoPanel(LayoutManager layout)
	{
		super(layout);
		// TODO Auto-generated constructor stub
	}
	public void setIce(Ice i) {
		ice = i;
		update();
		
	}

	@Override
	public void update()
	{
		try
		{
			name.setText("Jegtabla: " + Game.getInstance().findName(ice));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		invalidate();
	}

}
