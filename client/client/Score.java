package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
*Klasa przechowuj�ca i rysuj�ca wyniki w rozgrywce
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
	 * @param name wy�wietlana nazwa dla wyniku (gracza)
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
	 * Ustawia po�o�enie wy�wietlanego wyniku na panelu
	 * 
	 * @param x - pierwsza wsp�rz�dna
	 * @param y - druga wsp�rz�dna
	 */
	public void setPosition(int x, int y) {
		p = new Point(x, y);
	}

	/**
	 * Wypisuje nazw� gracza i wynik
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
