package Test_Script;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Flib;
import POM_Pages.HomePage;
import POM_Pages.LoginPage;

@Listeners(Generic.Extent_Report_Manager.class)
public class UploadFile extends BaseTest{
	@Test(priority=1)
	public void Login() throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.login(driver);
		Thread.sleep(2000);
		boolean messagedisplayed=lp.SuccessLoginMessage.isDisplayed();
		if (messagedisplayed==true) {System.out.println("Logged in Successfully");}
		Assert.assertTrue(messagedisplayed);
	}
	
	@Test(priority=2)
	public void uploadFile() throws AWTException, InterruptedException, IOException
	{
		HomePage hp=new HomePage(driver);
		hp.clickuploadButton();
		Thread.sleep(2000);
		Flib flib=new Flib();
		flib.upload(SampleFilepath);
		//Capturing the screenShot
		flib.takeScreenShot(driver, "After_File_Upload");
		
		//Comparing the two ScreenShots
		String expectedScreenShotpath="./src/test/resources/ScreenShots/InitialScreenShots/After_File_Upload.png";
		String actualScreenShotpath="./src/test/resources/ScreenShots/RunTimeScreenShots/After_File_Upload.png";
		
		boolean compareresult=flib.compareScreenShots(expectedScreenShotpath, actualScreenShotpath);
		if(compareresult==true)
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_Upload_MBXML, 1, 6, "Pass");
		}
		else
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_Upload_MBXML, 1, 6, "Fail");
		}		
	}

}
