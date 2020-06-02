package server;

public class PowerupType {
	public Powerups.PowerupFor For;
	public boolean UseTimer;
	public long Time;

	public PowerupType(Powerups.PowerupFor _for)
	{
		setup(_for, false, 0);
	}
	
	public PowerupType(Powerups.PowerupFor _for, long _time)
	{
		setup(_for, true, _time);
	}
	
	private void setup(Powerups.PowerupFor _for, boolean _useTimer, long _time)
	{
		For = _for;
		UseTimer = _useTimer;
		Time = _time;
	}
}
