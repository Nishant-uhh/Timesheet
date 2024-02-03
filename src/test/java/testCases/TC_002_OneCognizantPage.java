package testCases;

import org.testng.annotations.Test;

import pageObjects.OneCognizantPage;
import testBase.BaseClass;

public class TC_002_OneCognizantPage extends BaseClass{
	
	
	@Test(priority=3)
	public void clickSubmitTimesheet() throws Exception, InterruptedException {
		
		logger.info("**** Starting TC_002_OneCognizantPage  ****");
		OneCognizantPage oc = new OneCognizantPage(driver);
		
		oc.search();
		
		logger.info("clicked on SubmitTimesheet..");
		logger.info("**** Finished TC_002_OneCognizantPage  ****");
	}

}
