package server;

public class Player {
	public static final int DEFAULT_SPEED = 2;
	
	public static enum Direction
	{
		UP,
		DOWN,
		NONE;
		
		public int size() { return 3; }
	}
	
	public Bumper bumper;
	private Direction direction;
	private int speed;
	private boolean beer;
	private int playerID;
	
	public Player(int id, Bumper bump)  {
		playerID = id;
		bumper = bump;
		stop();
		resetSpeed();
	}
	
	public int getPlayerID()
	{
		return playerID;
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

	public void setBeer(boolean set) { beer = set; }
	
	public void setSpeed(int _speed) { speed = _speed; }
	
	public int getSpeed() { return speed; }
	
	public void resetSpeed() { setSpeed(DEFAULT_SPEED); }
	
	public Point getPosition() { return bumper.getPosition(); }	
	
	public Bumper getBumper()
	{
		return bumper;
	}
	
	public void update()
	{
		if (direction == Direction.NONE)
			return;
		Direction dir;
		
		if (beer)
		{
			if (direction == Direction.UP)
				dir = Direction.DOWN;
			else
				dir = Direction.UP;
		}
		else
			dir = direction;
		
		int dy = speed;
		int y = bumper.getPosition().y;
		
		if (dir == Direction.UP)
		{
			if ((y - dy) < 0)
				dy = y;
			
			dy *= -1;
		}
		else if ((y + bumper.getHeight() + dy) >= Board.HEIGHT)
			dy = Board.HEIGHT - y - bumper.getHeight();
		
		
		if (dy == 0)
			return;
		
		bumper.move(dy);
	}
}
