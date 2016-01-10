package joshua.nrod.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import joshua.nrod.stomp.Listener;

public class MyListener implements Listener {
	
	public void message(Map header, String body) {
    
		System.out.println("| Message Received: " + header);
		// System.out.println("| Message Received: " + body);
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
			// int msg_type = ListBody.get(i).indexOf("msg_type");
			 //	String msg_type1 = ListBody.get(i).substring(msg_type+11, msg_type+13);
			 if (body.contains("SF_MSG")) {
				 ListenerMethods.SF_MSG(ListBody.get(i));
			} else if (body.contains("SG_MSG")) {
				 ListenerMethods.SG_MSG(ListBody.get(i));
			 } else if (body.contains("SH_MSG")) {
				 ListenerMethods.SH_MSG(ListBody.get(i));
			} else if (body.contains("CA_MSG")) {
				ListenerMethods.CA_MSG(ListBody.get(i)); 
			} else if (body.contains("CB_MSG")) {
				ListenerMethods.CB_MSG(ListBody.get(i));
			} else {
				 System.out.println("A message that has not yet been decoded has arrived.");
			}
						
						
						
						
			//	 int CB = ListBody.get(i).indexOf("CB_MSG");
			//	 	String CB1 = ListBody.get(i).substring(CB, CB+6);
			//	 int CC = ListBody.get(i).indexOf("CC_MSG");
			//	 	String CC1 = ListBody.get(i).substring(CC, CC+6);
			//	 int CT = ListBody.get(i).indexOf("CT_MSG");
			//	 	String CT1 = ListBody.get(i).substring(CT, CT+6);
				
				 	
				 	
			//	 int descr = ListBody.get(i).indexOf("descr");
			//	 	String descr1 = ListBody.get(i).substring(descr+8, descr+12);
			//	 int to = ListBody.get(i).indexOf("to");
			//	 	String to1 = ListBody.get(i).substring(to+5, to+9);
			//	 int from = ListBody.get(i).indexOf("from");
			//	 	String from1 = ListBody.get(i).substring(from+7, from+11);
			//	 int area_id = ListBody.get(i).indexOf("area_id");
			//	 	String area_id1 = ListBody.get(i).substring(area_id+10, area_id+12);
				// int time = ListBody.get(i).indexOf("time");
				 //	String time1 = ListBody.get(i).substring(time+7, time+20);
				// int address = ListBody.get(i).indexOf("address");
				 //	String address1 = ListBody.get(i).substring(address+10, address+12);
				// int data = ListBody.get(i).indexOf("data");
				//	String data1 = ListBody.get(i).substring(data+7, data+15);
				// int report_time = ListBody.get(i).indexOf("report_time");
				//	String report_time1 = ListBody.get(i).substring(report_time+7, report_time+20); 
		
					// System.out.println("| " + SG1);
					 //System.out.println("| " + SH1);
					 //System.out.println(" " + CA1 + ": Consist " + descr1 + " moved from " + from1 + " to " + to1 + " in area " + area_id1);
					 //System.out.println("| " + CB1);
					 //System.out.println("| " + CC1);
					// System.out.println("| " + CT1);
			 
       }
    }

}