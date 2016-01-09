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
      	
		while(s != -1) {
			int t = body.indexOf(".");
			s = body.indexOf("},{");
  		   
			if(s == -1) {
				body = body.substring(s+1, body.length()-1);
				ListBody.add(body);
			} else {
				if(body.contains("[")) {
					ListBody.add(body.substring(t+2, s+1));
					body = body.substring(s+2);
				} else {
					ListBody.add(body.substring(t+1, s+1));
					body = body.substring(s+2);
				}
			}
		}
  	
		for(int i = 0; i < ListBody.size(); i++) {
	   		System.out.println("| Message Recieved: " + ListBody.get(i));
       }
    }
}