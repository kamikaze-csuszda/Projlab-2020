package Graphics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import Characters.Character;
import Characters.Eskimo;
import Characters.Scientist;
import Item.BreakableShovel;
import Item.Shovel;
public class CharacterView
{
	private Character character;
	private IceView iceView;
	private Image img;
	public void setCharacter(Character c) {
		character = c;
	}
	public void setIceView(IceView iv) {
		iceView = iv;
	}
	public IceView getIceView() {
		return iceView;
	}
	public Character getCharacter() {
		return character;
	}
	public CharacterView(IceView i, Character c) {
		character = c;
		iceView = i;
		File fr = null;
		BufferedImage bi = null;
		try
		{
			if(character instanceof Scientist)
				fr = new File("tudos.png");
			if(character instanceof Eskimo)
				fr = new File("eskimo.png");
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
	public void update()
	{
		
	}
	public void paint(Graphics g, Position pos)
	{
		g.drawImage(img, pos.getX(), pos.getY(), null);
	}

}
