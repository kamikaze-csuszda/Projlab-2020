package Graphics;

import java.awt.Frame;

public class MyFrame extends Frame
{
	private static final long serialVersionUID = 1L;
	private boolean active;
	public void setActive(boolean b) {
		active = b;
	}
	public boolean getActive() {
		return active;
	}
}
