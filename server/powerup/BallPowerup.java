package powerups;

public class BallPowerup extends server.Powerup
{

	public BallPowerup(int x, int y) {
		super(x, y);
		
	}

	public void hitBy(server.Balls balls, int index)
	{
		switch(Type)
		{
		case BALL_MULTIPLE_SINGLE:
			balls.multiple(index);
			break;
		case BALL_MULTIPLE_ALL:
			balls.multipleAll();
			break;
		default:
		}
	}
}
