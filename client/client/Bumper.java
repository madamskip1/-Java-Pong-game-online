package client;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bumper {
	private final int HEIGHT = 200;
	private final int WIDTH = 10;
	private final Color[] BUMPER_COLORS = { Color.green, Color.blue };

	private Point Position;
	private int width;
	private int height;

	public Bumper() {
		Position = new Point();
		width = WIDTH;
		height = HEIGHT;
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

	public void setWidth(int _width) {
		width = _width;
	}

	public int getWidth() {
		return width;
	}

	public Point getCentre() {
		return new Point(Position.getX() + (width / 2), Position.getY() + (height / 2));
	}

	public Point getPosition() {
		return Position;
	}

	public void draw(Graphics2D g2d, int player) {
		g2d.setColor(BUMPER_COLORS[player]);
		g2d.fillRect(Position.getX(), Position.getY(), width, height);
	}

}
