package powerup;

/**
 * Klasa efekt�w modyfikacji zachowa� graczy
 */
public class PlayerPowerup extends server.Powerup
{
	private server.Powerups.PlayerPowerupTypes playerType;
	
	/**
	 * Tworzy nowy powerup z efektami dla graczy
	 * 
	 * @param x - pierwsza wsp�rz�dna po�o�enia
	 * @param y - druga wsp�rz�dna po�o�enia
	 * @param _playerType - typ efektu
	 * @param _For - dla kogo efekt
	 */
	public PlayerPowerup(int x, int y, server.Powerups.PlayerPowerupTypes _playerType, server.Powerups.PowerupFor _For) {
		super(x, y);
		playerType = _playerType;
		For = _For;	
		int typeNr = 0;
		typeNr += server.Powerups.BallPowerupTypes.values().length;
		
		if (For == server.Powerups.PowerupFor.OPPONENT)
			typeNr += server.Powerups.PlayerPowerupTypes.values().length;
		typeNr += _playerType.ordinal();
		Type = server.Powerups.intToType(typeNr);
	}


	/**
	 * Uruchamia efekt powerupu w zale�no�ci od osoby przeznaczenia efektu
	 * 
	 * @param balls - wszystkie pi�ki
	 * @param ball - pi�ka kt�ra aktywowa�a powerup
	 * @return aktywowany efekt
	 */
	public server.Effect hitBy(server.Balls balls, server.Ball ball)
	{
		server.Player player = forPlayer(ball.touchBy());
		
		server.Effect effect;
		server.Effect.EffectsType type;
		
		switch (playerType)
		{
		case LONG:
			effect = new effects.BumperSize();
			type = server.Effect.EffectsType.LONG;
			break;
		case SMALL:
			effect = new effects.BumperSize();
			type = server.Effect.EffectsType.SMALL;
			break;
		case BEER:
			effect = new effects.PlayerBeer();
			type = server.Effect.EffectsType.BEER;
			break;
		case FAST:
			effect = new effects.PlayerSpeed();
			type = server.Effect.EffectsType.FAST;
			break;
		case SLOW:
			effect = new effects.PlayerSpeed();
			type = server.Effect.EffectsType.SLOW;
			break;
		default:
			return null;
		}
		
		effect = setupEffect(effect, player, type);
		return effect;
	}
	
	/**
	 * Sprawdza dla kt�rego gracza efekt ma by� aktywowany
	 * 
	 * @param index - kt�ry gracz aktywowa� powerup
	 * @return gracz dla kt�rej efekt b�dzie aktywowany
	 */
	private server.Player forPlayer(int index)
	{		
		if (For == server.Powerups.PowerupFor.ME)
			return server.Powerups.getPlayer(index);

		return server.Powerups.getPlayer((index + 1)%2);
	}
	
	/**
	 * Aktywuje efekt
	 * 
	 * @param player - gracz na kt�rego efekt b�dzie dzia�ac
	 * @param type - jaki efekt b�dzie aktywowany
	 * @return aktywowany efekt
	 */
	private server.Effect setupEffect(server.Effect effect, server.Player player, server.Effect.EffectsType type)
	{
		effect.setPlayer(player);
		effect.setType(type);
		effect.For = server.Effect.EffectFor.PLAYER;
		return effect;
	}
}
