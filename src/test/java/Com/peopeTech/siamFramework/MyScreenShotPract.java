package Com.peopeTech.siamFramework;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class MyScreenShotPract {

	WebDriver driver;
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\radia\\eclipse-workspace\\siamFramework\\Drivers\\chromedriver.exe");
		
		driver=new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://pbskids.org");
		
	}
	
	/*@Test(Dtaparovider="dp")
	public void parentsClick() {
		
		driver.findElement(By.linkText("PARENTS")).click();
	}
	  
	Date date=new Date();
	DateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd-hh-mm");
	
	try {
	FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/Screenshots/"+"test"+dateformat.format(date)+".png"));
	} catch(Exception e) {
		System.out.println("exception wjile screenshot "+e.getMessage());
	}
	
}	
@DataProvider()*/
	}
