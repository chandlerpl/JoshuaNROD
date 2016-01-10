package joshua.nrod.main;

import joshua.nrod.stomp.Client;
import joshua.nrod.stomp.Listener;

public class main {


    private static final String SERVER = "datafeeds.networkrail.co.uk";

    private static final int PORT = 61618;
   
    private static final String USERNAME = "joshuapopelewis@gmail.com";

    private static final String PASSWORD = "11985Jpl!";
    
    private static final String TOPIC = "/topic/TD_LNW_C_SIG_AREA";
    
    public static void main(String[] args) throws Exception {
        new main().go();
    }
    
    public void go() throws Exception {
    	System.out.println("| JPLogics - NROD TD Connection...");
        System.out.println("| Connecting...");
        Client client = new Client(SERVER, PORT, USERNAME, PASSWORD);
        if (client.isConnected()) {
            System.out.println("| Connected to " + SERVER + ":" + PORT);
        } else {
            System.out.println("| Could not connect");
            return;
        }
        System.out.println("| Subscribing...");
        Listener listener = new MyListener();
		new MyJSON();
        client.subscribe(TOPIC , listener);
        System.out.println("| Subscribed to " + TOPIC);
        System.out.println("| Waiting for message...");
    }
}
