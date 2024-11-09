package Test_Script;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Flib;
import POM_Pages.LoginPage;

public class Login extends BaseTest {
	
	@Test(priority=1,groups="Regression")
	public void Login1() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.login(driver);
		Thread.sleep(2000);
		boolean messagedisplayed=lp.SuccessLoginMessage.isDisplayed();
		if (messagedisplayed==true) {System.out.println("Logged in Successfully");}
		Assert.assertTrue(messagedisplayed);
		Flib flib=new Flib();;
		if(messagedisplayed==true)
		{
			flib.setCellData(TestCase_ExcelPath, TestCase_SheetName, 1, 6, "PASS");
		}
		
		
	}

}
