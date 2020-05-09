package pong;

public class Vector2d extends Point 
{
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
}
