package version_pack;

//import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class invoke extends AutoLog {
	
	//WebDriver driver=new ChromeDriver();
	public WebDriver driver;
	public String year;
    public static String  sitepath="http://192.168.1.210:278";
	
	public static void main(String[] args) throws InterruptedException,FileNotFoundException,IOException{
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		invoke i = new invoke();
		i.driver = i.login();
		//i.addEmploye("aa101", "aaaa");
		//i.transferIn("5117",i.driver);
		//i.checksession();
		//i.paymentTransferIn("5117","532",i.driver);
		//i.paymentTransferOut("10001","22215");
		
		//i.settlement("a2001",i.driver);
		//i.teardown(i.driver);
		//Heavydriver hd = new Heavydriver();
		//i.driver = hd.get_driver();
		
		/*System.out.println(driver.getTitle());
		driver.findElement(By.cssSelector("img[data-original-title='Masters']")).click();
		System.out.println(driver.getTitle());
		// */
	}

	public WebDriver login() throws InterruptedException, IOException
	{
		Heavydriver h = new Heavydriver();
		driver = h.get_driver();
		h.set_driver(driver);
		System.out.println("Execution after setting ChromeDriver path in System Variables");
		
		/*ChromeOptions options = new ChromeOptions(); 
		options.addArguments("headless"); 
		WebDriver driver = new ChromeDriver(options);*/ 
		 
		//WebDriver driver=Heavydriver.driver;
		//System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		 
		String unm,pwd;
		//System.out.println("enter link to automate");
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//sitepath = br.readLine();
		//if(sitepath=="")
			//sitepath="http://192.168.1.210:278";
			  //sitepath="http://192.168.6.9:81";
		
		 driver.manage().window().maximize();
		 
		 /*System.out.println("enter username");
		 BufferedReader brunm = new BufferedReader(new InputStreamReader(System.in));
		 
		 unm = brunm.readLine();
		 
		 if(unm=="")*/
			 //unm="version";
			 unm="VERSION";
			
		 /*System.out.println("enter password");
		 BufferedReader brpwd = new BufferedReader(new InputStreamReader(System.in));
		 pwd = brpwd.readLine();
		 if(pwd=="")*/
			 //pwd="vspl&&123";
			 //pwd="VSPL&321";
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
			 AutoLog.addcontent("website not launched..check internet issue");
			 driver.close();
		 }
		 driver.findElement(By.name("txtPassword")).sendKeys(pwd);
		 Thread.sleep(3000);
		 driver.findElement(By.id("btnLogin")).click();
		 Thread.sleep(3000);
		 
		 //remove the below comment to work from year selection screen
		 driver.findElement(By.id("btnOk")).click();
		 
		 
		 Select sel = new Select(driver.findElement(By.id("ddlYear")));
		 WebElement we = sel.getFirstSelectedOption();
		 year = we.getText();
		 
		 System.out.println("value of year is : "+year);
		 //Thread.sleep(5000);
		 
		 /*if(driver.getCurrentUrl() !="http://192.168.1.210:278//DashBoard")
			 driver.findElement(By.id("btnOk")).click();
		 else
			 Thread.sleep(4000); */
		Thread.sleep(4000);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		String cururl = driver.getCurrentUrl();
		if(cururl !=sitepath+"/DashBoard")
			driver.navigate().to(sitepath+"/DashBoard");
		
		return driver;
	}
	
	public void teardown(WebDriver driver)
	{
		driver.quit();
		System.out.println("Execution complete");
	}
	
	public void transferIn(String did,WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);   
		/*
		 * if(this.driver == null) { this.driver = driver; }
		 */
		driver.navigate().to(sitepath+"/PFTransferInApplicationMst.aspx?title=Application(TrIn-T)");
		Thread.sleep(3000);
		//driver.findElement(By.name("ctl00$DefaultContent$btnNewUp")).click();
		
		boolean b;
		try {
			WebDriverWait waitab = new WebDriverWait(driver,10);
			WebElement elementab = waitab.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_btnNewUp"))));
			elementab.click();
			b=true;
		}
		catch(Exception e){
			b= false;
			
		}
		System.out.println("new button is pressed : "+b);
		
		Thread.sleep(1000);
		
		String tdate = prevmonths(5);
		driver.findElement(By.id("ctl00_DefaultContent_txtApplicationDate")).clear();
		driver.findElement(By.id("ctl00_DefaultContent_txtApplicationDate")).sendKeys(tdate);
		driver.findElement(By.name("ctl00$DefaultContent$txtEmployeeCode")).sendKeys(did+"\n"); 
		
		Thread.sleep(2000);
		String pjdate = prevyears(5);
		String pldate = prevyears(4);
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevDateofJoining")).clear();
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevDateofJoining")).sendKeys(pjdate);
		
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevdateofLeaving")).clear();
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevdateofLeaving")).sendKeys(pldate);
		
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevEstablishmentCode")).sendKeys("12546221");
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevCompany")).sendKeys("TVS motors");
		
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevTrust")).sendKeys("old trust");
		Select trusttype = new Select (driver.findElement(By.name("ctl00$DefaultContent$ddlTypeOfTrust")));
		trusttype.selectByValue("P");
		
		driver.findElement(By.name("ctl00$DefaultContent$txtPrevPFNo")).sendKeys("4561237845");
		
		//String appid = driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtTransferInAppNo']")).getText();
		//String appid = driver.findElement(By.id("ctl00_DefaultContent_txtTransferInAppNo")).getAttribute("innerHTML");
		//String appid = driver.findElement(By.id("ctl00_DefaultContent_txtTransferInAppNo")).getCssValue("value");
		
		driver.findElement(By.xpath("//input[@type='submit'] [@tabindex=43]")).click();
	    
		String mess = driver.switchTo().alert().getText();
	    myprint(mess);
	    myprint("value of message is taken");
			
		driver.switchTo().alert().accept();
		
		/* String ssd = driver.findElement(By.id("ctl00_DefaultContent_lblDbErrMsg")).getText();
		myprint("=======================");
		myprint(ssd);
		myprint("======================="); */
		
		Thread.sleep(1000);
	
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		if(element.isEnabled())
		driver.findElement(By.id("btnCloseMessage")).click();
		Thread.sleep(2000);
		System.out.println("transfer in application is added");
		Thread.sleep(2000);
		//WebElement ele = driver.findElement(By.tagName("H3"));
		//String appid = driver.findElement(By.tagName("H3")).getText();
	
		String appid = driver.findElement(By.id("ctl00_DefaultContent_txtTransferInAppNo")).getAttribute("value");
		
		System.out.println("appid value from transfer in application is : "+appid);
		
		//paymentTransferIn(did,appid,driver);
	
	}
	
	public void transferOut(String did,WebDriver driver) throws InterruptedException
	{
		driver.navigate().to(sitepath+"/PFTransferOutApplicationMst.aspx?title=Application(TrOut-T)");
		Thread.sleep(3000);
		//driver.findElement(By.name("ctl00$DefaultContent$btnNewUp")).click();
		
		boolean b;
		try {
			WebDriverWait waitab = new WebDriverWait(driver,10);
			WebElement elementab = waitab.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_btnNewUp"))));
			elementab.click();
			b=true;
		}
		catch(Exception e){
			b= false;
			
		}
		System.out.println("new button is pressed : "+b);
		
		Thread.sleep(1000);
		
		String tdate = prevmonths(1);
		driver.findElement(By.id("ctl00_DefaultContent_txtApplicationDate")).clear();
		driver.findElement(By.id("ctl00_DefaultContent_txtApplicationDate")).sendKeys(tdate);
		driver.findElement(By.name("ctl00$DefaultContent$txtEmployeeCode")).sendKeys(did+"\n"); 
				
		Thread.sleep(2000);
		String dol = prevmonths(2);
		driver.findElement(By.name("ctl00$DefaultContent$txtDateofLeaving")).clear();
		driver.findElement(By.name("ctl00$DefaultContent$txtDateofLeaving")).sendKeys(dol);
		Thread.sleep(1000);
		Select leavereason = new Select (driver.findElement(By.name("ctl00$DefaultContent$ddlMemReasonOfLeaving")));
		leavereason.selectByValue("1");
		driver.findElement(By.id("ctl00_DefaultContent_chkLeavingCompany")).click();
		
		driver.findElement(By.name("ctl00$DefaultContent$txtEstablishmentCode")).sendKeys("12546221");
		driver.findElement(By.name("ctl00$DefaultContent$txtCompanyName")).sendKeys("TVS motors");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ac_results']/ul/li")).click();
		
		
		Select trusttype = new Select (driver.findElement(By.name("ctl00$DefaultContent$ddlTrustType")));
		trusttype.selectByValue("P");
		
		driver.findElement(By.name("ctl00$DefaultContent$txtTrustName")).sendKeys("new trust");
		driver.findElement(By.name("ctl00$DefaultContent$txtCellNo")).clear();
		driver.findElement(By.name("ctl00$DefaultContent$txtCellNo")).sendKeys("9825098250");
		driver.findElement(By.name("ctl00$DefaultContent$txtPfcodeacno")).sendKeys("4561237845");
		driver.findElement(By.name("ctl00$DefaultContent$txtRPFCName")).sendKeys("new rpfc");
		
		String appid = driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtTransferOutAppNo']")).getText();
		
		driver.findElement(By.xpath("//input[@type='submit'] [@tabindex=44]")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_DefaultContent_dvMessageBox']/table[1]/tbody[1]/tr[1]/td[2]/input[@id='btnCloseMessage' and @class='Button' and 1]")));
		
		//div[@id='ctl00_DefaultContent_dvMessageBox']/table[1]/tbody[1]/tr[1]/td[2]/input[@id='btnCloseMessage' and @class='Button' and 1]
		if(element.isEnabled())
		element.click();
		System.out.println("transfer out applcation inserted");
		paymentTransferOut(did, appid,driver);
	}
	
	public void annualslip(String did,WebDriver driver) throws InterruptedException
	{
		driver.navigate().to(sitepath+"/ReportPages/UnExempted/AnnualSlip.aspx?title=Annual%20Slip");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_ucEmployeeSelectionPopup_txtEnterEmpCode']")).sendKeys(did);
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_ucEmployeeSelectionPopup_btnAddEmp']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_ucEmployeeSelectionPopup_btnReport']")).click();
		Thread.sleep(5000);
		AutoLog.takeshot(driver, "annual slip");
		System.out.println("annual slip is generated");
		
	}
	
	public void paymentTransferIn(String did,String appid,WebDriver driver) throws InterruptedException
	{
		System.out.println("value of did is : "+did + " value of appid is : "+appid);
		driver.navigate().to(sitepath+"/TransferInReceipt.aspx?title=Receipt(TrIn-T)");
		Thread.sleep(2000);
		
		boolean b;
		try {
			WebDriverWait waita = new WebDriverWait(driver,10);
			WebElement elementa = waita.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_btnNewUp"))));
			elementa.click();
			b=true;
		}
		catch(Exception e){
			b=false;
		}
		System.out.println("the new button clicked or not :=="+b);
		
		WebDriverWait waita = new WebDriverWait(driver,20);
		Boolean elementa = waita.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("ctl00_DefaultContent_txtNarration"))));
		//WebElement elementa = waita.until(ExpectedConditions.presenceOfElementLocated((By) driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtNarration']"))));
		System.out.println(elementa);
		
		Thread.sleep(12000);
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtNarration']")).clear();
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtNarration']")).sendKeys("automation entry");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_rbManually']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtEmployeeCode']")).sendKeys(did+"\n");
		System.out.println("value of appid is "+appid);
		
		boolean b1=false;
		try
		{
			WebDriverWait waitappno = new WebDriverWait(driver,15);
			//WebElement elementappno = waitappno.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ctl00_DefaultContent_ddlApplicationNo"))));
			WebElement elementappno = waitappno.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("ctl00$DefaultContent$ddlApplicationNo"))));
			elementappno.click();
			
		//	WebElement elementappno = waitappno.until(ExpectedConditions.presenceOfElementLocated((By) driver.findElement(By.id("ctl00_DefaultContent_ddlApplicationNo"))));
			/*elementappno.click();
			Thread.sleep(1500);
			*/
			
			System.out.println("toniya : "+elementappno.isEnabled());
			Thread.sleep(1500);
			
		Select cappno = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlApplicationNo")));
		cappno.selectByValue(appid.trim().toString());
			b1 = true;
		}
		catch(Exception e)
		{
			b1 = false;
			System.out.println("error is : "+ e.getMessage());
		}
		
		System.out.println("the application number is entered or not : "+b1);
			
		System.out.println("***************************");
		
		
		System.out.println("***************************");
		
		Thread.sleep(1000);
		
		//driver.findElement(By.id("ctl00_DefaultContent_txtPrevPFNo")).sendKeys("456216214512");
		//driver.findElement(By.id("ctl00_DefaultContent_txtPrevDateofMem")).sendKeys("01/11/2011");
		String a1 = AutoLog.prevyears(3).toString(); //15/08/2020
		driver.findElement(By.id("ctl00_DefaultContent_txtReceiptDate")).sendKeys(a1);
		
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtMemberBalance']")).sendKeys("5000");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtVoluntaryBalance']")).sendKeys("3000");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtCompanyBalance']")).sendKeys("5000");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtMemberIntBalance']")).sendKeys("500");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtVoluntaryIntBalance']")).sendKeys("300");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtCompanyIntBalance']")).sendKeys("500");
		Thread.sleep(1000);
		
		driver.findElement(By.id("ctl00_DefaultContent_txtPrevEmployer")).sendKeys("prev company");
		driver.findElement(By.id("ctl00_DefaultContent_txtPrevTrustName")).sendKeys("prev trust");
		Select sel1 = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlTrustType")));
		sel1.selectByValue("I");
		
		driver.findElement(By.id("ctl00_DefaultContent_txtPrevEstablishmentCode")).sendKeys("56894512");
		driver.findElement(By.id("ctl00_DefaultContent_txtPrevDateofLeaving")).sendKeys("10/12/2018");
		
		Select status = new Select(driver.findElement(By.xpath("//select[@id='ctl00_DefaultContent_ddlStatus']")));
		status.selectByValue("RECEIVED");
		Thread.sleep(1000);
		
		WebDriverWait waitc = new WebDriverWait(driver,10);
		WebElement elementc = waitc.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_btnAddEmployee"))));
		elementc.click();
		Thread.sleep(4000);
		
		Select ledger = new Select(driver.findElement(By.xpath("//select[@id='ctl00_DefaultContent_ddlLedgerName']")));
		ledger.selectByVisibleText("HDFC BANK");
		Thread.sleep(2000);
		
		Select ptype = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlPaymentType")));
		ptype.selectByValue("C");		
		Thread.sleep(1000);
		String[] chekdetails = AutoLog.getcheque();
		//driver.findElement(By.id("ctl00_DefaultContent_txtChequeNo")).sendKeys("1542");
		driver.findElement(By.name("ctl00$DefaultContent$txtChequeNo")).sendKeys(chekdetails[0]);
		
		
		/*WebDriverWait waite = new WebDriverWait(driver,10);
		WebElement elemente = waite.until(ExpectedConditions.presenceOfElementLocated((By) driver.findElement(By.id("ctl00_DefaultContent_txtChequeNo"))));
		elemente.sendKeys("1542");*/
		
		WebDriverWait waitf = new WebDriverWait(driver,10);
		WebElement elementf = waitf.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_txtChequeDate"))));
		elementf.click();
		Thread.sleep(2000);
		
		WebDriverWait waitg = new WebDriverWait(driver,10);
		WebElement elementg = waitg.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_txtIssuerBankName"))));
		elementg.sendKeys(chekdetails[1]);
		
		WebDriverWait waitb = new WebDriverWait(driver,10);
		WebElement elementb = waitb.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ctl00_DefaultContent_btnAddAccount"))));
		elementb.click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnSaveUp']")).click();
		
		driver.switchTo().alert().accept();
		
		//input[@id='ctl00_DefaultContent_btnCloseMessage']
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_DefaultContent_btnCloseMessage")));
		if(element.isEnabled())
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnCloseMessage']")).click();
		Thread.sleep(2000);

		String showmsg = driver.findElement(By.id("ctl00_DefaultContent_lblUpdateModeUp")).getText().toString();
		//System.out.println("good good =" + showmsg +"last");
		
		if(showmsg.contains("Update"))
		System.out.println("transfer in receipt is added");
		else 
		System.out.println("transfer receipt not saved.. try again..");
		
	}
	
	
	public void paymentTransferOut(String did,String appid,WebDriver driver) throws InterruptedException
	{
		driver.navigate().to(sitepath+"/TransferOutPayment.aspx?title=Payment(TrOut-T)");
		
		driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
		Thread.sleep(8000);
		WebDriverWait waita = new WebDriverWait(driver,15);
		WebElement elementa = waita.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_ddlLedgerName"))));
		boolean b = elementa.isDisplayed();
		System.out.println(b);
		
		driver.findElement(By.id("ctl00_DefaultContent_txtEmployeeCode")).sendKeys(did);
		Thread.sleep(2000);
	
		WebDriverWait waitb = new WebDriverWait(driver,5);
		WebElement elementb = waitb.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='ac_results']/ul/li"))));
		elementb.click();
		
		driver.findElement(By.id("ctl00_DefaultContent_btnAddEmployee")).click();
		Thread.sleep(2000);
		
		WebDriverWait waitc = new WebDriverWait(driver,10);
		WebElement elementc = waitc.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_ddlLedgerName"))));
		elementc.sendKeys("hdfc bank");
		Thread.sleep(1000);
		
		Select ptype = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlPaymentType")));
		ptype.selectByValue("C");
		String[] checkdetails =AutoLog.getcheque();
		driver.findElement(By.id("ctl00_DefaultContent_txtChequeNo")).clear();
		driver.findElement(By.id("ctl00_DefaultContent_txtChequeNo")).sendKeys(checkdetails[0]);
		
		driver.findElement(By.id("ctl00_DefaultContent_txtPayeeName")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_DefaultContent_txtPayeeName")).sendKeys("new payee");
		
		driver.findElement(By.id("ctl00_DefaultContent_btnAddAccount")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnSaveUp']")).click();
		
		driver.switchTo().alert().accept();
		//===========================================
		System.out.println("execution done");
			
	}
	
	
	public void paymentSettlement(String did,String appid,WebDriver driver) throws InterruptedException
	{
		
	driver.navigate().to(sitepath+"/Settlement.aspx?title=Payment(Setl-T)");
	driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("ctl00_DefaultContent_txtEmployeeCode")).sendKeys(did);
	Thread.sleep(1000);
		
	WebDriverWait waitb = new WebDriverWait(driver,8);
	WebElement elementb = waitb.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='ac_results']/ul/li"))));
	elementb.click();
	
	Thread.sleep(2000);
	driver.findElement(By.id("ctl00_DefaultContent_btnCalculate")).click();
	Thread.sleep(10000);
	
	Select bankledger = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlLedgerName")));
	bankledger.selectByValue("48");
	Thread.sleep(1000);
	
	Select ptype = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlPaymentType")));
	ptype.selectByValue("C");
	
		
	//driver.findElement(By.id("ctl00_DefaultContent_txtChequeNo")).click();
	driver.findElement(By.id("ctl00_DefaultContent_txtChequeNo")).sendKeys("4512");
	Thread.sleep(2000);
	
	//driver.findElement(By.id("ctl00_DefaultContent_txtAccNo")).click();
	driver.findElement(By.id("ctl00_DefaultContent_txtAccNo")).sendKeys("454213215612");
	Thread.sleep(2000);
	
	//driver.findElement(By.id("ctl00_DefaultContent_txtPayeeName")).click();
	driver.findElement(By.id("ctl00_DefaultContent_txtPayeeName")).sendKeys("new payee");
	Thread.sleep(2000);
	
	//driver.findElement(By.id("ctl00_DefaultContent_txtBankName")).click();
	driver.findElement(By.id("ctl00_DefaultContent_txtBankName")).sendKeys("HDFC bank");
	
	driver.findElement(By.id("ctl00_DefaultContent_btnAddAccount")).click();	
	
	Thread.sleep(2000);
	System.out.println("*****************************");
	//WebDriverWait waitm = new WebDriverWait(driver,10);
	//WebElement elementm = waitm.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("ctl00$DefaultContent$btnSaveUp"))));
	//WebElement elementm = waitm.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_DefaultContent_btnCancelUp"))));
	//elementm.click();
	boolean b = driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnSaveUp']")).isEnabled();
	System.out.println(b);
	boolean c = driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnSaveUp']")).isDisplayed();
	System.out.println(c);
	Thread.sleep(1000);
	
	
	WebElement element = driver.findElement(By.id("ctl00_DefaultContent_btnSaveUp"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);
	//driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnSaveUp']")).click();
	
	//ctl00_DefaultContent_btnCancelUp
	System.out.println("*****************************");
	//driver.findElement(By.name("ctl00$DefaultContent$btnSaveUp")).click();
	
	Thread.sleep(2000);
	
	driver.switchTo().alert().accept();
	Thread.sleep(3000);
	driver.switchTo().activeElement();
	WebDriverWait waitj = new WebDriverWait(driver, 10);
	WebElement elementj = waitj.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_DefaultContent_btnCloseMessage")));
	
	if(elementj.isEnabled())
	driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnCloseMessage']")).click();
	Thread.sleep(2000);
		
	System.out.println("execution completed successfully");
	}
	
	
	public void settlement(String did,WebDriver driver) throws InterruptedException
	{
	
	driver.navigate().to(sitepath+"/PFTransferOutApplicationMst.aspx?title=Application(Setl-T)");
	driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
	//driver.findElement(By.id("ctl00_DefaultContent_txtEmployeeCode")).clear();
	Thread.sleep(2000);
	driver.findElement(By.id("ctl00_DefaultContent_txtEmployeeCode")).sendKeys(did+"\n");
	driver.findElement(By.id("ctl00_DefaultContent_txtApplicationDate")).clear();
	Thread.sleep(2000);
	
	String appdate = AutoLog.prevdays(2);
	driver.findElement(By.id("ctl00_DefaultContent_txtApplicationDate")).sendKeys(appdate);
	Thread.sleep(2000);
	
	Thread.sleep(2000);
	String prevdate = AutoLog.prevdays(5);
	driver.findElement(By.id("ctl00_DefaultContent_txtDateofLeaving")).sendKeys(prevdate);
	Thread.sleep(2000);
	
	Select memreason = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlMemReasonOfLeaving")));
	memreason.selectByValue("1");
	String appid = driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtTransferOutAppNo']")).getText();
	driver.findElement(By.id("ctl00_DefaultContent_btnSaveUp")).click();
	driver.switchTo().alert().accept();
	
	System.out.println("execution is completed.");
	
	driver.switchTo().activeElement();
	WebDriverWait waitg = new WebDriverWait(driver, 10);
	WebElement elementg = waitg.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCloseMessage")));
	
	if(elementg.isEnabled())
	driver.findElement(By.xpath("//input[@id='btnCloseMessage']")).click();
	Thread.sleep(2000);
	paymentSettlement(did, appid,driver);
	}
	
	
	public void addEmploye(String id,String name,WebDriver driver) throws InterruptedException, IOException
	{
		System.out.println("add employe method is called");
		
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		//driver.navigate().to("http://192.168.1.210:278/EmployeeMst.aspx?title=Employee%20Detail");
		/*driver.findElement(By.cssSelector("img[data-original-title='Masters']")).click();
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/ul/li[1]/a")).click();
		Thread.sleep(5000);*/
		System.out.println(sitepath);
		//driver.navigate().to("http://192.168.1.210:278/EmployeeMst.aspx?title=Employee%20Detail");
		driver.navigate().to(sitepath+"/EmployeeMst.aspx?title=Employee%20Detail");
		/* //http://192.168.7.61:1102/EmployeeMst.aspx?title=Employee%20Detail */ 
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.id("ctl00_DefaultContent_btnNewUp")).click();
		
		driver.findElement(By.name("ctl00$DefaultContent$txtEmployeeCode")).sendKeys(id);
		driver.findElement(By.name("ctl00$DefaultContent$txtEmployeeName")).sendKeys(name);
		Select type = new Select(driver.findElement(By.name("ctl00$DefaultContent$ddlEmployeeType")));
		type.selectByValue("1");

		Select gender = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlGender")));
		gender.selectByIndex(1);
		driver.findElement(By.name("ctl00$DefaultContent$txtAutoCompSBU")).sendKeys("17");
		Thread.sleep(2000);
		//driver.findElement(By.className("ac_even && ac_over")).click();
		driver.switchTo().activeElement();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[class='ac_results']")).click();
		Thread.sleep(3000);
		//driver.getWindowHandle();
		Select branch = new Select(driver.findElement(By.name("ctl00$DefaultContent$ddlBranch")));
		branch.selectByValue("21");
		Select dept = new Select(driver.findElement(By.name("ctl00$DefaultContent$ddlDepartment")));
		dept.selectByIndex(2);
		Select section = new Select(driver.findElement(By.name("ctl00$DefaultContent$ddlSection")));
		section.selectByIndex(1);
		Select desig = new Select(driver.findElement(By.name("ctl00$DefaultContent$ddlDesignation")));
		desig.selectByIndex(1);
		Select grade = new Select(driver.findElement(By.name("ctl00$DefaultContent$ddlGrade")));
		grade.selectByVisibleText("B");
		Select marstat = new Select(driver.findElement(By.name("ctl00$DefaultContent$ddlMaritalStatus")));
		marstat.selectByValue("UNMARRIED");		
		
		driver.findElement(By.xpath("//input[@name='ctl00$DefaultContent$txtMembershipNo']")).sendKeys("4567895231");
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_txtDateOfMem']")).sendKeys("10/12/2015");
		driver.findElement(By.id("ctl00_DefaultContent_txtDateOfJoining")).sendKeys("03/04/2020");
		
		Select memstat = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlMembershipStatus")));
		memstat.selectByValue("ACTIVE");
		
		Select voltype = new Select(driver.findElement(By.id("ctl00_DefaultContent_ddlVoluntaryContType")));
		voltype.selectByIndex(1);
		
		
		//driver.findElement(By.xpath("//input[@type='submit'],[@value='Save'],@id='ctl00_DefaultContent_btnSave']")).click();
		driver.findElement(By.xpath("//input[@id='ctl00_DefaultContent_btnSave']")).click();
		
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//input[@id='btnCloseMsg']")).click();
		
	    Thread.sleep(2000);
	    /*String delid = driver.findElement(By.name("ctl00$DefaultContent$txtEmployeeCode")).getText().toString();
	    if(delid=="")
	    	delid="hello";
	    System.out.println("value before calling function: " + delid);
		delEmploye(delid);*/
	}

	public void checksession() throws InterruptedException
	{
		String s1 = driver.getWindowHandle();
        addnewtab("http:\\\\drive.google.com");
        //addnewtab(sitepath+"/DashBoard");
        
        driver.switchTo().window(s1);
        
//      teardown(driver);
        
	
    }
	
	public void addnewtab(String s)
	{	
		((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
		switchtotab(driver);
		driver.get(s);
	}
	public void switchtotab(WebDriver driver)
	{
		String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) 
        {
         subWindowHandler = iterator.next();
        }
        
        System.out.println("value is : "+subWindowHandler);
        driver.switchTo().window(subWindowHandler);
 	}
	
	public void switchtoprevioustab(WebDriver driver)
	{
		String mainWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) 
        {
         mainWindowHandler = iterator.next();
        }
        driver.switchTo().window(mainWindowHandler);
	}

}