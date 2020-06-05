package server;

import java.util.Iterator;
import java.util.Vector;

public class Effects {
	private Vector<effects.PlayerEffect> PlayerEffects;
	private Vector<effects.BallEffect> BallEffects;
	
	Effects(){
		PlayerEffects = new Vector<effects.PlayerEffect>();
		BallEffects = new Vector<effects.BallEffect>();
	}
	
	
	public void update(long deltaTime)
	{
		int size;
		size = PlayerEffects.size();
		for (int i = 0; i < size; i++)
		{
			if (PlayerEffects.get(i).update(deltaTime))
				PlayerEffects.remove(PlayerEffects.get(i));
		}
	
		size = BallEffects.size();
		for (int i = 0; i < size; i++)
		{
			if (BallEffects.get(i).update(deltaTime))
				BallEffects.remove(BallEffects.get(i));
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
		int size = PlayerEffects.size();
		effects.PlayerEffect effect;
		for (int i = 0; i < size; i++)
		{
			effect = PlayerEffects.get(i);
			if(effect.isForSameObject(effectToAdd))
			{
				effect.end();
				BallEffects.remove(i);
				break;
			}
		}
		
		effectToAdd.startEffect();
		PlayerEffects.add(effectToAdd);
	}
	
	private void addBall(effects.BallEffect effectToAdd)
	{
		int size = BallEffects.size();
		effects.BallEffect effect;
		for (int i = 0; i < size; i++)
		{
			effect = BallEffects.get(i);
			if(effect.isForSameObject(effectToAdd))
			{
				effect.end();
				BallEffects.remove(i);
				break;
			}
		}
		
		effectToAdd.startEffect();
		BallEffects.add(effectToAdd);
	}
}
