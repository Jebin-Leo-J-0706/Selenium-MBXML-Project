package Test_Script;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Flib;
import POM_Pages.HomePage;
import POM_Pages.LoginPage;

@Listeners(Generic.Extent_Report_Manager.class)

public class Machining_Chart_IDs extends BaseTest {

	
	@Test(priority=1)
	public void login() throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.login(driver);
	}
	
	@Test(priority=2)
	public void upload() throws AWTException, InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.clickuploadButton();
		Flib flib=new Flib();
		flib.upload(SampleFilepath);		
	}
	
	@Test(priority=3)
	public void showDimension() throws InterruptedException, IOException
	{
		HomePage hp=new HomePage(driver);
		hp.showDimension();
		hp.getPercent_75_button().click();
		Flib flib=new Flib();
		flib.takeScreenShot(driver, "After Component ID displayed");
		Thread.sleep(2000);
		//Comparing ScreenShot
		String expectedScreenShotpath="./src/test/resources/ScreenShots/InitialScreenShots/After Component ID displayed.png";
		String actualScreenShotpath="./src/test/resources/ScreenShots/RunTimeScreenShots/After Component ID displayed.png";
		
		boolean compareresult=flib.compareScreenShots(expectedScreenShotpath, actualScreenShotpath);
		if(compareresult==true)
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_Machiningchart, 2, 6, "Pass");
		}
		else
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_Machiningchart, 2, 6, "Fail");
		}		
				
	}
	
	@Test(priority=4)
	public void showMachiningID() throws InterruptedException, IOException
	{
		HomePage hp=new HomePage(driver);
		Thread.sleep(2000);
		hp.getPercent_75_button().click();
		hp.showMachiningID_or_ComponentID();
		//taking Screenshot
		Flib flib=new Flib();
		flib.takeScreenShot(driver, "After Machining ID displayed");
		
		//Comparing ScreenShot
		String expectedScreenShotpath="./src/test/resources/ScreenShots/InitialScreenShots/After Machining ID displayed.png";
		String actualScreenShotpath="./src/test/resources/ScreenShots/RunTimeScreenShots/After Machining ID displayed.png";
		
		boolean compareresult=flib.compareScreenShots(expectedScreenShotpath, actualScreenShotpath);
		if(compareresult==true)
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_Machiningchart, 1, 6, "Pass");
		}
		else
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_Machiningchart, 1, 6, "Fail");
		}
		
	}
	
}
