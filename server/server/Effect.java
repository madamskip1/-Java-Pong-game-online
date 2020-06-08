package server;

/**
 * Zwraca warto�� po�o�enia na osi y
 */
public class Effect
{
	private static final int TIME_TO_EXPIRE = 5; // sec
	double timeToExpire;
	protected EffectsType type;
	public EffectFor For;
	
	public enum EffectsType
	{
	 	BEER,
		LONG,
		SMALL,
		SLOW,
		FAST
	};
	
	public enum EffectFor
	{
		PLAYER,
		BALL
	}

	public Effect()
	{
		timeToExpire = TIME_TO_EXPIRE;
	}
	
	
	/**
	 * Aktualizuje pozosta�y czas trwania efektu
	 * @param time - modyfikator pozosta�ego czasu
	 * @return true je�li efekt wygas�, false w przeciwnym przypadku
	 */
	public boolean update(long time)
	{
		timeToExpire -= (double) time / 1000000000;
		if (timeToExpire <= 0)
		{
			expire();
			return true;
		}
		return false;
	}
	
	public void end()
	{
		expire();
	} 
	
	public void setType(Effect.EffectsType _type)
	{
		type = _type;
	}
	
	public void startEffect() {}// metoda wirtualna
	protected void expire() {} // metoda wirtualna
	public void setPlayer(Player player) {} // metoda wirtualna
	public void setBall(Ball ball) {} // metoda wirtualna
	public boolean isForSameObject(Object obj) { return obj == this; };
}
