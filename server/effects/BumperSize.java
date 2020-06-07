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
		default:
			throw new RuntimeException("invalid effect type");
		}
		
		writeSize();
	}
	
	protected void expire()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH, server.Bumper.DEFAULT_HEIGHT);
		writeSize();
	}
	
	private void makeLong()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH,
				server.Bumper.DEFAULT_HEIGHT * MULTIPLE_SIZE);
	}
	
	private void makeSmall()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH,
				server.Bumper.DEFAULT_HEIGHT / MULTIPLE_SIZE);
	}
	
	
	private void setBumperSize(int width, int height)
	{
		server.Bumper bumper = Player.getBumper();
		bumper.setHeight(height);
		bumper.setWidth(width);
	}
	
	private void writeSize()
	{
		server.Bumper bump = Player.getBumper();
		server.PongServer.protocol.writePlayerProtocol(Player.getPlayerID(), "SIZE", bump.getWidth() + "," + bump.getHeight());
	}
}
