package joshua.nrod.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import joshua.nrod.stomp.Listener;

public class MyListener implements Listener {
	
	public void message(Map header, String body) {
    
		//System.out.println("| Message Received: " + header);
		//System.out.println("| Message Received: " + body);
		final List<String> ListBody = new ArrayList<String>();
   
		int s = 0;
      	
		while(s != -1) {
			int t = body.indexOf(".");
			s = body.indexOf("},{");
  		   
			if(s == -1) {				
				body = body.substring(s+1, body.length()-1).replaceAll("\"", "'");
				ListBody.add(body);
			} else {
				if(body.contains("[")) {
					
					String body1= body.substring(t+2, s+1).replaceAll("\"", "'");
					
					ListBody.add(body1);
					body = body.substring(s+2);
				} else {
					String body1= body.substring(t+1, s+1).replaceAll("\"", "'");
				
					ListBody.add(body1);
					body = body.substring(s+2);
				}
			}
		}
  	
		 for(int i = 0; i < ListBody.size(); i++) {
			try{
				Thread.sleep(100);
				
				
			if (ListBody.get(i).contains("'area_id':'SS'")) {
				if (ListBody.get(i).startsWith("{'SF_MSG")) {
					 ListenerMethods.SF_MSG(ListBody.get(i));
					 System.out.println(ListBody.get(i));
				} else if (ListBody.get(i).startsWith("{'SG_MSG")) {
					 ListenerMethods.SG_MSG(ListBody.get(i));
					 System.out.println(ListBody.get(i));
				 } else if (ListBody.get(i).startsWith("{'SH_MSG")) {
					 ListenerMethods.SH_MSG(ListBody.get(i));
					 System.out.println(ListBody.get(i));
				} else if (ListBody.get(i).startsWith("{'CA_MSG")) {
					ListenerMethods.CA_MSG(ListBody.get(i)); 
				} else if (ListBody.get(i).startsWith("{'CB_MSG")) {
					ListenerMethods.CB_MSG(ListBody.get(i)); 
				} else if (ListBody.get(i).startsWith("{'CT_MSG")) {
					ListenerMethods.CT_MSG(ListBody.get(i)); 
				} else if (ListBody.get(i).startsWith("{'CC_MSG")) {
					ListenerMethods.CC_MSG(ListBody.get(i)); 
				} else {
					System.out.println(ListBody.get(i));
				} 
			}
			else { 
				
			}
			
			
			} catch(Exception e){
				System.out.println("Exception caught " + e);
			}
		 }
	 }
}