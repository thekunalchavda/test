package version_pack;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PFBalance extends invoke {
	WebDriver driver = new ChromeDriver();

	public static void main(String[] args) throws InterruptedException, IOException  {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		PFBalance p = new PFBalance();
		p.AddPfOpening("6001");
	}
	public PFBalance() throws InterruptedException, IOException
	{
		invoke in = new invoke();
		this.driver = in.login();
	}
	
	public void AddPfOpening(String i) throws InterruptedException, IOException
	{
		
		driver.navigate().to("http://192.168.1.210:278/EmployeeBalanceDetail.aspx?title=Employee%27s%20Balance%20Detail");
		driver.findElement(By.id(("ctl00_DefaultContent_Search1_txtEmployeeCode"))).sendKeys(i);
		driver.findElement(By.id("ctl00_DefaultContent_Search1_btnSearch")).click();
		
		/*driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[1]/div/div/form/div[4]/div/table/tbody/tr/td/table[1]/tbody/tr[6]/td/div/table/tbody/tr[2]/td[1]/a\r\n"
				+ "[1]")).click();*/
		// this line will click on select button to select the employee for entering opening balance.
		driver.findElement(By.xpath("//table[@class='GridView']/tbody/tr[2]/td[1]/a")).click();
		
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
	
	public void addContribution(String month)
	{
		
		
	}

}
