package POM_Pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}



	@FindBy (xpath="//span[text()='UPLOAD']")
	private WebElement uploadButton;
	
	@FindBy(xpath="//span[text()='SHOW DIM']")
	private WebElement ShowDimensionButton;
	
	@FindBy(xpath="//span[text()='HIDE DIM']")
	private WebElement HideDimensionButton;
	
	@FindBy(xpath="(//input[@role='switch'])[2]")
	private WebElement machiningChartON_OFFButton;
	
	@FindBy(xpath="(//input[@role='switch'])[3]")
	private WebElement projectionsON_OFFButton;
	
	@FindBy(xpath="//span[text()='DOWNLOAD']")
	private WebElement DownloadButton;
	
	@FindBy (xpath="//div[@title='25%']")
	private WebElement percent_25_button;
	
	@FindBy (xpath="//div[@title='50%']")
	private WebElement percent_50_button;
	
	public WebElement getPercent_50_button() {
		return percent_50_button;
	}



	@FindBy (xpath="//div[@title='75%']")
	private WebElement percent_75_button;
	
	public WebElement getPercent_75_button() {
		return percent_75_button;
	}



	@FindBy (xpath="//div[@title='100%']")
	private WebElement percent_100_button;
	
	//All the user defined methods Starts*************************************
	
	public void clickuploadButton()
	{
		uploadButton.click();
		
	}
	
	public void showDimension()
	{
		percent_25_button.click();
		ShowDimensionButton.click();
	}
	
	public void hideDimension()
	{
		
		HideDimensionButton.click();
	}
	
	public void showMachiningID_or_ComponentID()
	{
		machiningChartON_OFFButton.click();
	}
	
	public void projectionsON()
	{
		projectionsON_OFFButton.click();
	}
	
	public void clickDownloadButton()
	{
		DownloadButton.click();
	}
	
}
