package effects;

/**
 * Klasa efektów modyfikacji prêdkoœci dla pi³ki
 */
public class BallSpeed  extends BallEffect
{
	private static final int MULTIPLE_SPEED = 2;
	
	/**
	 * Ustawia efekt dla powerupu
	 */
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
	
	/**
	 * Przywraca prêdkoœæ z momentu zebrania powerupu
	 */
	protected void expire()
	{
		Ball.returnToPreviousVelocity();
	}
	
	/**
	 * Przyspiesza pi³kê
	 */
	private void makeFast()
	{
		Ball.saveVelocity();
		Ball.getVelocity().multiply(MULTIPLE_SPEED);
	}
	
	/**
	 * Zwalnia pi³kê
	 */
	private void makeSlow()
	{
		Ball.saveVelocity();
		Ball.getVelocity().multiply(1 / (float) MULTIPLE_SPEED);
	}

}
