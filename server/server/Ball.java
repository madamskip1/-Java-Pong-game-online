package server;

import java.util.Random;

public class Ball {
	public static final int DEFAULT_RADIUS = 2;
	public static final double FRICTION = 0.05;
	private Point position;
	private Vector2d velocity;
	private int radius;
	private int touchByPlayer;// 0 == L ; 1 == P

	public Ball() {
		position = new Point(0, 0);
		velocity = new Vector2d(0, 0);
		radius = DEFAULT_RADIUS;
		touchByPlayer = -1;
	}

	public Ball clone() {
		Ball _ball = new Ball();
		_ball.setPosition(this.position);
		_ball.setVelocity(this.velocity);
		_ball.touchByPlayer = this.touchByPlayer;
		return _ball;
	}

	public static Ball generateBall(int boardWidth, int boardHeight) {
		Ball _ball = new Ball();
		int min, max;
		min = boardWidth * 2 / 5;
		max = boardWidth * 3 / 5;
		int x = Utility.randomInt(min, max);
		min = boardHeight * 1 / 5;
		max = boardHeight * 4 / 5;
		int y = Utility.randomInt(min, max);
		_ball.setPosition(x, y);

		x = Utility.randomInt(5, 10);
		if (Utility.randomInt(0, 100) <= 50)
			x *= -1;

		y = Utility.randomInt(0, 10);
		if (Utility.randomInt(0, 100) <= 50)
			y *= -1;

		_ball.setVelocity(x, y);

		return _ball;
	}

	public void setPosition(Point pos) {
		this.setPosition(pos.x, pos.y);
	}

	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	public Point getPosition() {
		return position;
	}

	public void setVelocity(int x, int y) {
		this.velocity.x = x;
		this.velocity.x = y;
	}

	public void setVelocity(Vector2d vec) {
		this.setVelocity(vec.x, vec.y);
	}

	public boolean update(double deltaTime) {
		position.add((int) (velocity.x * deltaTime), (int) (velocity.y * deltaTime));

		if (position.x < 0 || position.x > Board.WIDTH)
			return false;

		if ((position.y + radius) < 0) {
			position.y = -(position.y + radius);
			velocity.negateY();
		} else if ((position.y + radius) > Board.HEIGHT) {
			position.y = Board.HEIGHT - (Board.HEIGHT - position.y + radius);
			velocity.negateY();
		}

		return true;
	}

	public boolean bumperCollisionUpdate(Bumper b1, Bumper b2) {
		if (velocity.x > 0) {// collision check with left
			if (position.x + radius >= b2.getPosition().x) {
				velocity.negateX();
				spin(b1);
				touchByPlayer = 0;
				return true;
			}
		} else {// collision check with right
			if (position.x - radius <= b1.getPosition().x + b1.getWidth()) {
				velocity.negateX();
				spin(b2);
				touchByPlayer = 1;
				return true;
			}
		}
		return false;
	}

	boolean powerupCollisionCheck(Powerup pp) {

		int testX = 0;
		int testY = 0;

		if (position.x < pp.getPosition().x) 	testX = pp.getPosition().x; // left edge
		else if (position.x > pp.getPosition().x) 	testX = pp.getPosition().x + pp.getSize(); // right edge
		if (position.y < pp.getPosition().y) 	testY = pp.getPosition().y; // top edge
		else if (position.y > pp.getPosition().y)	 testY = pp.getPosition().y + pp.getSize(); // bottom edge

		int distX = position.x - testX;
		int distY = position.y - testY;
		double distance = Math.sqrt((distX * distX) + (distY * distY));

		if (distance <= radius)
			return true;

		return false;
	}

	private void spin(Bumper b) {
		if (b.getLastSpeed() < 0) {// board goes up
			if (velocity.y < 0)
				velocity.accelerate(0, (int) (-1 * FRICTION * b.getLastSpeed()));
			else
				velocity.accelerate(0, (int) (FRICTION * b.getLastSpeed()));
		} else {// board goes down
			if (velocity.y < 0)
				velocity.accelerate(0, (int) (-1 * FRICTION * b.getLastSpeed()));
			else
				velocity.accelerate(0, (int) (FRICTION * b.getLastSpeed()));
		}
	}

}
