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
public class Show_Hide_Dimensions extends BaseTest {
	
	@Test(priority=1,groups={"Smoke"})
	public void Login() throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.login(driver);
		
	}
	
	@Test(priority=2,dependsOnMethods= {"Login"},groups={"Smoke"})
	public void uploadFile() throws AWTException, InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.clickuploadButton();
		Flib flib=new Flib();
		flib.upload(SampleFilepath);
		
	}

	@Test(priority=3/*,dependsOnMethods= {"Login","uploadFile"}*/,groups={"Smoke"})//dependsonMethods is used ...dependent method is login();
	public void showDimensions() throws InterruptedException, IOException
	{
		HomePage hp=new HomePage(driver);
		Flib flib=new Flib();
		flib.takeScreenShot(driver,"Before Show Dimension");
		hp.showDimension();
		Thread.sleep(2000);
		flib.takeScreenShot(driver, "After Show Dimension");
		Thread.sleep(2000);
		
		
		String expectedScreenShotpath="./src/test/resources/ScreenShots/InitialScreenShots/After Show Dimension.png";
		String actualScreenShotpath="./src/test/resources/ScreenShots/RunTimeScreenShots/After Show Dimension.png";
		
		boolean compareresult=flib.compareScreenShots(expectedScreenShotpath, actualScreenShotpath);
		if(compareresult==true)
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_ShowHideDimension, 1, 6, "Pass");
		}
		else
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_ShowHideDimension, 1, 6, "Fail");
		}
			
		
	}
	
	@Test(priority=4/*,dependsOnMethods= {"Login","uploadFile","showDimensions"}*/,groups={"Smoke"})
	public void HideDimensions() throws IOException, InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.hideDimension();
		Thread.sleep(2000);
		Flib flib=new Flib();
		flib.takeScreenShot(driver, "After Hide Dimension");
		
		
		String expectedScreenShotpath="./src/test/resources/ScreenShots/InitialScreenShots/After Hide Dimension.png";
		String actualScreenShotpath="./src/test/resources/ScreenShots/RunTimeScreenShots/After Hide Dimension.png";
		
		boolean compareresult=flib.compareScreenShots(expectedScreenShotpath, actualScreenShotpath);
		if(compareresult==true)
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_ShowHideDimension, 2, 6, "Pass");
		}
		else
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_ShowHideDimension, 2, 6, "Fail");
		}
			
	}

}
