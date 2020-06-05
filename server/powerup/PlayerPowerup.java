package powerup;

public class PlayerPowerup extends server.Powerup {
	private server.Powerups.PlayerPowerupTypes playerType;
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

	
	public server.Effect hitBy(server.Balls balls, server.Ball ball)
	{
		server.Player player = forPlayer(ball.touchBy());

		switch (playerType) {
		case LONG:
			return Long(player);
		case SMALL:
			return Small(player);
//		case BEER:
//			break;
		case FAST:
			return Fast(player);
		case SLOW:
			return Slow(player);
		default:
			return null;
		}

	}
	
	private server.Player forPlayer(int index)
	{
		
		
		if (For == server.Powerups.PowerupFor.ME)
			return server.Powerups.getPlayer(index);

		return server.Powerups.getPlayer((index + 1) % 2);

	}

	private server.Effect Long(server.Player player) {
		server.Effect effect = new effects.BumperSize();
		effect.setPlayer(player);
		effect.setType(server.Effect.EffectsType.LONG);
		effect.For = server.Effect.EffectFor.PLAYER;
		return effect;
	}

	private server.Effect Small(server.Player player) {
		server.Effect effect = new effects.BumperSize();
		effect.setPlayer(player);
		effect.setType(server.Effect.EffectsType.SMALL);
		effect.For = server.Effect.EffectFor.PLAYER;
		return effect;
	}

	private server.Effect Slow(server.Player player) {
		server.Effect effect = new effects.PlayerSpeed();
		effect.setPlayer(player);
		effect.setType(server.Effect.EffectsType.SLOW);
		effect.For = server.Effect.EffectFor.PLAYER;
		return effect;
	}

	private server.Effect Fast(server.Player player) {
		server.Effect effect = new effects.PlayerSpeed();
		effect.setPlayer(player);
		effect.setType(server.Effect.EffectsType.FAST);
		effect.For = server.Effect.EffectFor.PLAYER;
		return effect;
	}
	
	
	private void setType()
	{
		
	}
}
