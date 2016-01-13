package joshua.nrod.main;

import java.lang.invoke.MethodHandles.Lookup;

public class MVTMethods {
	public static void MVT_0001_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 		String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 		String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println("MVT_MSG: " + MVT1 + ", "+MVTToc1);
		 
		 	}
	
	public static void MVT_0002_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 		String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 		String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
		
		// java.util.Date time2 = new java.util.Date(Long.parseLong(time1));
		 System.out.println("MVT_MSG: " + MVT1 + ", "+MVTToc1);
		 
		 	}
	
	public static void MVT_0003_MSG(String MVT_MSG) {
		int MVTType = MVT_MSG.indexOf("msg_type");
 			String MVT1 = MVT_MSG.substring(MVTType+11, MVTType+15);
 		int MVTToc = MVT_MSG.indexOf("toc_id");
 			String MVTToc1 = MVT_MSG.substring(MVTToc+9, MVTToc+11);
 		int MVTTime = MVT_MSG.indexOf("msg_queue_timestamp");
 			String MVTTime1 = MVT_MSG.substring(MVTTime+22, MVTTime+35);
 		int MVTNRS = MVT_MSG.indexOf("next_report_stanox");
 			String MVTNRS1 = MVT_MSG.substring(MVTNRS+21, MVTNRS+26);
 		int MVTRS = MVT_MSG.indexOf("loc_stanox");
 			String MVTRS1 = MVT_MSG.substring(MVTRS+13, MVTRS+18);
 		int MVTTRC = MVT_MSG.indexOf("train_service_code");
 			String MVTTRC1 = MVT_MSG.substring(MVTTRC+21, MVTTRC+29);
 				 
		 java.util.Date MVTTime2 = new java.util.Date(Long.parseLong(MVTTime1));
		 System.out.println("MVT_MSG " + MVT1 + ": Train "+ MVTTRC1 + " operated by TOC " + MVTToc1 +" reported at " + MVTRS1 + " next report at "+ MVTNRS1 + ". Report sent at " +MVTTime2 );
		 
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
