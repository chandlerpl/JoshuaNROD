package joshua.nrod.main;

import joshua.nrod.stomp.Client;
import joshua.nrod.stomp.Listener;

public class main {
	
    private static final String SERVER = "datafeeds.networkrail.co.uk";

    private static final int PORT = 61618;
    
    private static final String VERSION = "v0.1";
    
    public static void main(String[] args) throws Exception {
        new main().go();
        
            }
    
    public void go() throws Exception{
    	System.out.println("| JPLogics - NROD Connection.." + VERSION);
    	System.out.println("| Network Rail Security Token: " + MyAuth.SEC_TOKEN);
    	System.out.println("| Starting connection bond for " + SERVER);
        System.out.println("| Connecting...");
        Client client = new Client(SERVER, PORT, MyAuth.USERNAME, MyAuth.PASSWORD);
        if (client.isConnected()) {
            System.out.println("| Connected to " + SERVER + ":" + PORT);
        } else {
            System.out.println("| Could not connect");
            return;
        }
        System.out.println("| Subscribing...");
        Listener SIGlistener = new MySIGListener();
        Listener MVTlistener = new MyMVTListener();
        
        client.subscribe(MyFeeds.TOPIC_SIG , SIGlistener);
        System.out.println("| Subscribed to " + MyFeeds.TOPIC_SIG_AREA);
        System.out.println("| Waiting for message...");
        
        client.subscribe(MyFeeds.TOPIC_MVT , MVTlistener);
        System.out.println("| Subscribed to " + MyFeeds.TOPIC_MVT_TOC);
        System.out.println("| Waiting for message...");
    }
}
