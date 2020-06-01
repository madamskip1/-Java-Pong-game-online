package server;

public class Powerup {
	public static final int POWERUP_SIZE = 50;

	private Point position;
	private int size; //width == height

	
	public Powerup(int x, int y){
		position = new Point(x, y);
		size = POWERUP_SIZE;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public int getSize() {
		return size;
	}
	
}
