package Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

import Item.Item;
import Item.*;

public class ItemView implements UpdateInterface
{
	private IceView iceView;
	private Item item;
	public ItemView(IceView _iceView, Item _item)
	{
		iceView = _iceView;
		item = _item;
	}
	public void setItem(Item i) {
		item = i;
	}
	public Item getItem() {
		return item;
	}
	public void setIceView(IceView i) {
		iceView = i;
	}
	public IceView getIceView() {
		return iceView;
	}
	
	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}
	public void paint(Graphics g, Position pos)
	{
		File fr = null;
		BufferedImage bi = null;
		try
		{
			if(item instanceof Shovel)
				fr = new File("aso.png");
			if(item instanceof BreakableShovel)
				fr = new File("torekeny_aso.png");
			if(item instanceof DivingSuit)
				fr = new File("buvarruha.png");
			if(item instanceof Flare)
				fr = new File("flare.png");
			if(item instanceof Food)
				fr = new File("kaja.png");
			if(item instanceof Rope)
				fr = new File("kotel.png");
			if(item instanceof Cartridge)
				fr = new File("patron.png");
			if(item instanceof FlareGun)
				fr = new File("pisztoly.png");
			if(item instanceof Tent)
				fr = new File("tent.png");
			 bi = ImageIO.read(fr);
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Image img = bi.getScaledInstance(46, 46, Image.SCALE_DEFAULT);
		g.drawImage(img, pos.getX(), pos.getY(), null);
	}

}
