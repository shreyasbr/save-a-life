package com.server.crud;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertServlet extends HttpServlet {
	MongoClient mongo;
	DB firstDb;
	DBCollection userTable;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			mongo = new MongoClient("localhost", 27017);
			firstDb = mongo.getDB("bloodDonationDB");
			userTable = firstDb.getCollection("userTable");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BasicDBObject dbObject = new BasicDBObject("username",
				request.getParameter("username"))
				.append("fullname", request.getParameter("fullname"))
				.append("bloodgroup", request.getParameter("bloodgroup"))
				.append("city", request.getParameter("city"));
		userTable.insert(dbObject);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
