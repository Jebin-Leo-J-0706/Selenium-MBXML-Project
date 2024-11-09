package Generic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;



public class BaseTest implements IAutoConstant {

	
	public static WebDriver driver;
	
	@BeforeSuite
	public void beforeSuite()
	{
		
	}
	
	@BeforeTest
	public void beforetest()
	{
		
	}
	
	@BeforeClass
	//@Parameters({"browser"})
	public void setUp(/*String Browser*/) throws IOException
	{
		Flib flib=new Flib();
		
		String Browser=flib.getDatafromPropertyFile(property_file_path, "browser");
		
		if(Browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();	
		}
		else if(Browser.equalsIgnoreCase("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String URL=flib.getDatafromPropertyFile(property_file_path, "url");
		driver.get(URL);
		
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		
	}
	
	@AfterMethod
	public void afterMethod()
	{
		
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		//Thread.sleep(5000);
		//driver.close();
	}
	
	@AfterTest
	public void afterTest()
	{
		
	}
	
	@AfterSuite
	public void afterSuite()
	{
		
	}
	//to  take ScreenShots
		 public String takeScreenShotforExtentReport(String imageName) throws IOException
		 {
			 String timeStamp= new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
			 TakesScreenshot ts=(TakesScreenshot)driver;
			 File src=ts.getScreenshotAs(OutputType.FILE); 
			 String targetFilePath="./Reports/ExtentReports/"+imageName+"_"+timeStamp+".png";
			 File dest=new File(targetFilePath);
			 Files.copy(src, dest);
			 return targetFilePath;
			 
		 }
	
}
