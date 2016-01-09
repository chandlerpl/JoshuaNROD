package joshua.nrod.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import joshua.nrod.stomp.Listener;

public class MyListener implements Listener {
	public void message(Map header, String body) {
    
        System.out.println("| Message Recieved: " + header);
        
      	final List<String> ListBody = new ArrayList<String>();
           
      	int s = 0;
      	
      	System.out.println("| Message Recieved: " + body);
              	
      	while(s != -1) {
      		int t = body.indexOf(".");
      		s = body.indexOf("},{");
      		
      		ListBody.add(body.substring(t+1, s+1));
      		    		
      		body = body.substring(s+2);
      	}
      	
      	ListBody.add(body);
      	
       for(int i = 0; i < ListBody.size(); i++) {
    	   if(ListBody.get(i).length() == 0) {
    		   ListBody.remove(i);
    	   }
    	   
    		System.out.println("| Message Recieved: " + ListBody.get(i));
       }
    }
}


