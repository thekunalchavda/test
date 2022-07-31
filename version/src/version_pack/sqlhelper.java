package version_pack;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sqlhelper {

	//public static Connection connObj;
	public static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=test;integratedSecurity=true";

	public static void getDbConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionUrl);
			Statement statement = connection.createStatement();
			if(connection != null) {
				System.out.println("hi");
				
				ResultSet resultSet = null;
				String selectSql = "SELECT * from emp";
	            resultSet = statement.executeQuery(selectSql);
     	        while (resultSet.next()) {
	            	System.out.println("helo");
	                System.out.println(resultSet.getInt(1));
	                System.out.println(resultSet.getString(1)+ "   "+ resultSet.getString(2) +"   " +resultSet.getString(3));
	            }
			}
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		System.out.println("executed");
	}

	public static void main(String[] args) {
		getDbConnection();
	}
}