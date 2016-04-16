package com.soundconnect.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.soundconnect.Beans.Audio;
import com.soundconnect.constants.Constants;

@Service
public class VKAudioServiceImpl implements VKAudioService{

	public List<Audio> searchForAudio(String query) throws IOException {
String url = "https://api.vk.com/method/audio.search?q="+query+"&access_token="+Constants.ACCESS_TOKEN;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}		
		//parsing response!
		StringBuffer buff = new StringBuffer();
        List<Audio> audioresp = new ArrayList<Audio>();
		try {
		JSONParser parser = new JSONParser();
        JSONObject jsonResponse;
			jsonResponse = (JSONObject) parser.parse(response.toString());
        JSONArray mp3list = (JSONArray) jsonResponse.get("response");
        for (int i=1; i<mp3list.size(); i++) {
            JSONObject mp3 = (JSONObject) mp3list.get(i);
            //test
            System.out.println((Long) mp3.get("aid")+' '+new URL((String) mp3.get("url")).toString()+(Long)mp3.get("duration")+(String)mp3.get("title")+(String) mp3.get("artist")+(Long)mp3.get("genre"));
            audioresp.add(new Audio((Long) mp3.get("aid"), new URL(((String) mp3.get("url")).substring(0, ((String)mp3.get("url")).indexOf("?"))), (Long)mp3.get("duration"), (String)mp3.get("title"), (String) mp3.get("artist"), ((Long)mp3.get("genre")!=null)?(Long)mp3.get("genre"):0));
            //control
           // System.out.println(mp3.get("artist")+" - "+mp3.get("title")+"\n src: "+mp3.get("url"));
        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
		return audioresp;
	}

}
