package com.sof.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.sof.protobuffer.UserProtos.User;

public class Main {

	public static void main(String[] args) throws Exception {
		String USER_AGENT = "Mozilla/5.0";

		String GET_URL = "http://localhost:8080/tutorial-forum-sof/testclient?uname=jayram&password=Testing@123&age=32&set=male";

		String POST_PARAMS = "uname=jayram&password=Testing@123&age=32&set=male";
		
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			User user = User.parseFrom(con.getInputStream());
			// print result
			System.out.println("Response:" + user.toString());
		} else {
			System.out.println("GET request not worked");
		}
	}

}
