package POM_Pages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Generic.Flib;
import Generic.IAutoConstant;

public class LoginPage extends BaseClass implements IAutoConstant{

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(id="validateEmail")
	private WebElement usernameTextField;
	
	@FindBy(id="validatePassowrd")
	private WebElement passwordtextfield;
	
	@FindBy (xpath="//div[@class='d-grid gap-2']")     //"//button[@class='btn btn-secondary']")
	private WebElement submitButton;
	
	@FindBy (xpath="//div[@aria-label='Success']")
	public WebElement SuccessLoginMessage;
	
	
	@FindBy(xpath="//div[text()='*Either email or password is incorrect. Please correct and submit again.']")
	public WebElement invalidLoginValidationMessage;
	
	
	
	public void login(WebDriver driver) throws InterruptedException
	{
		usernameTextField.sendKeys("admin@blivinu.com");
		passwordtextfield.sendKeys("Blivinu@123@");
		Thread.sleep(3000);
		Flib flib=new Flib();
		flib.Scrolldown(driver);
		submitButton.click();
		
	}
	
	public void invalidLogin(String username,String password,WebDriver driver) throws InterruptedException, EncryptedDocumentException, IOException
	{
		
		usernameTextField.sendKeys(username);
		passwordtextfield.sendKeys(password);
		Thread.sleep(2000);
		Flib flib=new Flib();
		flib.Scrolldown(driver);
		Thread.sleep(2000);
		submitButton.click();
		boolean successmessage1=invalidLoginValidationMessage.isDisplayed();
		//System.out.println("The row number "+i+" username and password is invalid"+successmessage1);
		Assert.assertTrue(successmessage1);
		System.out.println(invalidLoginValidationMessage.getText());		
		Thread.sleep(1000);
		usernameTextField.clear();
		passwordtextfield.clear();
		
	
	}
	
	
	
	
	
}
