package joshua.nrod.main;

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
		int CT = CTMSG.indexOf("CT_MSG");
 		String CT1 = CTMSG.substring(CT, CT+6);
		int time = CTMSG.indexOf("time");
		String time1 = CTMSG.substring(time+7, time+20);	
		int area_id = CTMSG.indexOf("area_id");
		String area_id1 = CTMSG.substring(area_id+10, area_id+12);
		int report_time = CTMSG.indexOf("report_time");
		 String report_time1 = CTMSG.substring(report_time+14, report_time+18);
		 
		 java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 
		 System.out.println(CT1 + ": Report Time " + report_time1 + " in area " + area_id1 + " at " + time2);
	}
	
	public static void CB_MSG(String CBMSG) {
		int CB = CBMSG.indexOf("CB_MSG");
 		String CB1 = CBMSG.substring(CB, CB+6);
		int time = CBMSG.indexOf("time");
		String time1 = CBMSG.substring(time+7, time+20);	
		int area_id = CBMSG.indexOf("area_id");
		String area_id1 = CBMSG.substring(area_id+10, area_id+12);
		int descr = CBMSG.indexOf("descr");
		 String descr1 = CBMSG.substring(descr+8, descr+12);
		int from = CBMSG.indexOf("from");
		 String from1 = CBMSG.substring(from+7, from+11);
		
		 java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 
		 System.out.println(CB1 + ": Consist " + descr1 + " in area " + area_id1 + " cancelled from " + from1 + " at " + time2);
}
	
	public static void SH_MSG(String SHMSG) {	
		SHMSG = SHMSG.replaceAll("\\{", "");   // strip {
		SHMSG = SHMSG.replaceAll("\\}", "");  // strip }
		SHMSG = SHMSG.replaceAll("\'", "");  //strip single quotes
		SHMSG = SHMSG.replaceAll("SH_MSG:",""); //strip the header   
		//time:1452473490000,area_id:SK,address:71,msg_type:SF,data:EA   -- what the new string is!
		 String[] sfContent = SHMSG.split(",");  // split each value at the commas
		 
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
		SFMSG = SFMSG.replaceAll("\\{", "");   // strip {
		SFMSG = SFMSG.replaceAll("\\}", "");  // strip }
		SFMSG = SFMSG.replaceAll("\'", "");  //strip single quotes
		SFMSG = SFMSG.replaceAll("SF_MSG:",""); //strip the header   
		//time:1452473490000,area_id:SK,address:71,msg_type:SF,data:EA   -- what the new string is!
		 String[] sfContent = SFMSG.split(",");  // split each value at the commas
		 
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
		 
		 Long timeStamp = Long.parseLong(time);
		 
		 java.util.Date time1 = new java.util.Date((long)timeStamp);
		 
		 System.out.println(msg_type + ": Signal in area " + area + " was updated. (" + address + ", " + data + ", " + time1 + ")");
	}


	public static void SG_MSG(String SGMSG) {
		SGMSG = SGMSG.replaceAll("\\{", "");   // strip {
		SGMSG = SGMSG.replaceAll("\\}", "");  // strip }
		SGMSG = SGMSG.replaceAll("\'", "");  //strip single quotes
		SGMSG = SGMSG.replaceAll("SG_MSG:",""); //strip the header   
		//time:1452473490000,area_id:SK,address:71,msg_type:SF,data:EA   -- what the new string is!
		 String[] sfContent = SGMSG.split(",");  // split each value at the commas
		 
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
		 
		 Long timeStamp = Long.parseLong(time);
		 
		 java.util.Date time1 = new java.util.Date((long)timeStamp);
		 
	System.out.println(msg_type + ": Signal in area " + area + " was refreshed. (" + address + ", " + data + ", " + time1 + ")");	 

	}
	
}
