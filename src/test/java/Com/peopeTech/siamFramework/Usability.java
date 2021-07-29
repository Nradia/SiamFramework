package Com.peopeTech.siamFramework;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Usability {
 
	public static WebDriver driver;
	
 @Parameters ({"url"})
  @Test
  public static void beforeMethod (String url) {
	  
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\radia\\eclipse-workspace\\siamFramework\\Drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get(url);
		
  }

}
