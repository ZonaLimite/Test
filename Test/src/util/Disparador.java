package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Disparador {

	
	public static void main(String[] args) {
		int status =0;
		StringBuffer content=null;
		URL url=null;
		try {
			url = new URL("http://192.168.1.46/GatewayJava.war/rest-api/services/Report-GET?Informe=d:\\MIS\\ReportManager\\Data\\my_export.pdu");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");

		status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		content = new StringBuffer();
		while((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Response status: " + status);
		System.out.println(content.toString());
    }
	
	
}
