package joshua.nrod.main;
// Copyright Joshua Pope-Lewis
public class ListenerMethods {
		
	public static void CA_MSG(String CAMSG) {
		int CA = CAMSG.indexOf("CA_MSG");
 		String CA1 = CAMSG.substring(CA, CA+6);
		int time = CAMSG.indexOf("time");
		String time1 = CAMSG.substring(time+7, time+20);	
		int area_id = CAMSG.indexOf("area_id");
		String area_id1 = CAMSG.substring(area_id+10, area_id+12);
		int descr = CAMSG.indexOf("descr");
		 String descr1 = CAMSG.substring(descr+8, descr+12);
		int to = CAMSG.indexOf("to");
		 String to1 = CAMSG.substring(to+5, to+9);
		int from = CAMSG.indexOf("from");
		 String from1 = CAMSG.substring(from+7, from+11);

		 java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 
		 System.out.println(CA1 + ": Consist " + descr1 + " in area " + area_id1 + " moved from " + from1 + " to " + to1 + " at " + time2);
	}
	
	public static void CC_MSG(String CCMSG) {
		int CC = CCMSG.indexOf("CC_MSG");
 		String CC1 = CCMSG.substring(CC, CC+6);
		int time = CCMSG.indexOf("time");
		String time1 = CCMSG.substring(time+7, time+20);	
		int area_id = CCMSG.indexOf("area_id");
		String area_id1 = CCMSG.substring(area_id+10, area_id+12);
		int descr = CCMSG.indexOf("descr");
		 String descr1 = CCMSG.substring(descr+8, descr+12);
		int to = CCMSG.indexOf("to");
		 String to1 = CCMSG.substring(to+5, to+9);

		 java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 
		 System.out.println(CC1 + ": Consist " + descr1 + " in area " + area_id1 + " entered into " + to1 + " at " + time2);
	}
	
		 public static void CT_MSG(String CTMSG) {
			 CTMSG = CTMSG.replaceAll("\\{", "");   // strip {
				CTMSG = CTMSG.replaceAll("\\}", "");  // strip }
				CTMSG = CTMSG.replaceAll("\'", "");  //strip single quotes
				CTMSG = CTMSG.replaceAll("CT_MSG:",""); //strip the header   
				//time:1452473490000,area_id:SK,address:71,msg_type:SF,data:EA   -- what the new string is!
				 String[] ctContent = CTMSG.split(",");  // split each value at the commas
				 
				 String time = "";
				 String area = "";
				 String report_time = "";
				 String msg_type = "";

				 
				 
				 for(int i = 0; i < ctContent.length;i++) {
				  String[] ctLine = ctContent[i].split(":");   //now split the line at the colon
				  String tag = ctLine[0];
				  String val = ctLine[1];
				  
				  if(tag.equalsIgnoreCase("time")) {
					  time = val;
				  } else if(tag.equalsIgnoreCase("area_id")) {
					  area = val;
				  } else if(tag.equalsIgnoreCase("report_time")) {
					  report_time = val;
				  } else if(tag.equalsIgnoreCase("msg_type")) {
					  msg_type = val;
				  }
				  
				  }		 
				 java.util.Date time1 = new java.util.Date(Long.parseLong(time));


		 System.out.println(msg_type + "_MSG: Area " + area + " reported in " + report_time + " at " + time1);
	}
	
	public static void CB_MSG(String CBMSG) {
		 CBMSG = CBMSG.replaceAll("\\{", "");   
			CBMSG = CBMSG.replaceAll("\\}", "");  
			CBMSG = CBMSG.replaceAll("\'", "");  
			CBMSG = CBMSG.replaceAll("CB_MSG:","");   
			 String[] cbContent = CBMSG.split(",");  
			 
			 String time = "";
			 String area = "";
			 String descr = "";
			 String from = "";
			 String msg_type = "";

			 
			 
			 for(int i = 0; i < cbContent.length;i++) {
			  String[] cbLine = cbContent[i].split(":");  
			  String tag = cbLine[0];
			  String val = cbLine[1];
			  
			  if(tag.equalsIgnoreCase("time")) {
				  time = val;
			  } else if(tag.equalsIgnoreCase("area_id")) {
				  area = val;
			  } else if(tag.equalsIgnoreCase("descr")) {
				  descr = val;
			  } else if(tag.equalsIgnoreCase("from")) {
				  from = val;
			  } else if(tag.equalsIgnoreCase("msg_type")) {
				  msg_type = val;
			  }
			  
			  }		 
			 java.util.Date time1 = new java.util.Date(Long.parseLong(time));


		 System.out.println(msg_type + "_MSG: Consist " + descr + " in area " + area + " cancelled from " + from + " at " + time1);
}
	
