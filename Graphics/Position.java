package Graphics;

public class Position
{
	int x;
	int y;
	int r;
	public Position(int _x, int _y, int _r) {
		x = _x;
		y = _y;
		r = _r;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getR() {
		return r;
	}
	public void setPos(int _x, int _y) {
		x = _x;
		y = _y;
	}
	public void setRadius(int _r) {
		r = _r;
	}
}
