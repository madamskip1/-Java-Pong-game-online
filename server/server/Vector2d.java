package server;

public class Vector2d extends Point 
{
	public Vector2d(int _vx, int _vy) {
		x = _vx;
		y = _vy;
	}

	public void negate()
	{
		this.x *= -1;
		this.y *= -1;
	}
	
	public void negateX()
	{
		this.x *= -1;
	}
	
	public void negateY()
	{
		this.y *= -1;
	}
	
	public double length()
	{
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public int lengthInt()
	{
		return (int)length();
	}
	
	public void accelerate(int _d2x, int _d2y) {
		x += _d2x;
		y += _d2y;
	}
}
