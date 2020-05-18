package miscellaneous;

public class Player {
	public static final double DEFAULT_SPEED = 2.0;
	
	public enum Direction
	{
		UP,
		DOWN,
		NONE;
		
		public int size() { return 3; }
	}
	
	public Bumper bumper;
	private Direction direction;
	private double speed;
	
	public Player(int x, int y, Bumper bump)  {
		bumper = bump;
		stop();
		resetSpeed();
	}
	
	public void goUp() { direction = Direction.UP; }
	
	public void goDown() { direction = Direction.DOWN; }
	
	public void stop() { direction = Direction.NONE; }
	
	public void move(int dir)
	{
		if (dir >= direction.size())
			return;
		
		direction = Direction.values()[dir];
	}
	
	public void setSpeed(double _speed) { speed = _speed; }
	
	public double getSpeed() { return speed; }
	
	public void resetSpeed() { setSpeed(DEFAULT_SPEED); }
	
	public Point getPosition() { return bumper.getPosition(); }
	
	public void update(double deltaTime)
	{
		if (direction == Direction.NONE)
			return;
		
		int dy = (int)Math.round(deltaTime * speed);
		
		if (direction == Direction.UP)
			dy *= -1;
		
		bumper.move(dy);
	}
}
