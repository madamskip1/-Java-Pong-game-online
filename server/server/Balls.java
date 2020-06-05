package server;

import java.util.Iterator;
import java.util.Vector;

public class Balls {
	public final double DISPERSION = 25;
	private Vector<Ball> _balls;

	public Balls() {
		_balls = new Vector<Ball>();
	}

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

	public void update() {
		if (_balls.size() != 0) {
			for (int i = 0; i < _balls.size(); i++) {
				_balls.get(i).update();
			}
		}
	}

	public void updateScore(int score[]) {// tu moze byc cos
		for (Iterator<Ball> it = _balls.iterator(); it.hasNext();) {
			Ball p = it.next();
			if (p.addToScore(score)) {
				it.remove();
			}
		}
	}

	public void bumperCollisions(Bumper bumper1, Bumper bumper2) {
		for (int i = 0; i < _balls.size(); i++) {
			_balls.get(i).bumperCollision(bumper1, bumper2);
		}
	}

	public boolean areThereBalls() {
		if (_balls.size() == 0)
			return false;
		else
			return true;
	}

	public void multiple(Ball ballToMultiple)// chyba git ale moze cos pomylilem z katami; sie sprawdzi potem
	{
		Vector2d vel = ballToMultiple.getVelocity();
		double angle = Math.atan((double) vel.y / (double) vel.x);
		double totalSpeed = Math.sqrt(vel.x * vel.x + vel.y + vel.y);

		Ball newBall1 = ballToMultiple.clone();
		Ball newBall2 = ballToMultiple.clone();
		newBall1.setVelocity((int) (totalSpeed * Math.cos(angle + DISPERSION)),
				(int) (totalSpeed * Math.sin(angle + DISPERSION)));
		newBall2.setVelocity((int) (totalSpeed * Math.cos(angle - DISPERSION)),
				(int) (totalSpeed * Math.sin(angle - DISPERSION)));
		if(vel.x < 0) {
			newBall1.getVelocity().negateX();
			newBall2.getVelocity().negateX();
		}
		_balls.add(newBall1);
		_balls.add(newBall2);

	}

	public void multipleAll() {
		int size = size();

		for (int i = 0; i < size; i++)
			multiple(_balls.get(i));
	}
}
