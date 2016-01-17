package joshua.nrod.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import joshua.nrod.stomp.Listener;
// RTPM_Listener
public class RTPMListener implements Listener {

	// TSR Listener
		public void message(Map header, String body) {
			//System.out.println(header);
		//	System.out.println(body);
			
			final List<String> ListBodyRTPM = new ArrayList<String>();
			   
			int s = 0;
	      	
			while(s != -1) {
				int t = body.indexOf(".");
				s = body.indexOf("},{");
	  		   
				if(s == -1) {				
					body = body.substring(s+1, body.length()-1).replaceAll("\"", "'");
					ListBodyRTPM.add(body);
				} else {
					if(body.contains("[")) {
						
						String body1= body.substring(t+2, s+1).replaceAll("\"", "'");
						
						ListBodyRTPM.add(body1);
						body = body.substring(s+2);
					} else {
						String body1= body.substring(t+1, s+1).replaceAll("\"", "'");
					
						ListBodyRTPM.add(body1);
						body = body.substring(s+2);
					}
				}
			}
			
			for(int i = 0; i < ListBodyRTPM.size(); i++) {
				try{
					Thread.sleep(100);
				System.out.println(ListBodyRTPM.get(i));
				} catch(Exception e){
					System.out.println("Exception caught " + e);
				}	
				}
			}
}
