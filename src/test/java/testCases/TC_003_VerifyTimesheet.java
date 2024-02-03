package testCases;

import java.io.IOException;

import javax.swing.border.EtchedBorder;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.*;
import utilities.*;
import pageObjects.TimesheetPage;
import testBase.BaseClass;

public class TC_003_VerifyTimesheet extends BaseClass {
	
	TimesheetPage ts;
	DateUtils du;
	
	@Test(priority = 1)
	void test_validateLast3Week() throws IOException {
		
		logger.info("**** Starting TC_003_VerifyTimesheet *****");
		ts = new TimesheetPage(driver);
		ts.navigateToTimesheet();

		du = new DateUtils();
		String[] last3 = du.last3Saturdays();

		String[] last3Timesheet = ts.verifyLast3Weeks();

		Assert.assertEquals(last3Timesheet, last3);
		
		for (int i=0;i<last3Timesheet.length;i++) {
			et.setCellData("Dates", i, 0, String.valueOf(last3Timesheet[i]));
		}

	}

//	@Test(dependsOnMethods = { "test_validateLast3Week" })
	@Test(priority = 2)
	void test_validateSearchBy() {
		
		logger.info("validating searchBY option...");
		boolean actual = ts.validateSearchBy();
		Assert.assertEquals(actual, true);
	}

	@Test(priority = 3)
	void test_validateSearchByDate() throws IOException, InterruptedException {
		
		logger.info("validating searchBYDate option...");
		du = new DateUtils();
		ts.selectTodaysDate();
		String week = ts.validateDate();
		String expected = du.thisWeek();

//		System.out.println(week+" "+expected);
		Assert.assertEquals(week, expected);
		
		logger.info("**** Finished TC_003_VerifyTimesheet *****");
	}
}
