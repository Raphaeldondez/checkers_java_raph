package server;

public class server {
	public serverNetwork network;

	public static void main(String[] args) {
		serverNetwork network = new serverNetwork();
		network.setPort(500);
		network.getInfos();
		//int port = Integer.parseInt();
		// sock = new ServerSocket(port);
	}
}
