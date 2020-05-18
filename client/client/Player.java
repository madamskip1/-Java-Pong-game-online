package client;

import java.awt.Graphics2D;
import miscellaneous.*;

public class Player {
	public enum Direction
	{
		UP,
		DOWN,
		NONE;
		
		public int size() { return 3; }
	}
	
	private Direction direction;
	public Bumper bumper;
	
	public Player()  {
		stop();
		bumper = new Bumper();
	}
	
	public void goUp() { direction = Direction.UP; }
	
	public void goDown() { direction = Direction.DOWN; }
	
	public void stop() { direction = Direction.NONE; }
	
	public void setPosition(int x, int y) { bumper.setPosition(x, y); }
	
	public void setSize(int width, int height)
	{
		bumper.setWidth(width);
		bumper.setHeight(height);
	}
}
