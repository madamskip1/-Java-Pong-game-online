package server;

public class Powerup {
	public static final int POWERUP_SIZE = 50;
	protected Powerups.PowerupFor For;
	protected Powerups.PowerupTypes Type;
	private Point position;
	private int size; //width == height
	protected Powerups Powerups;
	
	public Powerup(int x, int y){
		position = new Point(x, y);
		size = POWERUP_SIZE;
	}
	
	public void print()
	{
		System.out.println(Type + " TYP; " + For + " FOR");
	}
	
	public void setPowerups(Powerups pups)
	{
		Powerups = pups;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public int getSize() {
		return size;
	}
	
	public Powerups.PowerupTypes getType()
	{
		return Type;
	}
	
	public void setType(Powerups.PowerupTypes typ)
	{
		Type = typ;
	}
	
	public server.Effect hitBy(Balls balls, Ball ball) { return null; } // metoda wirtualna

	
}
