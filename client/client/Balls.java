package client;

import java.awt.Graphics2D;
import java.util.Vector;

public class Balls {
	private Vector<Ball> _balls;

	public Balls() {
		_balls = new Vector<Ball>();
	}

	public int size() {
		return _balls.size();
	}

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

	public void setBalls(Vector<Ball> balls) {
		_balls = balls;
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < _balls.size(); ++i)
			_balls.get(i).drawBall(g);
	}

	public void clearBalls() {
		_balls.clear();
	}
}
