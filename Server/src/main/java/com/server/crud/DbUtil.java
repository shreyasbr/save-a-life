package com.server.crud;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DbUtil {
	private static MongoClient mongo;
	private static DB dbInstance;
	private static DBCollection tableInstance;

	public static DB getDb(String dbName) {
		if (dbInstance == null) {
			try {
				mongo = new MongoClient("localhost", 27017);
				dbInstance = mongo.getDB(dbName);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dbInstance;
	}

	public static DBCollection getTable(String tableName,String dbName) {
		tableInstance = getDb(dbName).getCollection(tableName);
		return tableInstance;
	}

}
