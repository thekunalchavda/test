package version_pack;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class test extends AutoLog{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//WebDriver driver=new ChromeDriver();
		/*System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		takeshot(driver,title);*/
		
		AutoLog.myprint("hii hello");
	}
	
	public static void takeshot(WebDriver driver, String title)
	{

		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			
			ImageIO.write(screenshot.getImage(), "jpg", new File("D:\\"+title+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
