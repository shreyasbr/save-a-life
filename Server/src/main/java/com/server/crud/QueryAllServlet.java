package com.server.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import org.json.JSONObject;

public class QueryAllServlet extends HttpServlet {
	MongoClient mongo;
	DB firstDb;
	DBCollection requiredTable;

	// ArrayList<JSONObject> jsonObjectList;

	@Override
	public void init() throws ServletException {
		// jsonObjectList = new ArrayList<JSONObject>();
		// TODO Auto-generated method stub
		try {
			mongo = new MongoClient("localhost", 27017);
			firstDb = mongo.getDB("bloodDonationDB");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		StringBuilder responseString = new StringBuilder();
		requiredTable = firstDb.getCollection(req.getParameter("tableName"));
		DBCursor cursor = requiredTable.find();
		while (cursor.hasNext()) {
			JSONObject jobj = new JSONObject(JSON.serialize(cursor.next()));
			writer.println(jobj.get("name").toString());
			// responseString.append(jobj.get("name").toString() + "\n");
			// jsonObjectList.add(jobj);
			// System.out.println(JSON.serialize(cursor.next()));
		}
		// writer.print(responseString);
	}

}
