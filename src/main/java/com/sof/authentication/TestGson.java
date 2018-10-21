package com.sof.authentication;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestGson {
	
	public static void main(String args[]) {
		JsonObject json = new JsonObject();
		json.addProperty("status", "ok");
		json.addProperty("authtoken", "123221232");
		
		System.out.println(json);
		//System.out.println("JSON.getAsString:" + json.getAsString());
		//System.out.println("JSON.getAsJsonPrimitive().getAsString():" + json.getAsJsonPrimitive().getAsString());
	}

}
