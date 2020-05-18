package miscellaneous;

import java.util.Vector;

public class Balls {
	private Vector<Ball> _balls;
	
	public Balls()
	{
		_balls = new Vector<Ball>();
	}
	
	public String serialize()
	{
		String ret = "";
		
		for(Ball b : _balls)
		{
			Point pos = b.getPosition();
			ret += pos.x + "," + pos.y + ";";
		}
		
		return ret;
	}
	
	public Ball getBall(int index) { return _balls.elementAt(index); }
	
	public void addBall(Ball _b) { _balls.add(_b); }
	
	public void addBall(Ball _b, int index) 
	{
		if (index >= _balls.size())
			_balls.setSize(index);
		
		_balls.add(index, _b);
	}
	
	public int size() { return _balls.size(); }

	public void update(double deltaTime)
	{
		for (Ball b : _balls)
		{
			if (!b.update(deltaTime))
			{
				_balls.remove(b);
			}
			
		}
	}
}
