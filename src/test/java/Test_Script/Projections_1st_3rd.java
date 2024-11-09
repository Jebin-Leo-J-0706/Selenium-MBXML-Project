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
	public class Projections_1st_3rd extends BaseTest {
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
	public void showDimension() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.showDimension();
		Thread.sleep(2000);
				
	}
	
	@Test(priority=4)
	public void showMachiningID() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.showMachiningID_or_ComponentID();
		Thread.sleep(2000);
		hp.getPercent_50_button().click();
	}
	
	@Test(priority=5)
	public void projections1st_3rd() throws IOException
	{
		HomePage hp=new HomePage(driver);
		hp.projectionsON();
		
		Flib flib=new Flib();
		flib.takeScreenShot(driver, "After 1st Angle Projection");
		
		
		String expectedScreenShotpath="./src/test/resources/ScreenShots/InitialScreenShots/After 1st Angle Projection.png";
		String actualScreenShotpath="./src/test/resources/ScreenShots/RunTimeScreenShots/After 1st Angle Projection.png";
		
		boolean compareresult=flib.compareScreenShots(expectedScreenShotpath, actualScreenShotpath);
		if(compareresult==true)
		{
			flib.setCellData(TestCase_ExcelPath,TestCase_SheetName_Projections, 2, 6, "Pass");
		}
		else
		{
			flib.setCellData(TestCase_ExcelPath,TestCase_SheetName_Projections, 2, 6, "Fail");
		}
		
	}
}
