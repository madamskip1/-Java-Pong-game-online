package pong;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(java.awt.Point p) {
		this.x = (int)p.getX();
		this.y = (int)p.getY();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void translate(int dx, int dy) {
		x = this.x + dx;
		y = this.y + dy;
	}
}
	