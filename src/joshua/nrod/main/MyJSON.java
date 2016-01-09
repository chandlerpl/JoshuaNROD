package joshua.nrod.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import joshua.nrod.json.JSONObject;

public class MyJSON {
    public void MyJSON() {
		System.out.println("JSON Script working");
		String jsonString = "Message: " + MyListener.message(body);
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONObject newJSON = jsonObject.getJSONObject("CA_MSG");
		System.out.println("Message: " + jsonString);
		jsonObject = new JSONObject(newJSON.toString());
		String descr = jsonObject.getString("descr");
		String to = jsonObject.getString("to");
        System.out.println("Consist: " + descr + " moved to: " + to);
}
}
