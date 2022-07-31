package version_pack;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.Select;

public class testweb {

	public static String unm;
	public static String pwd;
	public static String year;
	public static WebDriver driver;
	public static int flag=1;
	public static int i=0,j=0;
    public static String  sitepath="http://192.168.1.210:278";
    public static SessionId s=null;
    public static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=test;integratedSecurity=true";
    static Connection connection;
	static Statement statement; 
	static ResultSet resultset,resultset2,resultset3,resultset4;
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		login();
		getDbConnection();
		//operations();
		getdetails();
		//getcheck();
		teardown();
	}

	public void readapi()
	{
		
	}
	
	public static void login() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	
		unm="VERSION";
		pwd="V$R2001";
		 try {
			 driver.get(sitepath+"/Login");
			 driver.findElement(By.name("txtUsername")).sendKeys(unm);
			 //driver.findElement(By.name("txtPassword")).sendKeys(pwd);
			 //driver.findElement(By.id("btnLogin")).click();
			 }
			 
			 catch (Exception e)
			 {
				 //e.printStackTrace();
				// AutoLog.addcontent("website not launched..check internet issue");
				 driver.close();
			 }
			 driver.findElement(By.name("txtPassword")).sendKeys(pwd);
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 driver.findElement(By.id("btnLogin")).click();
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 driver.findElement(By.id("btnOk")).click();
			 
			 
			 Select sel = new Select(driver.findElement(By.id("ddlYear")));
			 WebElement we = sel.getFirstSelectedOption();
			 year = we.getText();
			 
			 System.out.println("value of year is : "+year);
			 
			Thread.sleep(4000);
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
			String cururl = driver.getCurrentUrl();
			if(cururl !=sitepath+"/DashBoard")
			driver.navigate().to(sitepath+"/DashBoard");
			s = ((RemoteWebDriver) driver).getSessionId();
		    System.out.println("Session Id : " + s);
				
}

	
	
public static void teardown()
	{
		if(flag==1)
		{
		
		}
		else
			{driver.quit();
		s=null; }
	}
	
	
public static void checksession() throws InterruptedException 
	{
	// needs to be checked if session is not expired then which condition will be checked further.
	String loginurl = driver.getCurrentUrl().toString();
	System.out.println("current url is : "+loginurl);
	Boolean display;
	//String urlval ="http://192.168.1.210:278/Login";
	try {
		    //this will create issue when session is not expired...  
			display = driver.findElement(By.xpath("//*[@id='btnLogin']")).isDisplayed();
			if(display==true)
			{
			System.out.println("website session expired");
			driver.close();
			login();
			}
		else
			{
			System.out.println("website session is not expired");
			}
		}
	catch(NoSuchElementException e)
		{
			System.out.println("website session not expired");
		}
	}
		
public static void operations() throws InterruptedException {
			Runnable runnable = new Runnable() {
		      public void run() {
		        // task to run goes here
		        System.out.println("Hello !!");
		    	  try {
		    		  System.out.println("entered to the try block");
		  			if(connection != null) {
		  				String updateSql = "update emp set flag=1 where flag=0";
		  				j=statement.executeUpdate(updateSql);
		  		     }
		  		} catch(Exception sqlException) {
		  			sqlException.printStackTrace();
		  		}
		  		System.out.println("executed");
		      }
		    };
		    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		    service.scheduleAtFixedRate(runnable, 0, 20, TimeUnit.SECONDS);
	}
	
public static ArrayList<String> getdata(String searchid,int reqid) throws InterruptedException, SQLException
{
	String name,sbu;
	System.out.println("from getdata method");
	ArrayList<String> list = new ArrayList<String>();
	checksession();
	driver.navigate().to(sitepath+"/EmployeeMst.aspx?title=Employee%20Detail");
	driver.findElement(By.id("ctl00_DefaultContent_Search1_txtEmployeeCode")).sendKeys(searchid);
	 Thread.sleep(1000);
	driver.findElement(By.id("ctl00_DefaultContent_Search1_btnSearch")).click();
	 Thread.sleep(3000);
	 
	//driver.findElement(By.xpath("//table[@class='GridView']/tbody/tr[2]/td[1]/a")).click();
	 
	//String name = driver.findElement(By.id("ctl00_DefaultContent_txtEmployeeName")).getText().toString();
	 name = driver.findElement(By.id("ctl00_DefaultContent_txtEmployeeName")).getAttribute("value");
	
	 Thread.sleep(3000);
	 System.out.println("value of name is :"+name);
	 sbu = driver.findElement(By.id("ctl00_DefaultContent_txtAutoCompSBU")).getAttribute("value");
	 Thread.sleep(3000);
	 System.out.println("value of sbu is :"+sbu);
	 
	 if(name!="" && sbu!="")
	 {
		 list.add(name);
		 list.add(sbu);
	 }
	 else
	 {
		 name="not found";
		 sbu="not found";
		 list.add(name);
		 list.add(sbu);
		 System.out.println("value of not found name  is :"+name);
		 System.out.println("value of not found sbu is :"+sbu);
		 
	 }
		 
	//System.out.println(list);
	 if(connection != null) {
         
			String inSql = "insert into response (reqid,searchid,name,sbu) VALUES ("+reqid+",'"+searchid+"','"+name+"','"+sbu+"')"; 
         //		"values ('"+reqid+'",'"+searchid+"',"'+name+'","'+sbu+'")" ;
			Statement st = connection.createStatement();
			String reqSql = "update request set flag=1 where reqid="+reqid;
			st.executeUpdate(inSql);
			Statement st2 = connection.createStatement();
			st2.executeUpdate(reqSql);
			}
	return list; 
}



