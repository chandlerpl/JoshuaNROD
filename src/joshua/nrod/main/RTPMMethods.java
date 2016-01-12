package joshua.nrod.main;

public class RTPMMethods {
	public static void RTPM_MSG(String RTPMMSG) {
		int code = RTPMMSG.indexOf("code");
 		String code1 = RTPMMSG.substring(code+7, code+9);
		int name = RTPMMSG.indexOf("name");
		String name1 = RTPMMSG.substring(name+7, +17);	
		int total = RTPMMSG.indexOf("total");
		String total1 = RTPMMSG.substring(total+8, total+11);
		int rag = RTPMMSG.indexOf("rag");
		 String rag1 = RTPMMSG.substring(rag+6, rag+7);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println(name1 + " (" + code1 +" ): Currently has a " + rag1 + " performance with a total of " + total1 + " trains");
		 	}
}
