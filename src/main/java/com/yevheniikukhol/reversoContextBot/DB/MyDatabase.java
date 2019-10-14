package com.yevheniikukhol.reversoContextBot.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MyDatabase {

	private static String table;
	private static Connection conn;
	private static Statement statement;

	public MyDatabase(String tab) {
		table = tab;
	}

	public static void connect() throws SQLException{
		String url = "jdbc:sqlite:telegramTest.db";
		conn = DriverManager.getConnection(url);
		statement = conn.createStatement();
	}

	public static void write(HashMap<String,String> fieldValueMap) throws SQLException{
		HashMap<String, String> params = getQueryParams(fieldValueMap);

		statement.execute("INSERT INTO '"+ table +"' ("+ params.get("fields") +") VALUES ("+ params.get("values") +")");
	}


	public static ResultSet getField(String fields) throws SQLException{

		return statement.executeQuery("SELECT " + fields +" FROM " + table);
	}

	public static ResultSet getField(String fields, String where) throws SQLException{

		return statement.executeQuery("SELECT " + fields +" FROM " + table + " WHERE "+ where);
	}

	public static int getCount() throws SQLException{

		return statement.executeQuery("SELECT COUNT(*) AS count FROM "+ table).getInt("count");
	}
	public static int getCount(String where) throws SQLException{

		return statement.executeQuery("SELECT COUNT(*) AS count FROM "+ table + " WHERE " + where).getInt("count");
	}

	public static void update(String set, String where) throws SQLException {
		statement.execute("UPDATE "+ table + " SET  " + set + " WHERE id=1");
	}



	private static HashMap<String, String> getQueryParams(HashMap<String,String> fieldValueMap) {
		HashMap<String, String> params = new HashMap<>();

		String fields = "";
		String values = "";
		int i = 0;
		for (Map.Entry<String, String> entry: fieldValueMap.entrySet()) {
			if (i > 0) {
				fields += ", " + "'" + entry.getKey() + "'";
				values += ", " + "'" + entry.getValue() + "'";
			}else{
				fields += "'" + entry.getKey() + "'";
				values += "'" + entry.getValue() + "'";
			}
			i++;
		}

		params.put("fields", fields);
		params.put("values", values);
		return params;
	}



}