public static void getdetails()
	{
		Runnable runnable = new Runnable() {
		      public void run() {
		        System.out.println("inside the runnable schedular");
		    	  try {
		    		  
		    		  System.out.println("entered to the try block");
		    		  if(connection != null) {
		    			  String cntsql = "select count(*) from request ";
		    			  resultset4 = statement.executeQuery(cntsql);
		    			  resultset4.next();
		    			  int count = resultset4.getInt(1);
		    			  if ( count > 0 ) 
		    			  {
		    				  String selectSql = "select searchid,reqid from request where flag=0";
				  				resultset4 = statement.executeQuery(selectSql);
				  			
				  				while(resultset4.next())
				  				{
				  					System.out.println("entered to the while block of resultset");
				  					System.out.println("values of paratmeters---------------------");
				  					System.out.println(resultset4.getString(1));
				  					System.out.println(resultset4.getInt(2));
				  					System.out.println("values of paratmeters---------------------");
				  					ArrayList<String> resplist = new ArrayList<String>();
				  					resplist = getdata(resultset4.getString(1),resultset4.getInt(2));
				  					System.out.println("value in resplist is "+resplist);
				  				}
		    			  }
		    			  else
		    			  {
		    				  System.out.println("executing the sleep part");
		    				  Thread.sleep(5000);
		    			  }
			  				
			  		     }
		  		     }
		  		 catch(Exception sqlException) { sqlException.printStackTrace(); }
		  		System.out.println("executed");
		      }
		    };
		    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		    service.scheduleAtFixedRate(runnable, 0, 20, TimeUnit.SECONDS);
   }
	

public static void getcheck()
{ 
	j=1;
	for(i=0;i<j;i++)
	{
		try {
  		  
  		  System.out.println("entered to the try block");
  		  if(connection != null) {
	  				String selectSql = "select searchid,reqid from request where flag=0";
	  				resultset4 = statement.executeQuery(selectSql);
	  				while(resultset4.next())
	  				{
	  					System.out.println("entered to the while block of resultset");
	  					System.out.println("values of paratmeters---------------------");
	  					System.out.println(resultset4.getString(1));
	  					System.out.println(resultset4.getString(2));
	  					System.out.println("values of paratmeters---------------------");
	  					ArrayList<String> resplist = new ArrayList<String>();
	  					resplist = getdata(resultset4.getString(1),resultset4.getInt(2));
	  					System.out.println("value in resplist is "+resplist);
	  				}
	  		     }
		     }
		 catch(Exception sqlException) { sqlException.printStackTrace(); }
		j++;
	}
}
public static void getDbConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionUrl);
			statement = connection.createStatement();
			
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
	}	

}


/*
 
 * 
 * System.out.println("hi");
		  				String selectSql = "SELECT count(*) from emp where flag=0";
		  				resultset = statement.executeQuery(selectSql);
		  				resultset.next();
		  				int count = resultset.getInt(1);
		  	            System.out.println("value of count of rows is "+resultset.getRow());
		  	            System.out.println("value of count variable is "+count);
		  	            
		  	            if(count==0)
		  	            {
		  	            	System.out.println("executing if part");
		  	            }
		  	            
		  	            else
		  	            {
		  	            	System.out.println("executing else part");
		  	            	resultset2 =statement.executeQuery("select * from emp where flag=0");
		  	            	System.out.println(resultset2.toString());
								/*
								 * while (resultset2.next()) { System.out.println("executing while part");
								 * String updateSql = "update emp set flag=1 where id="+resultset2.getInt(1); 
								 * j=statement.executeUpdate(updateSql); System.out.println("value of j is "+j);
								 * //resultset2.updateInt("flag",1); //resultset2.updateRow();
								 * System.out.println("row updated"); //System.out.println(resultSet.getInt(1));
								 * //System.out.println(resultSet.getString(1)+ "   "+ resultSet.getString(2)
								 * +"   " +resultSet.getString(3)); }
								 
		  	            	} */
  
