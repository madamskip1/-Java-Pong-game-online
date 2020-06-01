package server;
import java.io.*;
import java.net.*;

public class PongClientHandler implements Runnable {
	final DataInputStream InputStream;
	final DataOutputStream OutputStream;
	final Socket Socket;
	final int PlayerID;
	private ServerProtocol Protocol;
	
	public PongClientHandler(Socket socket, InputStream input, OutputStream output, int id, ServerProtocol protocol)
	{
		Socket = socket;
		InputStream = new DataInputStream(input);
		OutputStream = new DataOutputStream(output);
		PlayerID = id;
		Protocol = protocol;
	}
	
	@Override
	public void run()
	{
		String received;
		while(true)
		{
			try
			{
				received = InputStream.readUTF();
				Protocol.read(PlayerID, received);
			}
			catch (IOException e)
			{
				System.out.println("DUPA>ZEPSUTE");
				e.printStackTrace();
				break;
			}
		}
		try
		{
			InputStream.close();
			OutputStream.close();
			System.out.printf("Player %d disconected", PlayerID);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
