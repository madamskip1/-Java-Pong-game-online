package client;

public class Point {
	protected int x;
	protected int y;
	
	public Point()
	{
		this.zero();
	}
	
	public Point(Point p)
	{
		this.x = p.x;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(java.awt.Point p) {
		this.x = (int)p.getX();
		this.y = (int)p.getY();
	}
	
	public void zero()
	{
		this.x = 0;
		this.y = 0;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void add(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void add(Point p)
	{
		this.x += p.x;
		this.y += p.y;
	}
	
	public double distance(int x, int y)
	{
		int dx = this.x - x;
		int dy = this.y - y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public double distance(Point p)
	{
		return distance(p.x, p.y);
	}
	
	public void set(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
}
	