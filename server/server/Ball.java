package server;

import java.util.Random;

public class Ball {
	public static final int DEFAULT_RADIUS = 15;
	public static final double FRICTION = 5;
	private Point position;
	private Vector2d velocity;
	private int radius;
	private int touchByPlayer;// 0 == L ; 1 == P

	public Ball() {
		position = new Point(0, 0);
		velocity = new Vector2d(0, 0);
		radius = DEFAULT_RADIUS;
		touchByPlayer = 1;
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
		y=boardHeight/2;///////////////////////////////////////////////////
		x=510;
		_ball.setPosition(x, y);

		x = Utility.randomInt(5, 100);
		if (Utility.randomInt(0, 100) <= 50)
			x *= -1;

		y = Utility.randomInt(0, 10);
		if (Utility.randomInt(0, 100) <= 50)
			y *= -1;

		x=0;
		y=10;
		
		
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

	public int getRadius() {
		return radius;
	}

	public void setVelocity(int x, int y) {
		this.velocity.x = x;
		this.velocity.y = y;
	}
	
	public int touchBy()
	{
		return touchByPlayer;
	}

	public void setVelocity(Vector2d vec) {
		this.setVelocity(vec.x, vec.y);
	}

	public Vector2d getVelocity() {
		return velocity;
	}
	
	public boolean update() {
		position.add(velocity.x, velocity.y);

		if (position.x < 0 || position.x > Board.WIDTH) // Wysz³a z boku
			return false;

		if ((position.y - radius) < 0) { // Odbicie od góry
			position.y = radius - (position.y - radius);
			velocity.negateY();
		} else if ((position.y + radius) > Board.HEIGHT) { // Odbicie od do³u
			position.y = Board.HEIGHT - radius - (position.y + radius - Board.HEIGHT);
			velocity.negateY();
		}

		return true;
	}

	public boolean bumperCollision(Bumper b1, Bumper b2) {
		if (velocity.x > 0) {
			if (Utility.CircleRectangleCollision(position, radius, b2.getPosition(), b2.getWidth(), b2.getHeight())) {
				velocity.negateX();
				spin(b1);
				touchByPlayer = 1;
				return true;
			}

		} else {
			if (Utility.CircleRectangleCollision(position, radius, b1.getPosition(), b1.getWidth(), b1.getHeight())) {
				velocity.negateX();
				spin(b2);
				touchByPlayer = 0;
				return true;
			}
		}
		return false;
	}

	public boolean powerupCollisionCheck(Powerup pp) {
		if(touchByPlayer == -1)
			return false;
		return Utility.CircleRectangleCollision(position, radius, pp.getPosition(), pp.getSize(), pp.getSize());
	
	}

	public boolean addToScore(int score[]) {
		if (position.x < 0) {
			++score[0];
			return true;
		} else if (position.x > Board.WIDTH) {
			++score[1];
			return true;
		}
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
