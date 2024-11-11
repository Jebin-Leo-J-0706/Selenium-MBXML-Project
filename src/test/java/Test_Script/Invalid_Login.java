package Test_Script;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Flib;
import POM_Pages.LoginPage;

public class Invalid_Login extends BaseTest {

	
/*	@Test(groups={"Regression","Smoke","functional"})
	public void invalidLogin() throws InterruptedException, EncryptedDocumentException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		Flib flib=new Flib();
		
		for(int i=1;i<=3;i++)
		{
		String USERNAME=flib.getDataFromExcelSheet(Excel_Data_Path,Sheet_Name,i,0);
		String PASSWORD=flib.getDataFromExcelSheet(Excel_Data_Path, Sheet_Name, i, 1);
		lp.invalidLogin(USERNAME, PASSWORD, driver);
		Thread.sleep(2000);
		
		System.out.println("The Excel row number "+i+" username and password is invalid");
		
		flib.setCellData(Excel_Data_Path, Sheet_Name, i, 3, "Fail");
		flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_invalidLogin, i, 6, "Pass");
		
		}
		Thread.sleep(2000);
		
		
	}*/
	

	
	
	@Test(groups={"Regression","Smoke","functional"},dataProvider="invalidlogin")
	public void invalidLogin(String USERNAME,String PASSWORD) throws InterruptedException, EncryptedDocumentException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		Flib flib=new Flib();
		
		
		
		lp.invalidLogin(USERNAME, PASSWORD, driver);
		Thread.sleep(2000);
		for(int i=1;i<=3;i++) {
		System.out.println("The Excel row number "+i+" username and password is invalid");
		
		flib.setCellData(Excel_Data_Path, Sheet_Name, i, 3, "Fail");
		flib.setCellData(TestCase_ExcelPath, TestCase_SheetName_invalidLogin, i, 6, "Pass");
		}
		
		Thread.sleep(2000);
		
		
	}
	
	
	
	
	@DataProvider(name="invalidlogin",indices= {0,2})/*We should give this name attribute to Dataprovider annotation to link with 
										the test method.and also in @Test level we should add the
										 attribute as dataProvider= "that same name" to lin and also the
										  parameters should be added in the method to fetch those data 
										  multiple times. without using for loop.*/
	public Object [][] invalidLogindata()
	{
			Object [][] data= {
					
					{"adminkk@blivinu.com","Blivinu@123@"},
					{"adminlo@blivinu.com","123Blivinu@123@"},
					{"admin@blivinu.com","123Blivinu@123@"}
						
							};
			return data;
	}
			

@DataProvider(name="invalidloginsumm")
public Object [][] invaliddata()
{
	Object [][] data= {
			{"as","sd"},
			{"de","ss"},
			{"df","dfs"},
			{"sd","sfss"}
			};
	return data;
			
			
			
	}





	
	
	

	
	
}
