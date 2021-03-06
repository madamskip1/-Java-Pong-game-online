package server;
import java.io.IOException;
import java.net.*;

/**
 *Klasa ��cz�ca logik� rozgrywki i wysy�anie informacji do klient�w
 */
public class PongServer {
	public static final int PORT = 51234;
	
	private static PongClientHandler player1;
	private static PongClientHandler player2;
	public static  ServerProtocol protocol;
	
	private static ServerSocket Socket;
	
	private static Game Game;
	
	public static void main(String[] args) throws IOException {	
		PongServer serv = new PongServer();
	}
	
	/**
	 *Uruchamia serwer i wysy�a ustawienia pocz�tkowe do klient�w
	 *@throws IOException jesli sie nie uda
	 */
	public PongServer() throws IOException
	{
		System.out.println("Starting server...");
		Socket = new ServerSocket(PORT);
		System.out.println("Server started.");
		protocol = new ServerProtocol(this);
		player1 = null;
		player2 = null;
		
		while (player2 == null)
		{
			Socket _s = null;
			
			_s = Socket.accept();
			_s.setSoTimeout(1000);
			
			if (player1 == null)
			{
				player1 = new PongClientHandler(_s, _s.getInputStream(), _s.getOutputStream(), 0, protocol);
				Thread thread1 = new Thread(player1);
				thread1.start();
				System.out.println("Player1 connected");
				player1.OutputStream.writeUTF("PLAYER;YOU;0");
				
			}
			else if (player2 == null)
			{
				player2 = new PongClientHandler(_s, _s.getInputStream(), _s.getOutputStream(), 1, protocol);
				Thread thread2 = new Thread(player2);
				thread2.start();
				System.out.println("Player2 connected");
				player2.OutputStream.writeUTF("PLAYER;YOU;1");
				player2.OutputStream.writeUTF("PLAYER;OPPONENT;0");
			}
		}
		player1.OutputStream.writeUTF("PLAYER;OPPONENT;1");
		
		Game = new Game(protocol);
		protocol.setGame(Game);
		
		Game.initalized();
	}
	
	/**
	 *Wysy�a wiadomo�� do klient�w
	 *@param msg - wiadomo�� do wys�ania
	 *@throws IOException jesli sie nie uda
	 */
	public void output(String msg) throws IOException
	{
		player1.OutputStream.writeUTF(msg);
		player2.OutputStream.writeUTF(msg);
	}

}
