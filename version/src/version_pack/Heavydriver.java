package version_pack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Heavydriver {

	public WebDriver driver = new ChromeDriver();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
	}
	
	public WebDriver get_driver()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		return this.driver;
	}
	
	public void set_driver(WebDriver d)
	{
		this.driver = d;
	}

}
