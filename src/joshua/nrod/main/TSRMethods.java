//--------------------TSR Methods---------------------------\\
//---------------IGNORE CODE IN THIS FILE-------------------\\
//-----------------INCOMPLETED - V0.01 ---------------------\\

package joshua.nrod.main;

public class TSRMethods {
	public static void TSR_MSG(String TSRMSG) {
		 TSRMSG = TSRMSG.replaceAll("\\{", "");   
			TSRMSG = TSRMSG.replaceAll("\\}", "");  
			TSRMSG = TSRMSG.replaceAll("\'", "");  
			TSRMSG = TSRMSG.replaceAll("CB_MSG:","");   
			 String[] cbContent = TSRMSG.split(",");  
			 
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

}
