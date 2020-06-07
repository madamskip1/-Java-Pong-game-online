package client;

import java.awt.Graphics2D;
import java.util.Vector;

/**
 *Klasa przechowuj�ca zbi�r pi�ek. Zawiera funkcj� do rysowania
 *wszystkich pi�ek
 */
public class Balls {
	private Vector<Ball> _balls;

	/**
	 * Tworzy pusty wektor przechowuj�cy pi�ki
	 */
	public Balls() {
		_balls = new Vector<Ball>();
	}

	/**
	 * Zwraca ilo�� pi�ek
	 * 
	 * @return ilo�� pi�ek
	 */
	public int size() {
		return _balls.size();
	}

	/**
	 * Przetwarza odebran� wiadomo�� na wektor pi�ek
	 * 
	 * @param stringWithBalls odczytana wiadomo�� o pi�kach
	 * @return nowy wektor pi�ek
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
	 * Ustawia nowy wektor pi�ek
	 * 
	 * @param balls wektor pi�ek
	 */
	public void setBalls(Vector<Ball> balls) {
		_balls = balls;
	}

	/**
	 * Rysuje wszystkie pi�ki 
	 * 
	 * @param g - renderer
	 */
	public void draw(Graphics2D g) {
		for (int i = 0; i < _balls.size(); ++i)
			_balls.get(i).drawBall(g);
	}

	/**
	 * Zeruje ilo�� pi�ek
	 */
	public void clearBalls() {
		_balls.clear();
	}
}
