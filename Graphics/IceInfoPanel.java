package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Ice.Ice;
import Main.Game;
import Strategy.NoIgloo;
import Strategy.TentStrategy;

public class IceInfoPanel extends JPanel implements UpdateInterface
{
	private Ice ice;
	private JLabel name;
	private JLabel snow;
	private JLabel igloo;
	private JLabel maxchar;
	public IceInfoPanel(Ice i)
	{
		super();
		ice = i;
		setBackground(new Color(138,210,255));
		name = new JLabel("Jegtabla: ");
		snow = new JLabel("Horeteg: ");
		igloo = new JLabel("Van iglu: ");
		maxchar = new JLabel("Max karakterek: ");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(200, 800));
		add(name);
		add(snow);
		add(igloo);
		add(maxchar);
		repaint(0, 0, 200, 800);
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
			snow.setText("Horeteg: " + ice.getSnow());
			if(ice.getIglooStrategy() instanceof NoIgloo)
				igloo.setText("Van igloo: nincs");
			else if(ice.getIglooStrategy() instanceof TentStrategy)
				igloo.setText("Van igloo: sator van");
			else
				igloo.setText("Van igloo: van");
			if(ice.getMaxCharacters() > 0)
				maxchar.setText("Max karakterek: " + ice.getMaxCharacters());
			else
				maxchar.setText("Max karakterek: Nem instabil");
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		invalidate();
	}

}