	public static void SH_MSG(String SHMSG) {	
		SHMSG = SHMSG.replaceAll("\\{", "");  
		SHMSG = SHMSG.replaceAll("\\}", "");  
		SHMSG = SHMSG.replaceAll("\'", "");  
		SHMSG = SHMSG.replaceAll("SH_MSG:",""); 
		 String[] sfContent = SHMSG.split(","); 
		 
		 String time = "";
		 String area = "";
		 String address = "";
		 String msg_type = "";
		 String data = "";
		 
		 
		 for(int i = 0; i < sfContent.length;i++) {
		  String[] sfLine = sfContent[i].split(":");   //now split the line at the colon
		  String tag = sfLine[0];
		  String val = sfLine[1];
		  
		  if(tag.equalsIgnoreCase("time")) {
			  time = val;
		  } else if(tag.equalsIgnoreCase("area_id")) {
			  area = val;
		  } else if(tag.equalsIgnoreCase("address")) {
			  address = val;
		  } else if(tag.equalsIgnoreCase("data")) {
			  data = val;
		  } else if(tag.equalsIgnoreCase("msg_type")) {
			  msg_type = val;
		  }
		  
		  }		 
		 java.util.Date time1 = new java.util.Date(Long.parseLong(time));
		 		 
		System.out.println(msg_type + ": Signal in area " + area + " was Finished. (" + address + ", " + data + ", " + time1 + ")");	

	}


	public static void SF_MSG(String SFMSG) {
		SFMSG = SFMSG.replaceAll("\\{", "");    
		SFMSG = SFMSG.replaceAll("\\}", ""); 
		SFMSG = SFMSG.replaceAll("\'", "");  
		SFMSG = SFMSG.replaceAll("SF_MSG:","");   
		 String[] sfContent = SFMSG.split(",");  
		 
		 String time = "";
		 String area = "";
		 String address = "";
		 String msg_type = "";
		 String data = "";
		 
		 
		 for(int i = 0; i < sfContent.length;i++) {
		  String[] sfLine = sfContent[i].split(":");  
		  String tag = sfLine[0];
		  String val = sfLine[1];
		  
		  if(tag.equalsIgnoreCase("time")) {
			  time = val;
		  } else if(tag.equalsIgnoreCase("area_id")) {
			  area = val;
		  } else if(tag.equalsIgnoreCase("address")) {
			  address = val;
		  } else if(tag.equalsIgnoreCase("data")) {
			  data = val;
		  } else if(tag.equalsIgnoreCase("msg_type")) {
			  msg_type = val;
		  }
		  
		  }
		 
		 Long timeStamp = Long.parseLong(time);
		 
		 java.util.Date time1 = new java.util.Date((long)timeStamp);
		 
		 System.out.println(msg_type + ": Signal in area " + area + " was updated. (" + address + ", " + data + ", " + time1 + ")");
	}


	public static void SG_MSG(String SGMSG) {
		SGMSG = SGMSG.replaceAll("\\{", "");   
		SGMSG = SGMSG.replaceAll("\\}", "");  
		SGMSG = SGMSG.replaceAll("\'", "");  
		SGMSG = SGMSG.replaceAll("SG_MSG:",""); 
		 String[] sfContent = SGMSG.split(",");
		 
		 String time = "";
		 String area = "";
		 String address = "";
		 String msg_type = "";
		 String data = "";
		 
		 
		 for(int i = 0; i < sfContent.length;i++) {
		  String[] sfLine = sfContent[i].split(":");   
		  String tag = sfLine[0];
		  String val = sfLine[1];
		  
		  if(tag.equalsIgnoreCase("time")) {
			  time = val;
		  } else if(tag.equalsIgnoreCase("area_id")) {
			  area = val;
		  } else if(tag.equalsIgnoreCase("address")) {
			  address = val;
		  } else if(tag.equalsIgnoreCase("data")) {
			  data = val;
		  } else if(tag.equalsIgnoreCase("msg_type")) {
			  msg_type = val;
		  }
		  
		  }
		 
		 Long timeStamp = Long.parseLong(time);
		 
		 java.util.Date time1 = new java.util.Date((long)timeStamp);
		 
	System.out.println(msg_type + ": Signal in area " + area + " was refreshed. (" + address + ", " + data + ", " + time1 + ")");	 

	}
	
}
