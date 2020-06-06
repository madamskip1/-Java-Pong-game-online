package client;

import java.util.Vector;

public class Game {
	ClientProtocol Protocol;
	private Player Players[];
	private int you;
	private int opponent;
	private Window Window;
	private TopPanel TopPanel;
	private Board Board;
	private Keyboard Keyboard;
	private Balls Balls;
	private Powerups Powerups;
	protected static Score Scores[];
	private int FPS = 60;
	
	public enum States
	{
		BEFORE_INIT,
		INIT,
		ACCEPTED,
		INITIALIZED,
		RUNNING,
		GAMEOVER
	}
	
	protected static States State;
	
	public Game()
	{
		State = States.BEFORE_INIT;
		Players = new Player[2];
		Players[0] = new Player();
		Players[1] = new Player();
		Balls = new Balls();
		Powerups = new Powerups();
	}
	
	public void setProtocol(ClientProtocol _prot)
	{
		Protocol = _prot;
	}
	
	
	public void setYou(int num)
	{
		you = num;
		opponent = (num + 1) % 2;
	}
	
	public void Balls(int numOfBalls, String ballsString)
	{
		Vector<Ball> newBalls = client.Balls.deserialize(ballsString);
		
		Balls.setBalls(newBalls);
	}
	
	public void Powerups(int numOfPowerups, String powerupsString)
	{
		Vector<Powerup> newPowers = client.Powerups.deserialize(powerupsString);
		
		Powerups.setPowerups(newPowers);
	}
	
	public void PlayerPos(int player, int x, int y)
	{
		Players[player].setPosition(x, y);
	}
	
	public void PlayerSize(int player, int width, int height)
	{
		Players[player].setSize(width,  height);
	}
	
	
	public void init()
	{
		
		// Tutaj rysujesz pocz¹tek:
		//  * ogólnie okno
		//  * ¯e oczekujesz na gracz    -> DO ZROBIENIA

		
		State = States.INIT;
		
		Keyboard = new Keyboard(this);
		Window = new Window(Keyboard);
		Scores = new Score[2];
		
		if (you == 0)
		{
			Scores[0] = new Score("Ty");
			Scores[1] = new Score("Przeciwinik");
		}
		else
		{
			Scores[1] = new Score("Ty");
			Scores[0] = new Score("Przeciwinik");
		}
		
		TopPanel = new TopPanel(Scores[0], Scores[1], Keyboard);
		Board = new Board(Keyboard);
		Board.setBalls(Balls);
		Board.setBumper(Players[0].getBumper(), 0);
		Board.setBumper(Players[1].getBumper(), 1);
		Board.setPowerups(Powerups);
		Window.setBoard(Board);
		Window.setTopPanel(TopPanel);
		
		Window.createAndShowGUI();
		Board.repaint();
	}
	
	public void initialized()
	{
		State = States.INITIALIZED;
		Board.repaint();
	}
	
	
	public void start()
	{
		State = States.RUNNING;
	
		Thread loop = new Thread()
				{
					public void run()
					{
						gameLoop();
					}
				};
		
		loop.start();
	}
	
	public void gameOver(int winner)
	{
		State = States.GAMEOVER;
		
		// Zatrzymanie pêtli
		// Wyœwietlenie przegrana/wygrana zale¿nie, który klient
		
	}
	
	private void gameLoop()
	{
		long timeBetweenUpdates = 1000000000  / FPS;
		long lastTime = System.nanoTime();
		long now;
		long update;
		long delta;
		
		while (State == States.RUNNING)
		{
			// Pêtla do poprawienia
			now = System.nanoTime();
			update = now - lastTime;
			lastTime = now;
			delta = (long) (update / ((double) timeBetweenUpdates));
			
			// update(delta)   - to wykorzystamy do zmniejszenia efektu wizualnego laga
			
			Board.repaint();
			TopPanel.repaint();
			
		}
		
	}
	
	
	public void keyPressed(int code)
	{
		switch(code)
		{
		case 38:
		case 87:
			Protocol.writePlayerProtocol("DIR", "UP");
			break;
		case 40:
		case 83:
			Protocol.writePlayerProtocol("DIR", "DOWN");
			break;
		}
	}
	
	public void keyReleased(int code)
	{
		switch(code)
		{
		case 38:
		case 87:
		case 40:
		case 83:
			Protocol.writePlayerProtocol("DIR", "NONE");
			break;
		case 32:
			accept();
			break;
		}
	}
	
	public void accept()
	{
		if (State == Game.States.INITIALIZED || State == Game.States.ACCEPTED)
		{
			State = Game.States.ACCEPTED;
			Board.repaint();
			Protocol.writeAcceptProtocol();
		}
	}

	
	public void zeroBalls() {
		Balls.clearBalls();
	}
	
	public void zeroPowerups()
	{
		Powerups.clearPowerups();
	}
}
