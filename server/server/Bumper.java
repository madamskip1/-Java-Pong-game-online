package server;

public class Bumper {
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 40;
	
	private Point Position;
	private int width;
	private int height;
	private int lastSpeed;

	public Bumper() {
		Position = new Point();
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		lastSpeed = 0;
	}
	

	public void move(int dy) {
		Position.add(0, dy);
	}

	public void setHeight(int _height) {
		height = _height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int _width)
	{
		width = _width;
	}

	public int getWidth() {
		return width;
	}

	public Point getCentre() {
		return new Point(Position.getX() + (width / 2), Position.getY() + (height / 2));
	}
	
	public Point getPosition()
	{
		return Position;
	}
	
	public void setPosition(int x, int y)
	{
		Position.x = x;
		Position.y = y;
	}
	
	public void setLastSpeed(int _lastSpeed) {
		lastSpeed = _lastSpeed;
	}
	
	public int getLastSpeed() {
		return lastSpeed;
	}
}
