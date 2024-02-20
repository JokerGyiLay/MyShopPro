package com.shop.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnector {
	
	private static DBConnector instance;
	private Properties pro;
	private DBConnector() {
		pro = new Properties();
	}
	public static DBConnector getInstance() {
		return instance = instance == null ? new DBConnector(): instance;
		
	}
	public Connection getConnection () {
		try(InputStream in = new FileInputStream("config.properties")){
			pro.load(in);
			return DriverManager.getConnection(pro.getProperty("db.url"),
					                           pro.getProperty("db.usr"),
					                           pro.getProperty("db.pass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
