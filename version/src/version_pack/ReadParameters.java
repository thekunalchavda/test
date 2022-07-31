package version_pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadParameters {

	public static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=test;integratedSecurity=true";
    static Connection connection;
	static Statement statement; 
	static ResultSet rs1,rs2,rs3,rs4;
	static String url,uname,pword,year,trust; 
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		makeConnection();
		getAllParameters();
		showValues();
		
	}
	
	public static void makeConnection()
	{
		try {
			//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());   
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionUrl);
			statement = connection.createStatement();
			
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public static void getAllParameters() throws SQLException
	{
		if(connection!=null)
		{
			String sql="select * from automate";
			rs1 = statement.executeQuery(sql);
			if(rs1.last())
			{
				url = rs1.getString(2);
				uname = rs1.getString(3);
				pword = rs1.getString(4);
				trust = rs1.getString(5);
				year = rs1.getString(6);
			}
			else
			{
				confirmbox cf = new confirmbox();
				cf.showbox("Record not found");
			}
		}
		else
		{
			makeConnection();
		}
	}
	
	public static void showValues()
	{
		System.out.println("value of url is :" + url);
		System.out.println("value of username is :" + uname);
		System.out.println("value of password is :" + pword);
		System.out.println("value of trust is :" + trust);
		System.out.println("value of year is :" + year);
		
	}

}
