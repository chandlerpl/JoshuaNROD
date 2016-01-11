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
				if (ListBody.get(i).contains("'descr'")) {
					
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
			/*	if (ListBody.get(i).startsWith("{'SF_MSG")) {
				 System.out.println("SF_MSG: Out of Area");
			} else if (ListBody.get(i).startsWith("{'SG_MSG")) {
				 System.out.println("SG_MSG: Out of Area");
			 } else if (ListBody.get(i).startsWith("{'SH_MSG")) {
				 System.out.println("SH_MSG: Out of Area");
			} else if (ListBody.get(i).startsWith("{'CA_MSG")) {
				 System.out.println("CA_MSG: Out of Area");
			} else if (ListBody.get(i).startsWith("{'CB_MSG")) {
				 System.out.println("CB_MSG: Out of Area"); 
			} else if (ListBody.get(i).startsWith("{'CT_MSG")) {
				 System.out.println("CT_MSG: Out of Area"); 
			} else if (ListBody.get(i).startsWith("{'CC_MSG")) {
				 System.out.println("CC_MSG: Out of Area"); 
			} else {
				System.out.println(ListBody.get(i));
			} */
			}
			}
			else {
					
				}
			
			} catch(Exception e){
				System.out.println("Exception caught " + e);
			}
				 //System.out.println("A message that has not yet been decoded has arrived.");
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