package joshua.nrod.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import joshua.nrod.stomp.Listener;

public class MySIGListener implements Listener {
	
	public void message(Map header, String body) {
    
		//System.out.println("| Message Received: " + header);
		//System.out.println("| Message Received: " + body);
		final List<String> ListBodySIG = new ArrayList<String>();
   
		int s = 0;
      	
		while(s != -1) {
			int t = body.indexOf(".");
			s = body.indexOf("},{");
  		   
			if(s == -1) {				
				body = body.substring(s+1, body.length()-1).replaceAll("\"", "'");
				ListBodySIG.add(body);
			} else {
				if(body.contains("[")) {
					
					String body1= body.substring(t+2, s+1).replaceAll("\"", "'");
					
					ListBodySIG.add(body1);
					body = body.substring(s+2);
				} else {
					String body1= body.substring(t+1, s+1).replaceAll("\"", "'");
				
					ListBodySIG.add(body1);
					body = body.substring(s+2);
				}
			}
		}
  	
		 for(int i = 0; i < ListBodySIG.size(); i++) {
			try{
				Thread.sleep(100);
				
				
			if (ListBodySIG.get(i).contains("'area_id':'SS'")) {
				//if (ListBodySIG.get(i).contains("'address'':'")) {
					if (ListBodySIG.get(i).startsWith("{'SF_MSG")) {
						SIGMethods.SF_MSG(ListBodySIG.get(i));
					// System.out.println(ListBodySIG.get(i));
					} else if (ListBodySIG.get(i).startsWith("{'SG_MSG")) {
						SIGMethods.SG_MSG(ListBodySIG.get(i));
					// System.out.println(ListBody.get(i));
					} else if (ListBodySIG.get(i).startsWith("{'SH_MSG")) {
						SIGMethods.SH_MSG(ListBodySIG.get(i));
					// System.out.println(ListBody.get(i));
					} else {
					} 
				} else {
				}	
					
				if (ListBodySIG.get(i).contains("'area_id':'SS'")) {
					if (ListBodySIG.get(i).contains("'descr'")) {
						if (ListBodySIG.get(i).startsWith("{'CA_MSG")) {
					 		SIGMethods.CA_MSG(ListBodySIG.get(i)); 
					 	} else if (ListBodySIG.get(i).startsWith("{'CB_MSG")) {
					 		SIGMethods.CB_MSG(ListBodySIG.get(i)); 
					 	} else if (ListBodySIG.get(i).startsWith("{'CT_MSG")) {
					 		SIGMethods.CT_MSG(ListBodySIG.get(i)); 
					 	} else if (ListBodySIG.get(i).startsWith("{'CC_MSG")) {
					 		SIGMethods.CC_MSG(ListBodySIG.get(i)); 
					 	} else {
					 		
					 	}
						//System.out.println(ListBodySIG.get(i));
					} else {
				}			
			}
			else { 
			}
		//	}	
			} catch(Exception e){
				System.out.println("Exception caught " + e);
			}
		 }
	 }
}