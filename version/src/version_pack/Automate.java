package version_pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Automate {

	public static String linkurl,unm,pwd,year,trust;
	public static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=test;integratedSecurity=true";
    static Connection connection;
	static Statement statement; 
	static ResultSet rs1;
	static WebDriver driver ;
	
	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub
		makeConnection();
		readParameters();
		login();
	}

	public static void makeConnection()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionUrl);
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			} 
		catch(Exception sqlException)
			{
			sqlException.printStackTrace();
			}
	}
	
	public static void readParameters() throws SQLException
	{
	if(connection!=null)
		{
			//String sql="select * from automate where id = (select max(id) from automate)";
			String sql="select * from automate";
		
			rs1 = statement.executeQuery(sql);
			if(rs1.last())
			{
				linkurl = rs1.getString(2);
				unm = rs1.getString(3);
				pwd = rs1.getString(4);
				trust = rs1.getString(5);
				year = rs1.getString(6);
				
				System.out.println(linkurl);
				System.out.println(year);
				
			}
		}
	}
	
	public static void login() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	
		 try {
			 driver.get(linkurl+"/login");
			 driver.findElement(By.name("txtUsername")).sendKeys(unm);
			 }
			 
			 catch (Exception e)
			 {
				 //driver.close();
				 System.out.println("some exception occured :::()");
			 }
			 driver.findElement(By.name("txtPassword")).sendKeys(pwd);
		 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 driver.findElement(By.id("btnLogin")).click();
		 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		  	Select sel1 = new Select(driver.findElement(By.id("ddlTrustName")));
			sel1.selectByVisibleText(trust);
			Thread.sleep(4000);
			
		 	Select sel2 = new Select(driver.findElement(By.id("ddlYear")));
			sel2.selectByVisibleText(year);
			Thread.sleep(4000);
			
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
			String cururl = driver.getCurrentUrl();
			
			driver.findElement(By.id("btnOk")).click();
			
			if(cururl != linkurl+"/DashBoard")
			driver.navigate().to(linkurl+"/DashBoard");
				
	}
	
	
}
