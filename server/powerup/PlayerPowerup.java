package powerup;

import effects.BumperSize;

public class PlayerPowerup extends server.Powerup
{
	private server.Powerups.PlayerPowerupTypes playerType;

	public PlayerPowerup(int x, int y, server.Powerups.PlayerPowerupTypes _playerType,
			server.Powerups.PowerupFor _For) {
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

	public server.Effect hitBy(server.Balls balls, server.Ball ball) {
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

	private server.Player forPlayer(int index) {

		if (For == server.Powerups.PowerupFor.ME)
			return server.Powerups.getPlayer(index);

		return server.Powerups.getPlayer((index + 1)%2);
			
	}
	
	private server.Effect setupEffect(server.Effect effect, server.Player player, server.Effect.EffectsType typ)
	{
		effect.setPlayer(player);
		effect.setType(typ);
		effect.For = server.Effect.EffectFor.PLAYER;
		return effect;
	}

	private void setType() {

	}
}
