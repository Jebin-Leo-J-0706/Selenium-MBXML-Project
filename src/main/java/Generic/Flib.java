package Generic;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Flib extends BaseTest {
	
	
	private static final String RandomStringUtils = null;

	

	//utility method to get data from property file.
	public String getDatafromPropertyFile(String path,String key) throws IOException
	{
		FileInputStream Fis=new FileInputStream(path);
		Properties p=new Properties();
		p.load(Fis);
		String value=p.getProperty(key);
		return value;
		
	}
	//Utility method to scroll Down
	public void Scrolldown(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(1000);
	}
	
	//utility method to get data from Excel.
	public String getDataFromExcelSheet(String path,String sheet_name,int row_no,int column_no) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(path);
		Workbook workbook=WorkbookFactory.create(fis);
		Sheet sheet=workbook.getSheet(sheet_name);
		Row row=sheet.getRow(row_no);
		Cell cell=row.getCell(column_no);
		String value=cell.getStringCellValue();
		return value;
	}
	
	//utility method to upload File
	public void upload(String samplepath) throws AWTException, InterruptedException
	{
		
		StringSelection stringselection=new StringSelection(samplepath);
		
		//copy the path
		Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(stringselection, null);
		
		Thread.sleep(2000);
		
		//paste the path
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		
		Thread.sleep(2000);
		
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
	}
		/*
		StringSelection ss=new StringSelection("path");
		Clipboard cb1=Toolkit.getDefaultToolkit().getSystemClipboard();	
		cb1.setContents(stringselection, null);
		
		StringSelection ss=new StringSelection("path");
		Clipboard cb2=Toolkit.getDefaultToolkit().getSystemClipboard();
		cb2.setContents(stringselection, null);
		
		StringSelection ss=new StringSelection("path");
		Clipboard cb3=Toolkit.getDefaultToolkit().getSystemClipboard();
		cb3.setContents(ss, null);
		
		StringSelection ss=new StringSelection("path");
		Clipboard cb4=Toolkit.getDefaultToolkit().getSystemClipboard();
		cb4.setContents(ss, ss);
		*/
	
	/*public void extentReports() throws IOException
	{
		ExtentReports extentReport=new ExtentReports();
		File file=new File("C:\\Users\\hp\\eclipse-workspace\\Automation\\Blivinu\\test-output\\Extent Report\\ExtentReports.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
		extentReport.attachReporter(sparkReporter);
		
		extentReport.createTest("Login").pass("The Login is passed");
		
		
		
		extentReport.flush();
		//Desktop.getDesktop().browse(new File("ExtentReports.html").toURI());
	}*/
	
	//to get Excel Row count
	public int getRowCount(String path,String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(path);
		Workbook workbook=WorkbookFactory.create(fis);
		Sheet sheet=workbook.getSheet(sheetName);
		int Totalrowcount=sheet.getLastRowNum();		
		workbook.close();
		fis.close();
		return Totalrowcount;
	}
	
	//to get Excel Cell/Column count of a single row
	public int getCellcount(String path,String sheetName,int rowNo) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(path);
		Workbook workbook=WorkbookFactory.create(fis);
		Sheet sheet=workbook.getSheet(sheetName);
		Row row=sheet.getRow(rowNo);
		int Totalcellcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return Totalcellcount;
		
	}
	
	//to get the cell Data from excel
	 public String getCellData(String path,String sheetName,int rowNo,int cellNo) throws EncryptedDocumentException, IOException
	 {
		 FileInputStream fis=new FileInputStream(path);
		 Workbook workbook=WorkbookFactory.create(fis);
		 Sheet sheet=workbook.getSheet(sheetName);
		 Row row=sheet.getRow(rowNo);
		 Cell cell=row.getCell(cellNo);
		 String celldata=cell.getStringCellValue();//getString cell value not getCellvalue() or we should use Cell.toString() or we should use DataFormetter class.
		 workbook.close();
		 fis.close();
		 return celldata;
		 
	 }
	 //to write the data in the cell(first read the excel and then write on it)
	 public void setCellData(String path,String sheetName,int rowNo,int cellNo,String data) throws EncryptedDocumentException, IOException
	 {
		 FileInputStream fis=new FileInputStream(path);
		 Workbook workbook=WorkbookFactory.create(fis);
		 Sheet sheet=workbook.getSheet(sheetName);
		 Row row=sheet.getRow(rowNo);//until this reading the file
		 
		 Cell cell=row.createCell(cellNo);//from here creating a cell from that row and setting a value for that cell
		 cell.setCellValue(data);
		 FileOutputStream fos=new FileOutputStream(path);
		 workbook.write(fos);
		 workbook.close();
		 fis.close();
		 
	 }
	 
	 //to  take ScreenShots
	 public void takeScreenShot(WebDriver driver,String imageName) throws IOException
	 {
		 //String timeStamp= new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		 TakesScreenshot ts=(TakesScreenshot)driver;
		 File src=ts.getScreenshotAs(OutputType.FILE); 
		 String targetFilePath="./src/test/resources/ScreenShots/RunTimeScreenShots/"+imageName+".png";
		 File dest=new File(targetFilePath);
		 Files.copy(src, dest);
		
		 
	 }
	 
	 
	 
	 //to compare the screenshot using AShot API. 
	 public boolean compareScreenShots(String expectedImagePath,String actualImagepath) throws IOException
	 {
		 File expectedImageFile=new File(expectedImagePath);
		 File actualImageFile=new File(actualImagepath);
		 BufferedImage expectedImage=ImageIO.read(expectedImageFile);
		 BufferedImage actualImage=ImageIO.read(actualImageFile);
		 
		 ImageDiffer id=new ImageDiffer();
		 ImageDiff diff=id.makeDiff(expectedImage, actualImage);
		 
		 if(diff.hasDiff()) {
			 System.out.println("Images are not Same");
			 return false;
		 }
		 else
		 {
			 System.out.println("Images are same");
			 return true;
		 }
		 
	 }
	 
	 
	 
	 //To generate a Random String we use a dependency "Apache commons"
	 public String generate_Random_String()
	 {
		 String generateString=org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5);
		 return generateString;
	 }
	 
	 //To generate a Random Number we use a dependency "Apache commons"
	 public String generate_Random_Number()
	 {
		 String generateNumber=org.apache.commons.lang3.RandomStringUtils.randomNumeric(5);
		 return generateNumber;
	 }
	
	 //To generate a Random String and random Number concanate we use a dependency "Apache commons"
	 public String generate_Random_AlphaNumeric()
	 {
		 String generateString=org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5);
		 String generateNumber=org.apache.commons.lang3.RandomStringUtils.randomNumeric(5);
		 String generateAlphaNumeric=generateString+generateNumber;
		 return generateAlphaNumeric;
	 }
}
