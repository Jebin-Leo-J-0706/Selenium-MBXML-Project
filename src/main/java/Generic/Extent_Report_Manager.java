package Generic;

import java.io.IOException;
//import java.sql.Date;//dont use SQL date .Onlu use java.util.Date
import java.util.Date;
import java.text.SimpleDateFormat;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import POM_Pages.BaseClass;

public class Extent_Report_Manager implements ITestListener{
	
	
	public ExtentSparkReporter sparkReporter;//to manage UI of the report.
	public ExtentReports extentReports;//It populates common information in the report.
	public ExtentTest extentTest;//Create test case Entries on the report and update Status of the test methods.We are not creating object of this test.Used  only for storing the ExtentReports object as return type.
	
	
	@Override
	public void onStart(ITestContext context) {
		//Creating timeStamp to avoid overwriting of reports.
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
		Date d=new Date(0);
		String timeStamp=sdf.format(d);*/
		
		//Single Line command to create Time Stamp
		String timeStamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		
		String reportName="Extent_Report"+timeStamp+".html";							
		
		sparkReporter=new ExtentSparkReporter("./Reports/ExtentReports/"+reportName);	//Specifies the location of the report	
		//Adding or setting the UI configurations to the Extent report.
		sparkReporter.config().setDocumentTitle("MBXML Application Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);	
		
		
		extentReports=new ExtentReports();
		extentReports.attachReporter(sparkReporter);//like attaching the bogi to the Engine.
		
		//Adding common information in the report.
		
		extentReports.setSystemInfo("Browser", "Chrome");
		extentReports.setSystemInfo("Operating System", "Windows 11");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("Reporter Name", "Jebin Leo J");
		extentReports.setSystemInfo("Approved By","L Kumaresan");
		
	}
	

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//Also store the extentReports object inside the extentTest object to access the Each and every tests executed.
		extentTest=extentReports.createTest(result.getTestClass().getName());//Creates a new Entry in the report.getName() method of ITestListener reference Captures the name of the method that is onTestSuccess.
		//Adding Logs and categorys to the Failed Test
		extentTest.log(Status.PASS, "The TestCase passed is: "+result.getName());//log or update the Status as Pass/Fail/Skip at the time of testSuccess.
		extentTest.assignCategory(result.getMethod().getGroups());
		
	}
	WebDriver driver;
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest=extentReports.createTest(result.getTestClass().getName());//Creates a new entry in the report on test failure with that Test method name.
		
		extentTest.log(Status.FAIL, "The Test case Failed is: "+result.getName());
		extentTest.log(Status.FAIL, "The Test case Failed is due to: "+result.getThrowable());//It will give u the Error Message that is thrown in the console.
		extentTest.assignCategory(result.getMethod().getGroups());
		
		
		
		//attaching the ScreenShot in Test Level on Extent report on Test-Failure.v.v.important
		
		String screenshotImagePath;
		
			try {
				screenshotImagePath = new BaseTest().takeScreenShotforExtentReport(result.getName());
				extentTest.addScreenCaptureFromPath(screenshotImagePath,result.getName()+"Error Screenshot");//overrided method to provide the screenshot Description.
				//extentTest.addScreenCaptureFromBase64String(screenshotImagePath); 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Attach the ScreenShot in the Log level
			try {				
				screenshotImagePath=new BaseTest().takeScreenShotforExtentReport(result.getName());
				extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotImagePath).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest=extentReports.createTest(result.getTestClass().getName());
		
		extentTest.log(Status.SKIP, "The testCase Skipped  are: "+result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
	
		extentReports.flush();
	}
	
	

}
