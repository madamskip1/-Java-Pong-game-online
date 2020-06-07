package effects;

/**
 * Klasa efekt�w modyfikacji pr�dko�ci dla pi�ki
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
	 * Przywraca pr�dko�� z momentu zebrania powerupu
	 */
	protected void expire()
	{
		Ball.returnToPreviousVelocity();
	}
	
	/**
	 * Przyspiesza pi�k�
	 */
	private void makeFast()
	{
		Ball.saveVelocity();
		Ball.getVelocity().multiply(MULTIPLE_SPEED);
	}
	
	/**
	 * Zwalnia pi�k�
	 */
	private void makeSlow()
	{
		Ball.saveVelocity();
		Ball.getVelocity().multiply(1 / (float) MULTIPLE_SPEED);
	}

}
