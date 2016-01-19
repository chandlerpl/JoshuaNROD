package joshua.nrod.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import joshua.nrod.stomp.Listener;

public class MVTListener implements Listener {

	@Override
	public void message(Map header, String body) {
//		System.out.println(header);
		System.out.println(body);
		
		final List<String> ListBodyMVT = new ArrayList<String>();
		   
		int s = 0;
      	
		while(s != -1) {
			int t = body.indexOf(".");
			s = body.indexOf("}},{");
  		   
			if(s == -1) {				
				body = body.substring(s+1, body.length()-1).replaceAll("\"", "'");
				ListBodyMVT.add(body);
			} else {
				if(body.contains("[")) {
					
					String body1= body.substring(t+2, s+1).replaceAll("\"", "'");
					
					ListBodyMVT.add(body1);
					body = body.substring(s+2);
				} else {
					String body1= body.substring(t+1, s+1).replaceAll("\"", "'");
				
					ListBodyMVT.add(body1);
					body = body.substring(s+2);
				}
			}
		}
		
		for(int i = 0; i < ListBodyMVT.size(); i++) {
			try{
				Thread.sleep(100);
				String ListBodyMVT1 = ListBodyMVT.get(i);
				ListBodyMVT1 = ListBodyMVT1.replaceAll(":''", ":'null'");
  	
				if (ListBodyMVT1.contains("'toc_id':'64'")) {
					if (ListBodyMVT1.contains("msg_type':'0003'")) {
						MVTMethods.MVT_0003_MSG(ListBodyMVT1);
						} /*else if (ListBodyMVT.get(i).contains("msg_type':'0002'")) {
							MVTMethods.MVT_0002_MSG(ListBodyMVT.get(i));
						} else if (ListBodyMVT.get(i).contains("msg_type':'0003'"))  {
							MVTMethods.MVT_0003_MSG(ListBodyMVT.get(i));
						} else if (ListBodyMVT.get(i).contains("msg_type':'0004'")) {
							MVTMethods.MVT_0004_MSG(ListBodyMVT.get(i));
						} else if (ListBodyMVT.get(i).contains("msg_type':'0005'")) {
							MVTMethods.MVT_0005_MSG(ListBodyMVT.get(i));
						} else if (ListBodyMVT.get(i).contains("msg_type':'0006'")) {
							MVTMethods.MVT_0006_MSG(ListBodyMVT.get(i));
						} else if (ListBodyMVT.get(i).contains("msg_type':'0007'")) {
							MVTMethods.MVT_0007_MSG(ListBodyMVT.get(i));
						} else if (ListBodyMVT.get(i).contains("msg_type':'0008'")) {
							MVTMethods.MVT_0008_MSG(ListBodyMVT.get(i));*/
					} else {
				}
		
			 } catch(Exception e){
					System.out.println("Exception caught MVT " + e );
				}
			}
	}
}
