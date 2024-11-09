package Test_Script;

import java.awt.AWTException;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Flib;
import POM_Pages.HomePage;
import POM_Pages.LoginPage;

public class Download extends BaseTest {
	
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
		public void projections1st_3rd()
		{
			HomePage hp=new HomePage(driver);
			hp.projectionsON();
		}
		
		@Test(priority=6)
		public void download()
		{
			HomePage hp=new HomePage(driver);
			hp.clickDownloadButton();
		}
}
