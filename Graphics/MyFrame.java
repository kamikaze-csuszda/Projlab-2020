package Graphics;
import javax.swing.JFrame;

public class MyFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private boolean active;
	public MyFrame(String string)
	{
		super(string);
	}
	public void setActive(boolean b) {
		active = b;
	}
	public boolean getActive() {
		return active;
	}
}
