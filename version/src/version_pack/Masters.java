package version_pack;

//import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStreamReader;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Masters extends AutoLog {
	
	WebDriver driver=new ChromeDriver();
    public static String  sitepath; 

	public static void main(String[] args) throws InterruptedException,FileNotFoundException,IOException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		Masters m = new Masters();
		m.login();
		m.esta_code();
	}

	public WebDriver login() throws InterruptedException, IOException
	{
		System.out.println("Execution after setting ChromeDriver path in System Variables");
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		/*ChromeOptions options = new ChromeOptions(); 
		options.addArguments("headless"); 
		WebDriver driver = new ChromeDriver(options);*/ 
		 
		// WebDriver driver=new ChromeDriver();
		String unm,pwd;
		//System.out.println("enter link to automate");
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//sitepath = br.readLine();
		//if(sitepath=="")
			//sitepath="http://192.168.1.210:278";
			  sitepath="http://192.168.6.9:81";
		
		 driver.manage().window().maximize();
		 
		 /*System.out.println("enter username");
		 BufferedReader brunm = new BufferedReader(new InputStreamReader(System.in));
		 
		 unm = brunm.readLine();
		 
		 if(unm=="")*/
			 unm="version";
			
		 /*System.out.println("enter password");
		 BufferedReader brpwd = new BufferedReader(new InputStreamReader(System.in));
		 pwd = brpwd.readLine();
		 if(pwd=="")*/
			 //pwd="vspl&&123";
			 pwd="vspl&&123";
			
		 try {
		 driver.get(sitepath+"/Login");
		 driver.findElement(By.name("txtUsername")).sendKeys(unm);
		 }
		 catch (Exception e)
		 {
			 AutoLog.addcontent("website not launched..check internet issue");
			 driver.close();
		 }
		 
		 
		 driver.findElement(By.name("txtPassword")).sendKeys(pwd);
		 Thread.sleep(3000);
		 driver.findElement(By.id("btnLogin")).click();
		 Thread.sleep(4000);
		 
		 driver.findElement(By.id("btnOk")).click();
		 
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		String cururl = driver.getCurrentUrl();
		if(cururl !=sitepath+"/DashBoard")
			driver.navigate().to(sitepath+"/DashBoard");
			
		return driver;
	}
	
	public void teardown()
	{
		driver.quit();
		System.out.println("Execution complete");
	}
	
	public void esta_code()
	{
		driver.navigate().to(sitepath+"/EstablishmentCodeMst.aspx?title=Establishment%20Code");
		
		/*driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_txtEstablishmentCode")).sendKeys("1111");
		driver.findElement(By.id("ctl00_DefaultContent_txtSROCode")).sendKeys("aaaa");
		Select type = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlEDLIType")));
		type.selectByValue("EXEMPTED");
		driver.findElement(By.id("ctl00_DefaultContent_chkAllSBU")).click();
		driver.findElement(By.id("ctl00_DefaultContent_btnSave")).click();
		driver.switchTo().alert().accept();
		WebDriverWait waite = new WebDriverWait(driver, 10);
		WebElement elemente = waite.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(elemente.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();*/
	}
	
	public void master_entry()
	{
		driver.navigate().to(sitepath+"/BranchMst.aspx?title=Branch");
		driver.findElement(By.id("ctl00_DefaultContent_ucBranchMstPopup_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_ucBranchMstPopup_txtBranchCode")).sendKeys("1111	");
		driver.findElement(By.id("ctl00_DefaultContent_ucBranchMstPopup_txtName")).sendKeys("aaaa");
		driver.findElement(By.id("ctl00_DefaultContent_ucBranchMstPopup_btnSave")).click();
		driver.switchTo().alert().accept();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(element.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
		
		driver.navigate().to(sitepath+"/DepartmentMst.aspx?title=Department");
		driver.findElement(By.id("ctl00_DefaultContent_ucDepartmentMstPopup_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_ucDepartmentMstPopup_txtDepartmentCode")).sendKeys("1111");
		driver.findElement(By.id("ctl00_DefaultContent_ucDepartmentMstPopup_txtDepartmentName")).sendKeys("aaaa");
		driver.findElement(By.id("ctl00_DefaultContent_ucDepartmentMstPopup_btnSave")).click();
		driver.switchTo().alert().accept();
		WebDriverWait waitb = new WebDriverWait(driver, 10);
		WebElement elementb = waitb.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(elementb.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
		
		driver.navigate().to(sitepath+"/SBUMst.aspx?title=SBU");
		driver.findElement(By.id("ctl00_DefaultContent_ucSBUMstPopup_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_ucSBUMstPopup_txtSbuCode")).sendKeys("1111");
		driver.findElement(By.id("ctl00_DefaultContent_ucSBUMstPopup_txtSbuName")).sendKeys("aaaa");
		driver.findElement(By.id("ctl00_DefaultContent_ucSBUMstPopup_btnSave")).click();
		driver.switchTo().alert().accept();
		WebDriverWait waitc = new WebDriverWait(driver, 10);
		WebElement elementc = waitc.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(elementc.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
		
		
		driver.navigate().to(sitepath+"/SectionMst.aspx?title=Section");
		driver.findElement(By.id("ctl00_DefaultContent_ucSectionMstPopup_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_ucSectionMstPopup_txtSectionCode")).sendKeys("1111");
		driver.findElement(By.id("ctl00_DefaultContent_ucSectionMstPopup_txtSectionName")).sendKeys("aaaa");
		driver.findElement(By.id("ctl00_DefaultContent_ucSectionMstPopup_btnSave")).click();
		driver.switchTo().alert().accept();
		WebDriverWait waitd = new WebDriverWait(driver, 10);
		WebElement elementd = waitd.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(elementd.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
		
		driver.navigate().to(sitepath+"/EstablishmentCodeMst.aspx?title=Establishment%20Code");
		driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_txtEstablishmentCode")).sendKeys("1111");
		driver.findElement(By.id("ctl00_DefaultContent_txtSROCode")).sendKeys("aaaa");
		Select type = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlEDLIType")));
		type.selectByValue("EXEMPTED");
		driver.findElement(By.id("ctl00_DefaultContent_chkAllSBU")).click();
		driver.findElement(By.id("ctl00_DefaultContent_btnSave")).click();
		driver.switchTo().alert().accept();
		WebDriverWait waite = new WebDriverWait(driver, 10);
		WebElement elemente = waite.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(elemente.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
		
		
		driver.navigate().to(sitepath+"/DesignationMst.aspx?title=Designation");
		driver.findElement(By.id("ctl00_DefaultContent_ucDesignationMstPopup_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_ucDesignationMstPopup_txtDesignationCode")).sendKeys("1111");
		driver.findElement(By.id("ctl00_DefaultContent_ucDesignationMstPopup_txtDescription")).sendKeys("aaaa");
		driver.findElement(By.id("ctl00_DefaultContent_ucDesignationMstPopup_btnSave")).click();
		driver.switchTo().alert().accept();
		WebDriverWait waitf = new WebDriverWait(driver, 10);
		WebElement elementf = waitf.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(elementf.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
		
		
		driver.navigate().to(sitepath+"/GradeMst.aspx?title=Grade");
		driver.findElement(By.id("ctl00_DefaultContent_ucGradeMstPopup_btnNewUp")).click();
		driver.findElement(By.id("ctl00_DefaultContent_ucGradeMstPopup_txtGradeCode")).sendKeys("1111");
		driver.findElement(By.id("ctl00_DefaultContent_ucGradeMstPopup_txtGradeName")).sendKeys("aaaa");
		driver.findElement(By.id("ctl00_DefaultContent_ucGradeMstPopup_btnSave")).click();
		driver.switchTo().alert().accept();
		WebDriverWait waitg = new WebDriverWait(driver, 10);
		WebElement elementg = waitg.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(elementg.isEnabled())
		driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
	}
	
}

