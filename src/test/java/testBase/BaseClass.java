package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import utilities.*;

public class BaseClass {

	public static WebDriver driver;
	public static Logger logger;
	public static ExcelUtility et = new ExcelUtility(System.getProperty("user.dir")+"\\testData\\Timesheet.xlsx");

	//protected ExtentTest logger;
	
	@BeforeTest
	@Parameters({"os","browser"})
	public void setup(String os, String br)
	{
		
		logger=LogManager.getLogger(this.getClass());//Log4j
		
		try {
			if (br.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
			} else if (br.equalsIgnoreCase("Edge")) {
				
				driver = new EdgeDriver();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		driver.manage().window().maximize();
	}
	
	public void reportPass(String str) {
		logger.info(str);
	}
	
	public void reportFail(String str) {
		logger.info(str);;
	}
	public void reportInfo(String log)
	{
		logger.info(log);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
	
