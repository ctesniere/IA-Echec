package fr.esgi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connexion {

	public String Connexion(String _url)
	{
	  try {
 
		URL url = new URL(_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
 
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		String output;
		String s =""; 
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			//System.out.println(output);
			s = s+output;
		}
		
		conn.disconnect(); 
		
		//System.out.println(s);
		return s;
		
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
		
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
	return null;
	  
	 
	  
	}
	 
}
