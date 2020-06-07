package effects;

public class PlayerSpeed  extends PlayerEffect
{
	private static final int MULTIPLE_SPEED = 2;
	
	public void startEffect()
	{
		switch(type)
		{
		case FAST:
			makeFast();
			break;
		case SLOW:
			makeSlow();
			break;
		default:
			throw new RuntimeException("invalid effect type");
		}
	}
	
	protected void expire()
	{
		Player.resetSpeed();
	}
	
	private void makeFast()
	{
		Player.setSpeed(server.Player.DEFAULT_SPEED * MULTIPLE_SPEED);
	}
	
	private void makeSlow()
	{
		Player.setSpeed(server.Player.DEFAULT_SPEED / MULTIPLE_SPEED);
	}

}
