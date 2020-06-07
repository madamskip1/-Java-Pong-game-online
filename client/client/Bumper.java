package client;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Klasa przechowuj¹ca informacje o bumperze - odbijaczu.
 */
public class Bumper {
	private final int HEIGHT = 200;
	private final int WIDTH = 10;
	private final Color[] BUMPER_COLORS = { Color.green, Color.blue };

	private Point Position;
	private int width;
	private int height;

	/**
	 * Tworzy instancjê klasy Bumper z domyœlnymi parametrami
	 */
	public Bumper() {
		Position = new Point();
		width = WIDTH;
		height = HEIGHT;
	}

	/**
	 * Ustawia pozycjê bumpera
	 * 
	 * @param x - pierwsza wspó³rzêdna
	 * @param y - druga wspó³rzêdna
	 */
	public void setPosition(int x, int y) {
		Position.set(x, y);
	}

	/**
	 * Ustawia d³ugoœæ(wysokoœæ) bumpera
	 * 
	 * @param _height nowa d³ugoœæ bumpera
	 */
	public void setHeight(int _height) {
		height = _height;
	}

	/**
	 * Zwraca d³ugoœæ(wysokoœæ) bumpera
	 * 
	 * @return d³ugoœæ bumpera
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Ustawia szerokoœæ bumpera
	 * 
	 * @param _width nowa szerokoœæ bumpera
	 */
	public void setWidth(int _width) {
		width = _width;
	}

	/**
	 * Zwraca szerokoœæ bumpera
	 * 
	 * @return szerokoœæ bumpera
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Zwraca punkt stanowi¹cy œrodek bumpera
	 * 
	 * @return œrodek bumpera
	 */
	public Point getCentre() {
		return new Point(Position.getX() + (width / 2), Position.getY() + (height / 2));
	}

	/**
	 * Zwraca po³o¿enie (lewy górny róg) bumpera
	 * 
	 * @return po³o¿enie bumpera
	 */
	public Point getPosition() {
		return Position;
	}

	/**
	 * Rysuje bumper
	 * 
	 * @param g2d - renderer
	 * @param color - kolor (bumpery obu maj¹ ró¿ne kolory)
	 */
	public void draw(Graphics2D g2d, int color) {
		g2d.setColor(BUMPER_COLORS[color]);
		g2d.fillRect(Position.getX(), Position.getY(), width, height);
	}

}
