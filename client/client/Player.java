package client;

public class Player {
	public enum Direction
	{
		UP,
		DOWN,
		NONE;
		
		public int size() { return 3; }
	}
	
	private Bumper bumper;
	
	public Player()  {
		bumper = new Bumper();
	}
	
	public void setPosition(int x, int y) { bumper.setPosition(x, y); }
	
	public void setSize(int width, int height)
	{
		bumper.setWidth(width);
		bumper.setHeight(height);
	}
	
	public Bumper getBumper() {
		return bumper;
	}
}
