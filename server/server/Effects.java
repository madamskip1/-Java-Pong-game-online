package server;

import java.util.Iterator;
import java.util.Vector;

/**
 * Klasa przechowuj¹ca wszystkie efekty.
 */
public class Effects {
	private Vector<effects.PlayerEffect> PlayerEffects;
	private Vector<effects.BallEffect> BallEffects;
	
	Effects(){
		PlayerEffects = new Vector<effects.PlayerEffect>();
		BallEffects = new Vector<effects.BallEffect>();
	}
	
	
	public void update(long deltaTime)
	{
		for (Iterator<effects.PlayerEffect> pe = PlayerEffects.iterator(); pe.hasNext();)
		{
			effects.PlayerEffect e = pe.next();
			if (e.update(deltaTime))
				pe.remove();
		}
	
		for (Iterator<effects.BallEffect> be = BallEffects.iterator(); be.hasNext();)
		{
			effects.BallEffect e = be.next();
			if (e.update(deltaTime))
				be.remove();
		}
	}
	
	
	
	public void add(Effect effect)
	{
		switch(effect.For)
		{
		case BALL:
			addBall((effects.BallEffect) effect);
			break;
		case PLAYER:
			addPlayer((effects.PlayerEffect) effect);
			break;
		default:
			break;
		}
	}
	
	
	private void addPlayer(effects.PlayerEffect effectToAdd)
	{		
		for (Iterator<effects.PlayerEffect> it = PlayerEffects.iterator(); it.hasNext();)
		{
			effects.PlayerEffect effect = it.next();
			if (effect.isForSameObject(effectToAdd))
			{
				effect.end();
				it.remove();
				break;
			}
		}	
		effectToAdd.startEffect();
		PlayerEffects.add(effectToAdd);
	}
	
	private void addBall(effects.BallEffect effectToAdd)
	{
		for (Iterator<effects.BallEffect> it = BallEffects.iterator(); it.hasNext();)
		{
			effects.BallEffect effect = it.next();
			if (effect.isForSameObject(effectToAdd))
			{
				effect.end();
				it.remove();
				break;
			}
		}	
		effectToAdd.startEffect();
		BallEffects.add(effectToAdd);
	}
}
