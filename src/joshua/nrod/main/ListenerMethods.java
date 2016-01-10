package joshua.nrod.main;

import java.text.SimpleDateFormat;

public class ListenerMethods {
		
	public static void CA_MSG(String CAMSG) {
		SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss");
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
		//System.out.println(CA1 + ": Consist " + descr1 + " in area " + area_id1 + " moved from " + from1 + " to " + to1 + " at " + TimeFormat.format(time1));
		System.out.println(CA1 + ": Consist " + descr1 + " in area " + area_id1 + " moved from " + from1 + " to " + to1 + " at " + time1);
	}
	
	
	public static void SH_MSG(String SHMSG) {
		int SH = SHMSG.indexOf("SH_MSG");
	 	String SH1 = SHMSG.substring(SH, SH+6);
	int time = SHMSG.indexOf("time");
		String time1 = SHMSG.substring(time+7, time+20);	
	int area_id = SHMSG.indexOf("area_id");
	 	String area_id1 = SHMSG.substring(area_id+10, area_id+12);	
	int address = SHMSG.indexOf("address");
		String address1 = SHMSG.substring(address+10, address+12); 	
	int data = SHMSG.indexOf("data");
		String data1 = SHMSG.substring(data+7, data+15); 	
	System.out.println(SH1 + ": Signal in area " + area_id1 + "was Finished. (" + address1 + ", " + data1 + ", " + time1 + ")");	
}


	public static void SF_MSG(String SFMSG) {
		int SF = SFMSG.indexOf("SF_MSG");
	 	String SF1 = SFMSG.substring(SF, SF+6);
	int time = SFMSG.indexOf("time");
		String time1 = SFMSG.substring(time+7, time+20);	
	int area_id = SFMSG.indexOf("area_id");
	 	String area_id1 = SFMSG.substring(area_id+10, area_id+12);	
	int address = SFMSG.indexOf("address");
		String address1 = SFMSG.substring(address+10, address+12); 	
	int data = SFMSG.indexOf("data");
		String data1 = SFMSG.substring(data+7, data+15); 	
	System.out.println(SF1 + ":Signal in area " + area_id1 + "was updated. (" + address1 + ", " + data1 + ", " + time1 + ")");
	}


	public static void SG_MSG(String SGMSG) {
		int SG = SGMSG.indexOf("SG_MSG");
	 	String SG1 = SGMSG.substring(SG, SG+6);
	int time = SGMSG.indexOf("time");
		String time1 = SGMSG.substring(time+7, time+20);	
	int area_id = SGMSG.indexOf("area_id");
	 	String area_id1 = SGMSG.substring(area_id+10, area_id+12);	
	int address = SGMSG.indexOf("address");
		String address1 = SGMSG.substring(address+10, address+12); 	
	int data = SGMSG.indexOf("data");
		String data1 = SGMSG.substring(data+7, data+15); 	
	System.out.println(SG1 + ": Signal in area " + area_id1 + "was refreshed. (" + address1 + ", " + data1 + ", " + time1 + ")");	 

	}
	
}
