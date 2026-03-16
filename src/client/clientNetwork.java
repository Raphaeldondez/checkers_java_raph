package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import server.server;

public class clientNetwork{
	private int port = 5000;
	private String server_host = "localhost"; //"Server_name" or "Disconnected"
	public Socket socket;
	private PrintWriter out;
	private  BufferedReader in;

	public void connect(){
		try
		{
			InetAddress server = InetAddress.getByName(server_host);
			socket = new Socket(server, port);
			this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			this.in  = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			System.out.println("Connected to server" );
		}
		catch(IOException e)
		{
			System.out.println("Connected to server failed: "+ e );
		}
	}

	public void disconnect(){}

	public void sendMove(String move){
		
	}

	public void listen() throws IOException
	{
		try {
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println("Message received: " + line);
			}
		} catch (IOException e) {
			System.out.println("[!] Client disconnected: " + socket.getInetAddress());
		}

		
		// // Socket socket = new Socket("localhost", 5000);

        // BufferedReader in = new BufferedReader(
        //     new InputStreamReader(socket.getInputStream())
        // );

        // String message = in.readLine();
        // System.out.println("Message received from server: " + message);

		// socket.close();
	}


	// private void move_send(String message) {
	// 	try {
	// 		InetAddress serveur = InetAddress.getByName(server_host);
	// 		socket = new Socket(serveur, port);
	// 		PrintStream out = new PrintStream(socket.getOutputStream());
	// 		out.println(message);
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }
}