package powerups;

public class BallPowerup extends server.Powerup
{
	private server.Powerups.BallPowerupTypes ballType;
	
	public BallPowerup(int x, int y, server.Powerups.BallPowerupTypes _ballType) {
		super(x, y);
		ballType = _ballType;
		For = server.Powerups.PowerupFor.BALL;
		Type = server.Powerups.intToType(_ballType.ordinal());
	}

	public server.Effect hitBy(server.Balls balls, server.Ball ball)
	{
		switch(ballType)
		{
		case MULTIPLE_SINGLE:
			balls.multiple(ball);
			break;
		case MULTIPLE_ALL:
			balls.multipleAll();
			break;
		default:
		}
		
		return null;
	}
}
