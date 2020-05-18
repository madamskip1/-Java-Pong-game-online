package client;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bumper {
	public static final Color BUMPER_COLOR = Color.yellow;
	
	private Point Position;
	private int width;
	private int height;

	public Bumper() {
	}
	

	public void setPosition(int x, int y) {
		Position.set(x, y);
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
