package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Characters.Character;
import Item.Gun;
import Item.Item;
import Main.Game;

public class CharacterInfoPanel extends JPanel implements UpdateInterface
{
	private Character selected;
	private static final long serialVersionUID = 1L;
	JLabel name, items, warmth, action, gunparts;
	public CharacterInfoPanel(Character c)
	{
		super();
		name = new JLabel();
		items = new JLabel();
		warmth = new JLabel();
		action = new JLabel();
		gunparts = new JLabel();
		setCharacter(c);;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(200, 800));
		setBackground(new Color(138,210,255));
		add(name);
		add(items);
		add(gunparts);
		add(warmth);
		add(action);
		
		
	}

	public CharacterInfoPanel(LayoutManager layout)
	{
		super(layout);
		
	}
	public void setCharacter(Character c) {
		selected = c;
		update();
	}
	@Override
	public void update()
	{
		try
		{
			name.setText("Kivalasztott karakter: " + Game.getInstance().findName(selected));
		} catch (Exception e){}
		String temp = "Targyak: ";
		for (Item item : selected.getEquipment())
		{
			try
			{
				temp = temp.concat(Game.getInstance().findName(item) + ", ");
			} catch (Exception e){}
			
		}
		items.setText(temp);
		warmth.setText("Testho: " + selected.getWarmth() + "/" + selected.getMaxWarmth());
		action.setText("Hatralevo akciok: " + selected.getAction());
		temp = "Fegyverreszek: ";
		for (Gun item : selected.getGunParts())
		{
			try
			{
				temp = temp.concat(Game.getInstance().findName(item) + ", ");
			} catch (Exception e){}
			
		}
		gunparts.setText(temp);
	}

}
