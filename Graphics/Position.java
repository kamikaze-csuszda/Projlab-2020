package Graphics;

public class Position
{
	float x;
	float y;
	float r;
	public Position(float _x, float _y, float _r) {
		x = _x;
		y = _y;
		r = _r;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getR() {
		return r;
	}
	public void setPos(float _x, float _y) {
		x = _x;
		y = _y;
	}
	public void setRadius(float _r) {
		r = _r;
	}
}
