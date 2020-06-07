package server;
import java.io.*;
import java.net.*;

/**
 *Klasa sieciowo obs³uguj¹ca klientów
 */
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
			received = "";
			try 
			{
				received = InputStream.readUTF();
			}
			catch (IOException e)
			{
				if (!(e instanceof SocketTimeoutException))
				{
					System.out.println("DUPA>ZEPSUTE");
					e.printStackTrace();
					break;
				} 
					
			}
			Protocol.read(PlayerID, received);
		}
		try
		{
			InputStream.close();
			OutputStream.close();
			System.out.printf("Player %d disconected", PlayerID);
		}
		catch (IOException e)
		{
			System.out.println("ROZLACZONO");
			e.printStackTrace();
		}
		System.out.println("ROZLACZONO");
	}
}
