package server;

import java.util.Iterator;
import java.util.Vector;

/**
 * Klasa przechowuj¹ca wszystkie pi³ki.
 */
public class Balls {
	private final double DISPERSION = 25;

	private Vector<Ball> _balls;

	/**
	 * Tworzy klasê z iloœci¹ pi³ek = 0
	 * */
	public Balls() {
		_balls = new Vector<Ball>();
	}

	/**
	 * Zapisuje stan instancji klasy tak aby mo¿na by³o je wys³aæ do klientów
	 * 
	 * @return wiadomoœæ do wys³ania
	 * */
	public String serialize() {
		String ret = "";

		for (Ball b : _balls) {
			Point pos = b.getPosition();
			ret += pos.x + "," + pos.y + "," + b.getRadius() + ";";
		}

		return ret;
	}

	
	public Ball getBall(int index) {
		return _balls.elementAt(index);
	}

	public void addBall(Ball _b) {
		_balls.add(_b);
	}

	public void addBall(Ball _b, int index) {
		if (index >= _balls.size())
			_balls.setSize(index);

		_balls.add(index, _b);
	}

	public int size() {
		return _balls.size();
	}

	
	/**
	 * Aktualizuje pozycjê wszystkich pi³ek
	 */
	public void update() {
		if (_balls.size() != 0) {
			for (int i = 0; i < _balls.size(); i++) {
				_balls.get(i).update();
			}
		}
	}

	/**
	 * Aktualizuje wynik w oparciu o pozycjê wszystkich pi³ek
	 * @param score - tablica wyników
	 */
	public void updateScore(int score[]) {// tu moze byc cos
		for (Iterator<Ball> it = _balls.iterator(); it.hasNext();) {
			Ball p = it.next();
			if (p.addToScore(score)) {
				it.remove();
			}
		}
	}

	/**
	 * Sprawdza kolizjê wszystkich pi³ek z bumperami
	 * @param bumper1 - lewy bumper
	 * @param bumper2 - prawy bumper
	 */
	public void bumperCollisions(Bumper bumper1, Bumper bumper2) {
		for (int i = 0; i < _balls.size(); i++) {
			_balls.get(i).bumperCollision(bumper1, bumper2);
		}
	}

	/**
	 * Sprawdza czy w grze s¹ jakieœ pi³ki
	 * @return true je¿eli s¹, inaczej false
	 */
	public boolean areThereBalls() {
		if (_balls.size() == 0)
			return false;
		else
			return true;
	}

	/**
	 * Potraja wybran¹ pi³kê
	 * @param ballToMultiple - pi³ka do zwielokrotnienia
	 */
	public void multiple(Ball ballToMultiple) {
		Vector2d vel = ballToMultiple.getVelocity();
		double totalSpeed = Math.sqrt(vel.x * vel.x + vel.y * vel.y);
		double angle = Math.atan2(vel.y, vel.x);
		double angle1 = Math.toRadians(DISPERSION);

		Ball newBall1 = ballToMultiple.clone();
		Ball newBall2 = ballToMultiple.clone();

		newBall1.setVelocity((int) (totalSpeed * Math.cos(angle + angle1)),
				(int) (totalSpeed * Math.sin(angle + angle1)));
		newBall2.setVelocity((int) (totalSpeed * Math.cos(angle - angle1)),
				(int) (totalSpeed * Math.sin(angle - angle1)));

		newBall1.correctSpeed();
		newBall2.correctSpeed();

		_balls.add(newBall1);
		_balls.add(newBall2);

	}

	/**
	 * Potraja wszystkie pi³ki
	 */
	public void multipleAll() {
		int size = size();

		for (int i = 0; i < size; i++)
			multiple(_balls.get(i));
	}

	/**
	 * Generuje pi³kê jeœli nie ma ¿adnej
	 */
	public void addIfZero() {
		if (_balls.size() == 0) {
			Ball b = Ball.generateBall(Board.WIDTH, Board.HEIGHT);
			_balls.add(b);
		}

	}

}
