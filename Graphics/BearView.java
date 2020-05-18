package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import Characters.Eskimo;
import Characters.PolarBear;
import Characters.Scientist;

public class BearView implements UpdateInterface
{
	private IceView iceView;
	Image img;
	public BearView(IceView i) {
		iceView = i;
		File fr = null;
		BufferedImage bi = null;
		try
		{
			fr = new File("bear.png");
			 bi = ImageIO.read(fr);
		}
	 catch (FileNotFoundException e)
	{
		e.printStackTrace();
	} catch (IOException e)
	{
		e.printStackTrace();
	}
	img = bi.getScaledInstance(46, 46, Image.SCALE_DEFAULT);
	}

	public IceView getIceView() {
		return iceView;
	}
	public void paint(Graphics g, Position pos)
	{
		g.drawImage(img, pos.getX(), pos.getY(), null);
	}
	@Override
	public void update()
	{
		
	}
	

}
