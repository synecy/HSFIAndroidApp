package com.example.androidrssdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsDownloaderClass {

	public String download( String dataUrl ) throws IOException {
		String inputString = "";
		String receivedString = "";
		try {
			URL url = new URL( dataUrl );
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setReadTimeout(15000);
			urlConnection.setConnectTimeout(5000);
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();
			try {
				BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				while ( (inputString = inputBuffer.readLine()) != null ) {
					receivedString += inputString;
				}
				inputBuffer.close();
				urlConnection.disconnect();
				return receivedString;
			} catch(UnsupportedEncodingException e) {
				return "DownloadError";
			}
		} catch (IOException e) {
			return "DownloadError";		
		}
	}

}


