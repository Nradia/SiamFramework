package extentReportYoitube;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DataProFacebk {
	
	public WebDriver driver;
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	@BeforeTest
	public void setExtent() {
		//Report location
		htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/MyReportGov.html");
		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setReportName("HealthGov Report");
		htmlreporter.config().setTheme(Theme.STANDARD);
		//Create test case
		extent= new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("os","Windows 10");
		extent.setSystemInfo("Browser","Chrome");
		extent.setSystemInfo("Tester","Nora");
		extent.setSystemInfo("dinner","Pizza");
		
		
		
	}
	@BeforeMethod
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		
		driver= new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://healthcare.gov");	
	
	}
	
	
	@Test(dataProviderClass = dataProviderForTest.class ,dataProvider = "signInData")
	public void fillHomePage(String username, String pw)  {
		
		/*   The code to Pick a state and enter email =deal with the alert
		WebElement states=driver.findElement(By.xpath("//div[@id='prefix-questions']/select"));
		System.out.println(states.isDisplayed());
		Select select=new Select(states);
		select.selectByVisibleText("Texas");
		driver.findElement(By.id("prefix-emailInput")).sendKeys("kk@gmail.com");*/
		test=extent.createTest("fillHomePage");
		driver.findElement(By.xpath("//button[text()='Close subscription dialog']")).click();
		
		driver.findElement(By.linkText("Log in")).click();
		test.log(Status.PASS, "LOGin clicked");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
		test.log(Status.PASS,"Email entered in email field");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pw);
		test.log(Status.PASS,"Password entered in password field");
		driver.findElement(By.xpath("//button[@aria-label='Log in']")).click();
		test.log(Status.PASS,"Log in clicked");
		
	}
	@AfterTest
	public void endReport() {
		extent.flush();
	}
}
	


