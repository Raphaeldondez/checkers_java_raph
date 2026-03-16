package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class serverNetwork {
	public  int port = 5000;
	public List<Client> clientList = new ArrayList<>();


	public class Client{
		public  String name;
		private Socket socket;
    	private PrintWriter out;
    	private  BufferedReader in;

		public Client(String name){
			this.name = name;
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				socket = serverSocket.accept();
				this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        		this.in  = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			} 
			catch (IOException e) 
			{
				System.out.println("Error: " + e.getMessage());
			}
		}

		public void send(String message){
			out.println(message);
		}
	}

	public void getInfos(){
		System.out.println("Port: "+ port );
		System.out.println("Nb socket: "+ clientList.size() );
	}

	public void setPort(int port)
		{
			this.port = port;
		}

	public void addSocket(String name) //Lance l'événement d'attente de connection
	{
		Client newClient = new Client(name);
		clientList.add(newClient);
		System.out.println("Client added: "+ name );
	}

	public void sendBoard(String board)  throws IOException//Envoie la board à tous les clients connectés
	{
		System.out.println("Borad sending");
		for(Client c: clientList)
		{
			c.send(board);
			System.out.println("board send to "+ c.name);
			
			
			
			// // ServerSocket serverSocket = new ServerSocket(5000);
			// // System.out.println("Server is waiting for a connection...");

			// // Socket clientSocket = serverSocket.accept();
			// // System.out.println("Client connected!");

			// PrintWriter out = new PrintWriter(c.socket.getOutputStream(), true);
			// out.println("Checkers board");


        	// // clientSocket.close();
        	// // serverSocket.close();
		}
	}

	public static void listen(){} //Lance l'écoute d'instruction sur un socket

	public static void closeSocket(){}//Ferme le socket avec le client concerné
}
