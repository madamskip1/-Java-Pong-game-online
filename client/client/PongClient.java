package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PongClient {
	final static int ServerPort = 51234;
	private static Socket Socket;
	private static Game game;
	private static ClientProtocol protocol;
	private static DataInputStream InputStream = null;
	private static DataOutputStream OutputStream = null;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket = new Socket("127.0.0.1", ServerPort);
		Scanner scanner = new Scanner(System.in);		
		InputStream = new DataInputStream(Socket.getInputStream());
		OutputStream = new DataOutputStream(Socket.getOutputStream());
		
		
		game = new client.Game();
		protocol = new ClientProtocol(game, OutputStream);
		game.setProtocol(protocol);
		Thread send = new Thread(new Runnable() {

			@Override
			public void run() {
				String received;
				while(true)
				{
					try
					{
						received = InputStream.readUTF();
						protocol.read(received);
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					
				}
			}
		});
		
		
		send.start();
	}

	public void output(String msg) throws IOException
	{
		OutputStream.writeUTF(msg);
	}
}
