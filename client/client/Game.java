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
	private Score Scores[];
	
	private int FPS = 60;
	
	public enum States
	{
		BEFORE_INIT,
		INIT,
		INITIALIZED,
		RUNNING,
		GAMEOVER
	}
	
	private States State;
	
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
		Vector<Ball> newBalls = Balls.deserialize(ballsString);
		
		Balls.setBalls(newBalls);
	}
	
	public void Powerup(int numOfPowerups, String powerupsString)
	{
		Vector<Powerup> newPowers = Powerups.deserialize(powerupsString);
		
		
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
		
		// Tutaj rysujesz pocz�tek:
		//  * og�lnie okno
		//  * �e oczekujesz na gracz    -> DO ZROBIENIA

		
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
	}
	
	public void initialized()
	{
		State = States.INITIALIZED;
		// Tutaj rysujesz po do��czeniu drugiego gracza:
		//  * �e czekasz na akceptacj� (to dopiero dodamy, p�ki co startuje automatycznie)
	
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
		
		// Zatrzymanie p�tli
		// Wy�wietlenie przegrana/wygrana zale�nie, kt�ry klient
		
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
			// P�tla do poprawienia
			now = System.nanoTime();
			update = now - lastTime;
			lastTime = now;
			delta = (long) (update / ((double) timeBetweenUpdates));
			
			// update(delta)   - to wykorzystamy do zmniejszenia efektu wizualnego laga
			
			Board.repaint();
		}
		
	}
	
	
	public void keyPressed(int code)
	{
		System.out.println(code);
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
