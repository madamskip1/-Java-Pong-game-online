package client;

import java.awt.Graphics2D;
import java.util.Vector;

/**
 *Klasa przechowuj¹ca zbiór pi³ek. Zawiera funkcjê do rysowania
 *wszystkich pi³ek
 */
public class Balls {
	private Vector<Ball> _balls;

	/**
	 * Tworzy pusty wektor przechowuj¹cy pi³ki
	 */
	public Balls() {
		_balls = new Vector<Ball>();
	}

	/**
	 * Zwraca iloœæ pi³ek
	 * 
	 * @return iloœæ pi³ek
	 */
	public int size() {
		return _balls.size();
	}

	/**
	 * Przetwarza odebran¹ wiadomoœæ na wektor pi³ek
	 * 
	 * @param stringWithBalls odczytana wiadomoœæ o pi³kach
	 * @return nowy wektor pi³ek
	 */
	public static Vector<Ball> deserialize(String stringWithBalls) {
		Vector<Ball> toReturn = new Vector<Ball>();
		String[] splitted = stringWithBalls.split(";");
		String[] singleBall;

		for (String ball : splitted) {
			singleBall = ball.split(",");
			toReturn.add(new Ball(Integer.parseInt(singleBall[0]), Integer.parseInt(singleBall[1]),
					Integer.parseInt(singleBall[2])));
		}

		return toReturn;
	}

	/**
	 * Ustawia nowy wektor pi³ek
	 * 
	 * @param balls wektor pi³ek
	 */
	public void setBalls(Vector<Ball> balls) {
		_balls = balls;
	}

	/**
	 * Rysuje wszystkie pi³ki 
	 * 
	 * @param g - renderer
	 */
	public void draw(Graphics2D g) {
		for (int i = 0; i < _balls.size(); ++i)
			_balls.get(i).drawBall(g);
	}

	/**
	 * Zeruje iloœæ pi³ek
	 */
	public void clearBalls() {
		_balls.clear();
	}
}
