package joshua.nrod.main;

import java.lang.invoke.MethodHandles.Lookup;

public class MVTMethods {
	public static void MVT_0001_MSG(String MVT_MSG) {
		 MVT_MSG = MVT_MSG.replaceAll("\\{", "");   // strip {
			MVT_MSG = MVT_MSG.replaceAll("\\}", "");  // strip }
			MVT_MSG = MVT_MSG.replaceAll("\'", "");  //strip single quotes
			MVT_MSG = MVT_MSG.replaceAll("CT_MSG:",""); //strip the header   
			//time:1452473490000,area_id:SK,address:71,msg_type:SF,data:EA   -- what the new string is!
			 String[] mvtContent1 = MVT_MSG.split(",");  // split each value at the commas
			 
			 String msg_type = "";
			 String toc_id = "";
			 String report_time = "";
			 

			 
			 
			 for(int i = 0; i < mvtContent1.length;i++) {
			  String[] mvtLine1 = mvtContent1[i].split(":");   //now split the line at the colon
			  String tag = mvtLine1[0];
			  String val = mvtLine1[1];
			  if(val.isEmpty()) {
				  
			  } else {
			  if(tag.equalsIgnoreCase("msg_type")) {
				  msg_type = val;
			  } else if(tag.equalsIgnoreCase("toc_id")) {
				  toc_id = val;
			  } else if(tag.equalsIgnoreCase("report_time")) {
				  report_time = val;
			  }
			  
			  }		
			 }
			 java.util.Date report_time1 = new java.util.Date(Long.parseLong(report_time));


	 System.out.println(msg_type + "_MSG: Operator " + toc_id + " at " + report_time1);

		 	}
	
	public static void MVT_0002_MSG(String MVT_MSG) {
		MVT_MSG = MVT_MSG.replaceAll("\\{", "");   // strip {
		MVT_MSG = MVT_MSG.replaceAll("\\}", "");  // strip }
		MVT_MSG = MVT_MSG.replaceAll("\'", "");  //strip single quotes
		MVT_MSG = MVT_MSG.replaceAll("CT_MSG:",""); //strip the header   
		//time:1452473490000,area_id:SK,address:71,msg_type:SF,data:EA   -- what the new string is!
		 String[] mvtContent2 = MVT_MSG.split(",");  // split each value at the commas
		 
		 String msg_type = "";
		 String toc_id = "";
		 String report_time = "";
		 

		 
		 
		 for(int i = 0; i < mvtContent2.length;i++) {
		  String[] mvtLine2 = mvtContent2[i].split(":");   //now split the line at the colon
		  String tag = mvtLine2[0];
		  String val = mvtLine2[1];
		  if(val.isEmpty()) {
			  
		  } else {
		  if(tag.equalsIgnoreCase("msg_type")) {
			  msg_type = val;
		  } else if(tag.equalsIgnoreCase("toc_id")) {
			  toc_id = val;
		  } else if(tag.equalsIgnoreCase("report_time")) {
			  report_time = val;
		  }
		  }
		  }		 
		 java.util.Date report_time1 = new java.util.Date(Long.parseLong(report_time));


 System.out.println(msg_type + "_MSG: Operator " + toc_id + " at " + report_time1);
	
		 	}

	public static void MVT_0003_MSG(String MVT_MSG) {

		 MVT_MSG = MVT_MSG.replaceAll("\\{", "");   // strip {
		 	MVT_MSG = MVT_MSG.replaceAll(":''", ":'na'");
			MVT_MSG = MVT_MSG.replaceAll("\\}", "");  // strip }
			MVT_MSG = MVT_MSG.replaceAll("\'", "");  //strip single quotes
			MVT_MSG = MVT_MSG.replaceAll("MVT_MSG:",""); //strip the header
			
			System.out.println(MVT_MSG);
			//time:1452473490000,area_id:SK,address:71,msg_type:SF,data:EA   -- what the new string is!
			 String[] mvtContent3 = MVT_MSG.split(",");  // split each value at the commas
			 
			 String msg_type = "";
			 String toc_id = "";
			 String report_time = "";
			 String next_report_stanox = "";
			 String loc_stanox = "";
			 String train_service_code = "";
			 
			 for(int i = 0; i < mvtContent3.length;i++) {
			  String[] mvtLine3 = mvtContent3[i].split(":");   //now split the line at the colon
			  String tag = mvtLine3[0];
			  String val = mvtLine3[1];
			  
			  if(tag.equalsIgnoreCase("msg_type")) {
				  msg_type = val;
			  }  else if(tag.equalsIgnoreCase("toc_id")) {
					   toc_id = val;
			  }  else if(tag.equalsIgnoreCase("report_time")) {
					   report_time = val;
			  }  else if(tag.equalsIgnoreCase("next_report_stanox")) {
					   next_report_stanox = val;
			  }  else if(tag.equalsIgnoreCase("loc_stanox")) {
					   loc_stanox = val;
			  }  else if(tag.equalsIgnoreCase("train_service_code")) {
					   train_service_code = val;
				   
			  	}
			  }
			 
			 java.util.Date report_time1 = new java.util.Date(Long.parseLong(report_time));

			 System.out.println("MVT_MSG " + msg_type + ": Train "+ train_service_code + " operated by TOC " + toc_id +" reported at " + loc_stanox + " next report at "+ next_report_stanox + ". Report sent at " +report_time1 );
			 
	}
	public static void MVT_0004_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 		String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 		String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println("MVT_MSG " + MVT1 + ": "  +MVTToc1);
		 
		 	}
	public static void MVT_0005_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 		String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 		String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println("MVT_MSG: " + MVT1 + ", "+MVTToc1);
		 
		 	}
	public static void MVT_0006_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 		String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 		String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println("MVT_MSG: " + MVT1 + ", "+MVTToc1);
		 
		 	}
	public static void MVT_0007_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 		String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 		String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println("MVT_MSG: " + MVT1 + ", "+MVTToc1);
		 
		 	}
	public static void MVT_0008_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 		String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 		String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println("MVT_MSG: " + MVT1 + ", "+MVTToc1);
		 
		 	}
}
