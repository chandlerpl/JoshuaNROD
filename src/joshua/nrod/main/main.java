package joshua.nrod.main;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import joshua.nrod.stomp.Client;
import joshua.nrod.stomp.Listener;

public class main {
	
    private static final String SERVER = "datafeeds.networkrail.co.uk";

    private static final int PORT = 61618;
    
    private static final String VERSION = "v0.1";
   
    private static final String USERNAME = "joshuapopelewis@gmail.com";
    
    private static final String PASSWORD = "1185Jpl!";
    
    private static final String SEC_TOKEN = "c2c4b7c8-f0fb-446e-be95-d5ea572cb1c7";
    
    private static final String TOPIC = "/topic/TD_LNW_C_SIG_AREA";
    
    public static void main(String[] args) throws Exception {
        new main().go();
    }
    
    public void go() throws Exception{
    	
    	System.out.println("| JPLogics - NROD Connection.." + VERSION);
    	System.out.println("| Security Token: " + SEC_TOKEN);
    	System.out.println("| Starting connection bond for " + SERVER);
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
        
        client.subscribe(TOPIC , listener);
        System.out.println("| Subscribed to " + TOPIC);
        System.out.println("| Waiting for message...");
    }
}
