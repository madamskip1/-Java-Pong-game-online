package server;

import miscellaneous.Ball;
import miscellaneous.Balls;
import miscellaneous.Bumper;
import miscellaneous.Player;

public class Game
{
	public enum States
	{
		INIT,
		INITIALIZED,
		RUNNING,
		GAMEOVER
	}
	
	private static final int PLAYERS = 2;
	private Player players[];
	private Bumper playersBumpers[];
	private ServerProtocol Protocol;
	private Balls Balls;
	private States State;
	
	public Game(ServerProtocol _prot)
	{
		State = States.INIT;
		Protocol = _prot;
		
		Protocol.writeGameStateProtocol("INIT", "0");
		
		setupPlayers();
		setupFirstBall();
		
		Protocol.writeGameStateProtocol("INIT", "1");
		State = States.INITIALIZED;
	}
	
	public void setupPlayers()
	{
		players = new Player[PLAYERS];
		playersBumpers = new Bumper[PLAYERS];
		
		playersBumpers[0] = new Bumper();
		playersBumpers[1] = new Bumper();
		
		int yPosition = (miscellaneous.BoardConst.WIDTH/2) - (Bumper.DEFAULT_HEIGHT/2);
		players[0] = new Player(miscellaneous.BoardConst.X_PADDING, yPosition, playersBumpers[0]);
		Protocol.writePlayerProtocol(0, "MOVE", miscellaneous.BoardConst.X_PADDING + "," + yPosition);
		Protocol.writePlayerProtocol(0, "SIZE", Bumper.DEFAULT_WIDTH + "," + Bumper.DEFAULT_HEIGHT);
		
		players[1] = new Player(miscellaneous.BoardConst.WIDTH - miscellaneous.BoardConst.X_PADDING - Bumper.DEFAULT_WIDTH, yPosition, playersBumpers[1]);
		Protocol.writePlayerProtocol(1, "MOVE", (miscellaneous.BoardConst.WIDTH - miscellaneous.BoardConst.X_PADDING) + "," + yPosition);
		Protocol.writePlayerProtocol(1, "SIZE", Bumper.DEFAULT_WIDTH + "," + Bumper.DEFAULT_HEIGHT);
	}
	
	public void setupFirstBall()
	{
		Balls = new Balls();
		Balls.addBall(Ball.generateBall(miscellaneous.BoardConst.WIDTH, miscellaneous.BoardConst.HEIGHT));
		Protocol.writeBallProtocol(("POSITION;" + Balls.size()), Balls.serialize());
	}
	
	public void start()
	{
		Protocol.writeGameStateProtocol("START", "0");
		State = States.RUNNING;
	}
	
	public void gameOver()
	{
		int winner = 0;
		Protocol.writeGameStateProtocol("OVER", Integer.toString(winner));
	}
	
		
	private void update(double deltaTime)
	{
		players[0].update(deltaTime);
		players[1].update(deltaTime);
		Balls.update(deltaTime);
		
		// Check collision
		// Check gameEnd
	}
}
