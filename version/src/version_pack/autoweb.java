package version_pack;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//import version_pack.AutoLog;


public class autoweb {
	public static String unm;
	public static String pwd;
	public static String year;
	public static WebDriver driver;
	public static int flag=1;
	
    public static String  sitepath="http://192.168.1.210:278";
    
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		login();
		operations();
		teardown();
	}

	public static void login() throws InterruptedException
	{
		int i;
		i=check();
		if(i==0)
		{
		
		}
		else
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
		}
	}
	
	
	public static void teardown()
	{
		if(flag==1)
		{
		
		}
		else
			driver.quit();
	}
	public static int check() throws InterruptedException
	{
	String dashboardurl;
	dashboardurl = driver.getCurrentUrl();
	if(dashboardurl!="http://192.168.1.210:278/DashBoard")
		{
			return 1;
		}
	else 
		return 0;
	}
	
	public static void operations() throws InterruptedException {
	int i = check();
	if(i==0)
		{
		Runnable runnable = new Runnable() {
		      public void run() {
		        // task to run goes here
		        System.out.println("Hello !!");
		      }
		    };
		    ScheduledExecutorService service = Executors
		                    .newSingleThreadScheduledExecutor();
		    service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		}
	else
		login();
	
		
	}
	
}
