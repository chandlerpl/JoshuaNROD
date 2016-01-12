package joshua.nrod.main;

import java.util.Map;

import joshua.nrod.stomp.Listener;

public class MyMVTListener implements Listener {

	@Override
	public void message(Map header, String body) {
		System.out.println(header);
		System.out.println(body);
	}
}
