package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
*Klasa przechowuj¹ca i rysuj¹ca wyniki w rozgrywce
* */
public class Score {
	private final int size = 20;
	private final Color textColor = Color.WHITE;

	private int score;
	private Point p;
	private String name;

	/**
	 * Tworzenie nowej instancji
	 * 
	 * @param name wyœwietlana nazwa dla wyniku (gracza)
	 */
	public Score(String name) {
		score = 0;
		this.name = name;
	}

	/**
	 * Ustawia wynik
	 * 
	 * @param x - ustawiany wynik
	 */
	public void setScore(int x) {
		score = x;
	}

	/**
	 * Ustawia po³o¿enie wyœwietlanego wyniku na panelu
	 * 
	 * @param x - pierwsza wspó³rzêdna
	 * @param y - druga wspó³rzêdna
	 */
	public void setPosition(int x, int y) {
		p = new Point(x, y);
	}

	/**
	 * Wypisuje nazwê gracza i wynik
	 * 
	 * @param g2d - renderer
	 */
	public void drawScore(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Skia", Font.PLAIN, size);
		g2d.setFont(font);
		g2d.setColor(textColor);
		g2d.drawString(name + ": " + String.valueOf(score), p.x, p.y);
	}
}
