package com.app.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class DbUtil {
	static JSONParser jsonParser = new JSONParser();
	static StringBuilder responseString = new StringBuilder();

	public static List<String> populateListFromDatabase(String tableName) {
		String url = "http://10.0.2.2:8080/BloodDonationDatabaseConnection/queryAll?"
				+ tableName;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tableName", tableName));
		responseString = jsonParser.makeHttpRequest(url, "POST", params);
		String[] responseArray = responseString.toString().split("\n");
		List<String> responseList = convertArrayToList(responseArray);
		return responseList;
	}

	private static List<String> convertArrayToList(String[] stringArray) {
		ArrayList<String> stringList = new ArrayList<String>();
		for (int i = 0; i < stringArray.length; i++) {
			stringList.add(stringArray[i]);
		}
		return stringList;
	}
}
