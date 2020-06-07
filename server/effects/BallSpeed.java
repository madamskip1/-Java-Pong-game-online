package effects;

public class BallSpeed  extends BallEffect
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
		Ball.returnToPreviousVelocity();
	}
	
	private void makeFast()
	{
		Ball.saveVelocity();
		Ball.getVelocity().multiply(MULTIPLE_SPEED);
	}
	
	private void makeSlow()
	{
		Ball.saveVelocity();
		Ball.getVelocity().multiply(1 / (float) MULTIPLE_SPEED);
	}

}
