package pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bumper {
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 40;
	public static final Color BUMPER_COLOR = Color.yellow;
	
	private Point Position;
	private int width;
	private int height;

	public Bumper(int x, int y) {
		Position = new Point (x, y);
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
	}
	

	public void translate(int dx, int dy) {
		Position.translate(dx, dy);
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

	public void draw(Graphics2D g2d) {
		g2d.setColor(BUMPER_COLOR);
		g2d.fillRect(Position.getX(), Position.getY(), width, height);
	}
	
	
}
