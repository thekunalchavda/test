package version_pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class AutoLog {
	static String fname;
	
	public static void main(String[] args) throws InterruptedException,FileNotFoundException,IOException {
		Date date = Calendar.getInstance().getTime();
		//Date pdate;
        // Display a date in day, month, year format
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        System.out.println("Today : " + today);
       
        
     System.out.println("***************************");   
     String dateTime = "11/15/2013 08:00:00";
     org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
     DateTime jodatime = dtf.parseDateTime(dateTime);
     org.joda.time.format.DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/MM/yyyy");
     System.out.println(dtfOut.print(jodatime));
     System.out.println("***************************");
        
        System.out.println(org.joda.time.LocalDate.now());              
        org.joda.time.LocalDate now = org.joda.time.LocalDate.now();
        //org.joda.time.LocalDate Behind = now.minusYears(5);
        now = now.minusMonths(5);
        org.joda.time.format.DateTimeFormatter dd = DateTimeFormat.forPattern("dd/MM/yyyy");
       
        System.out.println(dd.print(now) + "good one");
      
        String ff = dd.print(now).toString();
        System.out.println(ff + "good orwere");
        //System.out.println(Behind);
        //System.out.println(now);
        
        String chek[] = getcheque();
        for(int k=0;k<chek.length;k++)
        {
        	System.out.println(chek[k]);	
        }
        
               
	}
	
	public static void addcontent(String str) throws IOException
	{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		fname = "log"+java.time.LocalDate.now();
		File f = new File("E:\\"+fname+".txt");
      	
		try (FileWriter fw = new FileWriter(f,true)) {
			fw.append(str + "    " +  dateFormat.format(date) + "\n");
		}  
		
		
	}
	
	public static String printdate() {
		Date date = Calendar.getInstance().getTime();

        // Display a date in day, month, year format
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        return today;
        
	}
	
	public static String addtodate(int k,String sdate)
	{
	    org.joda.time.LocalDate now = LocalDate.parse(sdate);
        org.joda.time.LocalDate Behind = now.plusDays(k);
        org.joda.time.format.DateTimeFormatter dd = DateTimeFormat.forPattern("dd/MM/yyyy");
        String today = dd.print(Behind).toString();
        return today;
    }
	
	public static String nextdays(int k)
	{
	    org.joda.time.LocalDate now = org.joda.time.LocalDate.now();
        org.joda.time.LocalDate Behind = now.plusDays(k);
        org.joda.time.format.DateTimeFormatter dd = DateTimeFormat.forPattern("dd/MM/yyyy");
        String today = dd.print(Behind).toString();
        return today;
    }
	
	public static String prevdays(int k)
	{
	    org.joda.time.LocalDate now = org.joda.time.LocalDate.now();
        org.joda.time.LocalDate Behind = now.minusDays(k);
        org.joda.time.format.DateTimeFormatter dd = DateTimeFormat.forPattern("dd/MM/yyyy");
        String today = dd.print(Behind).toString();
        return today;
    }
	
	public static String prevyears(int k)
	{
	    org.joda.time.LocalDate now = org.joda.time.LocalDate.now();
        org.joda.time.LocalDate Behind = now.minusYears(k);
        org.joda.time.format.DateTimeFormatter dd = DateTimeFormat.forPattern("dd/MM/yyyy");
        String today = dd.print(Behind).toString();
        return today;
    }
	
	public static String prevmonths(int k)
	{
	      
		org.joda.time.LocalDate now = org.joda.time.LocalDate.now();
        org.joda.time.LocalDate Behind = now.minusMonths(k);
        org.joda.time.format.DateTimeFormatter dd = DateTimeFormat.forPattern("dd/MM/yyyy");
        String today = dd.print(Behind).toString();
        return today;
    }
	
	public static void takeshot(WebDriver driver, String title)
	{
		org.joda.time.LocalDate now = org.joda.time.LocalDate.now();
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000)).takeScreenshot(driver);
		try {
			
			ImageIO.write(screenshot.getImage(), "jpg", new File("D:\\"+title+"   "+now+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void myprint(String s)
	{
		System.out.println(s);
	}
	
	public static String[] getcheque()
	{
		Random r = new Random();
		int digit;
		digit = r.nextInt(10000);
		String number = String.valueOf(digit);
		String[] banks= {"sbi","bob","hdfc","axis","boi"};
		String bankname= banks[r.nextInt(banks.length)];
		String[] cheques = {number,bankname};
		return cheques;
    }
	
}
