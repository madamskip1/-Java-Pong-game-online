package client;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class PongClient {
	final static int ServerPort = 51234;
	private static Socket Socket;
	static Window window = new Window();
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket = new Socket("127.0.0.1", ServerPort);
		Scanner scanner = new Scanner(System.in);
		DataInputStream inputStream = new DataInputStream(Socket.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(Socket.getOutputStream());
		

		Thread send = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
				{
					try
					{
						System.out.println(inputStream.readUTF());
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					
					
				}
			}
			
		});
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				window.createAndShowGUI();
			}
		});
	
		
		
		
		send.start();
	}

}
