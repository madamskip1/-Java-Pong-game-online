package miscellaneous;

import java.util.Random;

import server.Utility;

public class Ball {
	public static final int DEFAULT_RADIUS = 2;
	private Point position;
	private Vector2d velocity;
	private int radius;
	private int touchByPlayer;
	
	public Ball()
	{
		position = new Point(0, 0);
		velocity = new Vector2d(0, 0);
		radius = DEFAULT_RADIUS;
		touchByPlayer = -1;
	}
	
	public Ball clone()
	{
		Ball _ball = new Ball();
		_ball.setPosition(this.position);
		_ball.setVelocity(this.velocity);
		_ball.touchByPlayer = this.touchByPlayer;
		return _ball;
	}
	
	public static Ball generateBall(int boardWidth, int boardHeight)
	{
		Ball _ball = new Ball();
		int min, max;
		min = boardWidth * 2 / 5;
		max = boardWidth * 3 / 5;
		int x = Utility.randomInt(min,  max);
		min = boardHeight * 1 / 5;
		max = boardHeight * 4 / 5;
		int y = Utility.randomInt(min,  max); 
		_ball.setPosition(x, y);
		
		x = Utility.randomInt(5, 10);
		if (Utility.randomInt(0, 100) <= 50)
			x *= -1;
		
		y = Utility.randomInt(0, 10);
		if (Utility.randomInt(0, 100) <= 50)
			y *= -1;
		
		_ball.setVelocity(x,  y);
		
		return _ball;
	}
	
	public void setPosition(Point pos)
	{
		this.setPosition(pos.x, pos.y);
	}
	
	public void setPosition(int x, int y)
	{
		this.position.x = x;
		this.position.y = y;
	}
	
	public Point getPosition() { return position; }
	
	public int getRadius() { return radius; }
	
	
	public void setVelocity(int x, int y)
	{
		this.velocity.x = x;
		this.velocity.x = y;
	}
	
	public void setVelocity(Vector2d vec)
	{
		this.setVelocity(vec.x, vec.y);
	}
	
	public boolean update(double deltaTime)
	{
		position.add((int)(velocity.x * deltaTime), (int)(velocity.y * deltaTime));
		
		if (position.x < 0 || position.x > BoardConst.WIDTH)
			return false;
		
		if ((position.y + radius) < 0)
		{
			position.y = -(position.y + radius);
			velocity.negateY();
		}
		else if ((position.y + radius) > BoardConst.HEIGHT)
		{
			position.y = BoardConst.HEIGHT -(BoardConst.HEIGHT - position.y + radius);
			velocity.negateY();
		}
			
		return true;
	}
	
}
