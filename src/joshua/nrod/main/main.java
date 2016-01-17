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
    	System.gc();
    	System.out.println("| JPLogics - NROD Connection.." + VERSION);
    	System.out.println("| Network Rail Security Token: " + Auth.SEC_TOKEN);
    	System.out.println("| Starting connection bond for " + SERVER);
        System.out.println("| Connecting...");
        Client client = new Client(SERVER, PORT, Auth.USERNAME, Auth.PASSWORD);
        if (client.isConnected()) {
            System.out.println("| Connected to " + SERVER + ":" + PORT);
        } else {
            System.out.println("| Could not connect");
            return;
        }
        System.out.println("| Subscribing...");
        Listener SIGlistener = new SIGListener();
        Listener MVTlistener = new MVTListener();
        Listener TSRlistener = new TSRListener();
        Listener RTPMlistener = new RTPMListener();
        
        client.subscribe(Feeds.TOPIC_SIG , SIGlistener);
        System.out.println("| Subscribed to " + Feeds.TOPIC_SIG_AREA);
        client.subscribe(Feeds.TOPIC_MVT , MVTlistener);
        System.out.println("| Subscribed to " + Feeds.TOPIC_MVT_TOC);
        client.subscribe(Feeds.TOPIC_TSR , TSRlistener);
        System.out.println("| Subscribed to " + Feeds.TOPIC_TSR_AREA);
        client.subscribe(Feeds.TOPIC_RTTPM , RTPMlistener);
        System.out.println("| Subscribed to " + Feeds.TOPIC_RTTPM_ALL);
        
        System.out.println("| Waiting for messages...");
    }
}
