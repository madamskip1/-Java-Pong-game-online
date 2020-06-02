package client;

import java.awt.Graphics2D;
import java.util.Vector;

public class Balls {
	private Vector<Ball> _balls;

	public Balls() {
		_balls = new Vector<Ball>();
	}

	public static Vector<Ball> deserialize(String stringWithBalls) {
		Vector<Ball> toReturn = new Vector<Ball>();
		String[] splitted = stringWithBalls.split(";");
		String[] singleBall;
		int numberOfBalls = splitted.length;

		for (String ball : splitted) {
			singleBall = ball.split(",");
			toReturn.add(new Ball(Integer.parseInt(singleBall[0]),
					Integer.parseInt(singleBall[1]),
					Integer.parseInt(singleBall[2])));
		}

		return toReturn;
	}

	public void setBalls(Vector<Ball> balls) {
		_balls = balls;
	}

	public void draw(Graphics2D g) {
		for (Ball b : _balls)
			b.drawBall(g);
	}

	public void clearBalls() {
		_balls.clear();
	}
}
