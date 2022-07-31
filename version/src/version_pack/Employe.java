package version_pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class Employe extends invoke  {
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
	System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
	Employe e = new Employe();
	
	ArrayList<String> list = new ArrayList<String>();
    //File f = new File("D:\\usable_files\\Add_Employee.txt");
	File f = new File("D:\\test records\\usable_files\\Add_Employee.txt");
    
    FileReader fr2 = new FileReader(f);
    try (BufferedReader br2 = new BufferedReader(fr2)) {
		String line2;
		System.out.println("Reading text file after");
		
		while((line2 = br2.readLine()) != null){
            list.add(line2);
        }
	}
    
    String[] filedata = list.toArray(new String[list.size()]);
    int i=1,j;
    j=filedata.length;
    System.out.println(j);
    
    String eid[] = new String[j];
    String ename[] = new String[j];
    
    for(i=1;i<filedata.length;i++)
    {
    	eid[i-1]=filedata[i].toString().substring(0, 5).trim();
    	ename[i-1]=filedata[i].toString().substring(6).trim();
    }
   
    invoke inv = new invoke();
    
    for(i=0;i<eid.length;i++)
    {
    	
    	if(eid[i]==null)
    	break;
    	e.addEmploye(eid[i], ename[i]);
    	AutoLog.addcontent("new employee added with id :  "+ eid[i]);
    	e.AddPfOpening(eid[i]);
    	AutoLog.addcontent("opening pf balance added for employee id :  "+ eid[i]);
    	inv.transferIn(eid[i],driver);
    	addcontent("transfer in application is added : "+ eid[i]);
    	inv.transferOut(eid[i],driver);
    	addcontent("transfer out application is added : "+ eid[i]);
    	inv.annualslip(eid[i],driver);
    	addcontent("annual slip generated for :  "+ eid[i]);
   }
    
    
    /*for(i=0;i<eid.length;i++)
    {
    	if(eid[i]==null)
    		break;
    	e.AddPfOpening(eid[i]);
    	AutoLog.addcontent("opening pf balance added for employee id :  "+ eid[i]);
    }*/
	
    
    //e.delEmploye();
    e.teardown(driver);
	
	}
	
	
	public Employe() throws InterruptedException, IOException
	{
		System.out.println("employe constructior called");
		invoke i = new invoke();
		Employe.driver = i.login();
		Thread.sleep(8000);
		System.out.println("driver initialised from constructor");
	}
	
	
	public void AddPfOpening(String empid) throws InterruptedException
	{
		
		driver.navigate().to(sitepath+"/EmployeeBalanceDetail.aspx?title=Employee%27s%20Balance%20Detail");
		driver.findElement(By.id(("ctl00_DefaultContent_Search1_txtEmployeeCode"))).sendKeys(empid);
		driver.findElement(By.id("ctl00_DefaultContent_Search1_btnSearch")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@class='GridView']/tbody/tr[2]/td[1]/a")).click();
		/*driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[1]/div/div/form/div[4]/div/table/tbody/tr/td/table[1]/tbody/tr[6]/td/div/table/tbody/tr[2]/td[1]/a\r\n"
				+ "[1]")).click();*/
		// this line will click on select button to select the employee for entering opening balance.
		
		
		WebElement wb = driver.findElement(By.id("ctl00_DefaultContent_dcContOpeningMember"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].value='80000';", wb);
		
		
		WebElement wc = driver.findElement(By.id("ctl00_DefaultContent_dcContOpeningVoluntary"));
		JavascriptExecutor jsf = (JavascriptExecutor)driver;
		jsf.executeScript("arguments[0].value='50000';", wc);
		
		
		WebElement wd = driver.findElement(By.id("ctl00_DefaultContent_dcContOpeningCompoany"));
		JavascriptExecutor jsg = (JavascriptExecutor)driver;
		jsg.executeScript("arguments[0].value='50000';", wd);
		
		
		driver.findElement(By.id("ctl00_DefaultContent_btnSave")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		driver.findElement(By.id("btnCloseMessage")).click();
		System.out.println("opening balance is added");
			
	}

	
	
	@Test
	public void addEmploye(String id,String name) throws InterruptedException, IOException
	{
		System.out.println("add employe method is called");
		System.out.println("value of id is : "+id +"  value of name is :"+ name);
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
		Thread.sleep(500);
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
		//driver.findElement(By.cssSelector("div[class='ac_results']")).click();
		//li[@class='ac_even ac_over']
		driver.findElement(By.xpath("//li[@class='ac_even ac_over']")).click();
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
	
	public void delEmploye() throws IOException
	{
		//driver.navigate().to("http://192.168.1.210:278/VersionUtility.aspx?title=Version%20Utility");
		driver.navigate().to(sitepath+"/VersionUtility.aspx?title=Version%20Utility");
				
		File file = new File("D:\\Del_Employee.txt");
        FileReader fr = new FileReader(file);
               
        //FileWriter fw = new FileWriter(file,true);
        //fw.append(did);
        //fw.append("8888");
        //fw.close();
        
        BufferedReader br = new BufferedReader(fr);
        String line;
        System.out.println("Reading text file using FileReader");
        while((line = br.readLine()) != null){
            //process the line
            System.out.println(line);
        }
        br.close();
        fr.close();
			
              
        WebElement fileInput = driver.findElement(By.id("ctl00_DefaultContent_fuEmployeeDeleteFile"));
        fileInput.sendKeys("D:\\Del_Employee.txt");
        
        driver.findElement(By.id("ctl00_DefaultContent_btnDeleteEmployee")).click();
        AutoLog.addcontent(" all employees are deleted ");
	}
	
}
