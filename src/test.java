import client.client;
import client.clientNetwork;
import server.serverNetwork;

public class test {
    public static void main (String[] args)
	{
		// serverNetwork sNetwork = new serverNetwork();
        // sNetwork.addSocket("one");

		clientNetwork cliNetwork = new clientNetwork();
        cliNetwork.connect();

        boolean flag = true;
        while (true) {
            try {
                // sNetwork.sendBoard("the board");

                cliNetwork.listen();
                flag = false;
            } catch (Exception e) {
                System.out.println("Error:"+ e.getMessage());
            }
        }





	}
}
