package server;

import java.util.Vector;

public class Effects {
	private Vector<effects.PlayerEffect> PlayerEffects;
	//private Vector<BallEffect> BallEffects;
	
	Effects(){
		PlayerEffects = new Vector<effects.PlayerEffect>();
	}
	
	
	public void update(long deltaTime)
	{
		for(effects.PlayerEffect effect : PlayerEffects)
		{
			if (effect.update(deltaTime))
				PlayerEffects.remove(effect);
		}
	}
	
	
	
	public void add(Effect effect)
	{
		switch(effect.For)
		{
		case BALL:
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
		for(effects.PlayerEffect effect : PlayerEffects)
		{
			if (effect.isForSameObject(effectToAdd))
			{
				effect.end();
				PlayerEffects.remove(effect);
				break;
			}
		}
		
		effectToAdd.startEffect();
		PlayerEffects.add(effectToAdd);
	}
}
