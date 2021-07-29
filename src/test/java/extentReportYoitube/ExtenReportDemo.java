package extentReportYoitube;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtenReportDemo {
	
	
	
	public WebDriver driver;
	
		//specify document title, theme
	public ExtentHtmlReporter htmlreporter;
		//create entry in the report
	public ExtentReports extent;
		// update status in report. pass, fail,skip
	public ExtentTest test;
	
	
	@BeforeTest
	public void setExtent() {
		htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/MyReport.html");
		htmlreporter.config().setDocumentTitle("Automation Report=docment title");//title of report
		htmlreporter.config().setReportName("Functional teats=report title");
		htmlreporter.config().setTheme(Theme.DARK);
		// create test case 
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Hostname","LocalHost");
		extent.setSystemInfo("os","Window10");
		extent.setSystemInfo("Browser","Chrome");
		extent.setSystemInfo("Name","Radia");
		
		
	}
	
	@Test
	public void signInClick() {
		
		test=extent.createTest("signInClick");
		driver.findElement(By.xpath("//span[text()='Hello, Sign in']")).click();
	test.log(Status.PASS, " Sign in button clicked");
	driver.findElement(By.id("ap_email")).sendKeys("radia@gmail.com");
	test.log(Status.PASS,"Emal enterd successfuly in field");
	}
	
	@Test
	public void validateTitle() {
		
		test=extent.createTest("validateTitle");
		String title=driver.getTitle();
		Assert.assertEquals(title, "amaznv");
		System.out.println("title is "+title);
		test.log(Status.PASS, " Title is correct");
		
	}
	@Test
	public void validateLogo() {
		
		test=extent.createTest("validateLogo");
		boolean flag=driver.findElement(By.xpath("//a[@aria-label='Amazon']")).isDisplayed();
		Assert.assertTrue(flag,"Logo not displayed");
		test.log(Status.PASS, " Logo is diplayed");
		
	}
	
@AfterTest
public void endReport() {
	extent.flush();
}
@BeforeMethod
public void setupbrowser() {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\radia\\eclipse-workspace\\siamFramework\\Drivers\\chromedriver.exe");
    driver= new ChromeDriver();
	//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	//driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	driver.get("https://amazon.com");
}
@AfterMethod
public void teardown(ITestResult result) throws IOException {
	if (result.getStatus()==ITestResult.FAILURE) {
		test.log(Status.FAIL," TEST CASE FAILED IS "+result.getName() );
		test.log(Status.FAIL," TEST CASE FAILED IS "+result.getThrowable() );

		String screenshotpath=getScreenShot(driver, result.getName());
		test.addScreenCaptureFromPath(screenshotpath);
	}
	driver.quit();
}

public static String getScreenShot(WebDriver driver, String screenshotname) throws IOException {
	
	Date date= new Date();
	String dateName=new SimpleDateFormat("yyyy-MM-dd-hh-mm").format(date);
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	
	String destination= System.getProperty("user.dir")+"/Screenshots/"+screenshotname+dateName+".png";
	File finaldestination= new File(destination);
	FileUtils.copyFile(source,finaldestination);
	return destination;
	
	
}
}