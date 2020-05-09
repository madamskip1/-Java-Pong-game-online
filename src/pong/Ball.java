package pong;

public class Ball {
	private Point position;
	private Vector2d velocity;
	
	public Ball clone()
	{
		Ball _ball = new Ball();
		_ball.setPosition(this.position);
		_ball.setVelocity(this.velocity);
		
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
	
	public void setVelocity(int x, int y)
	{
		this.velocity.x = x;
		this.velocity.x = y;
	}
	
	public void setVelocity(Vector2d vec)
	{
		this.setVelocity(vec.x, vec.y);
	}
}
