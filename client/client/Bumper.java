package client;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Klasa przechowuj�ca informacje o bumperze - odbijaczu.
 */
public class Bumper {
	private final int HEIGHT = 200;
	private final int WIDTH = 10;
	private final Color[] BUMPER_COLORS = { Color.green, Color.blue };

	private Point Position;
	private int width;
	private int height;

	/**
	 * Tworzy instancj� klasy Bumper z domy�lnymi parametrami
	 */
	public Bumper() {
		Position = new Point();
		width = WIDTH;
		height = HEIGHT;
	}

	/**
	 * Ustawia pozycj� bumpera
	 * 
	 * @param x - pierwsza wsp�rz�dna
	 * @param y - druga wsp�rz�dna
	 */
	public void setPosition(int x, int y) {
		Position.set(x, y);
	}

	/**
	 * Ustawia d�ugo��(wysoko��) bumpera
	 * 
	 * @param _height nowa d�ugo�� bumpera
	 */
	public void setHeight(int _height) {
		height = _height;
	}

	/**
	 * Zwraca d�ugo��(wysoko��) bumpera
	 * 
	 * @return d�ugo�� bumpera
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Ustawia szeroko�� bumpera
	 * 
	 * @param _width nowa szeroko�� bumpera
	 */
	public void setWidth(int _width) {
		width = _width;
	}

	/**
	 * Zwraca szeroko�� bumpera
	 * 
	 * @return szeroko�� bumpera
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Zwraca punkt stanowi�cy �rodek bumpera
	 * 
	 * @return �rodek bumpera
	 */
	public Point getCentre() {
		return new Point(Position.getX() + (width / 2), Position.getY() + (height / 2));
	}

	/**
	 * Zwraca po�o�enie (lewy g�rny r�g) bumpera
	 * 
	 * @return po�o�enie bumpera
	 */
	public Point getPosition() {
		return Position;
	}

	/**
	 * Rysuje bumper
	 * 
	 * @param g2d - renderer
	 * @param color - kolor (bumpery obu maj� r�ne kolory)
	 */
	public void draw(Graphics2D g2d, int color) {
		g2d.setColor(BUMPER_COLORS[color]);
		g2d.fillRect(Position.getX(), Position.getY(), width, height);
	}

}
