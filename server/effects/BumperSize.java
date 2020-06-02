package effects;

public class BumperSize extends PlayerEffect
{
	private static final int MULTIPLE_SIZE = 2;
	
	
	public void startEffect()
	{
		switch (type)
		{
		case LONG:
			makeLong();
			break;
		case SMALL:
			makeSmall();
			break;
		}
	}
	
	protected void expire()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH, server.Bumper.DEFAULT_HEIGHT);
	}
	
	private void makeLong()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH * MULTIPLE_SIZE,
				server.Bumper.DEFAULT_HEIGHT * MULTIPLE_SIZE);
	}
	
	private void makeSmall()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH / MULTIPLE_SIZE,
				server.Bumper.DEFAULT_HEIGHT / MULTIPLE_SIZE);
	}
	
	
	private void setBumperSize(int width, int height)
	{
		server.Bumper bumper = Player.getBumper();
		bumper.setHeight(server.Bumper.DEFAULT_HEIGHT);
		bumper.setWidth(server.Bumper.DEFAULT_HEIGHT);
	}
}
