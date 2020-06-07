package powerup;

import server.Effect;

/**
 * Klasa efektów modyfikacji zachowañ pi³ek
 */
public class BallPowerup extends server.Powerup
{
	private server.Powerups.BallPowerupTypes ballType;
	
	/**
	 * Tworzy nowy powerup z efektami dla pi³ek
	 * 
	 * @param x - pierwsza wspó³rzêdna po³o¿enia
	 * @param y - druga wspó³rzêdna po³o¿enia
	 * @param _ballType - typ efektu
	 */
	public BallPowerup(int x, int y, server.Powerups.BallPowerupTypes _ballType) {
		super(x, y);
		ballType = _ballType;
		For = server.Powerups.PowerupFor.BALL;
		Type = server.Powerups.intToType(_ballType.ordinal());
	}

	/**
	 * Uruchamia efekt powerupu w zale¿noœci od typu
	 * 
	 * @param balls - wszystkie pi³ki
	 * @param ball - pi³ka która aktywowa³a powerup
	 * @return aktywowany efekt
	 */
	public server.Effect hitBy(server.Balls balls, server.Ball ball)
	{
		server.Effect effect;
		Effect.EffectsType type = null;
		switch(ballType)
		{
		case MULTIPLE_SINGLE:
			balls.multiple(ball);
			return null;
		case MULTIPLE_ALL:
			balls.multipleAll();
			return null;
		case FAST:
			effect = new effects.BallSpeed();
			type = Effect.EffectsType.FAST;
			break;
		case SLOW:
			effect = new effects.BallSpeed();
			type = Effect.EffectsType.SLOW;
			break;
		default:
			return null;
		}
		
		effect.setType(type);
		effect.setBall(ball);
		effect.For = server.Effect.EffectFor.BALL;
		return effect;
	}
}
