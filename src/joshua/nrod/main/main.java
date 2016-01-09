package joshua.nrod.main;

import joshua.nrod.stomp.Client;
import joshua.nrod.stomp.Listener;

public class main {
    
    
    //Network Rail ActiveMQ server
    private static final String SERVER = "datafeeds.networkrail.co.uk";
    
    // Server port for STOMP clients
    private static final int PORT = 61618;
    
    // Your account username, typically an email address
    private static final String USERNAME = "joshuapopelewis@gmail.com";
    
    // Your account password
    private static final String PASSWORD = "11985Jpl!";
    
    // Example topic (this one is for Southern Train Movements)
    private static final String TOPIC = "/topic/TD_LNW_C_SIG_AREA";
    
    public static void main(String[] args) throws Exception {
        new main().go();
    }
    
    /*
     * Connect to a single topic and subscribe a listener
     * @throws Exception Too lazy to implement exception handling....
     */
    public void go() throws Exception {
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
