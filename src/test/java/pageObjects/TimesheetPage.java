package pageObjects;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import testBase.BaseClass;
import utilities.*;


public class TimesheetPage extends BasePage{

	OneCognizantPage oc ;
	
	public TimesheetPage(WebDriver driver) {
		super(driver);
	}
	
	DateUtils du;		
	
	// Elements
	@FindAll(@FindBy(xpath = "//div[@class='ps_box-link timesheet_period']/span/a"))
	List<WebElement> timesheetWeeks;

	public boolean navigateToTimesheet() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> windows = driver.getWindowHandles();

		for (String win : windows) {
			driver.switchTo().window(win);
			String title = driver.getTitle();

//			System.out.println(title);

			if (title.contains("Timesheet")) {
				return true;
			}
		}
		return false;
	}

	public String[] verifyLast3Weeks() {

//		System.out.println(timesheetWeeks.size());

		String[] last3Timesheet = new String[3];
		String sub;

		for (int i = 0; i < 3; i++) {
			sub = timesheetWeeks.get(i).getText();
			last3Timesheet[i] = sub.substring(0, 11);
		}

		return last3Timesheet;

	}

	@FindBy(id = "CTS_TS_LAND_WRK_CTS_TS_SEARCH_LBL")
	WebElement searchByLabel;

	@FindBy(id = "CTS_TS_LAND_WRK_CTS_TS_SEARCH")
	WebElement searchBy;

	Select searchBySelect;

	@FindBy(id = "CTS_TS_LAND_WRK_DATE")
	WebElement datePicker;

	@FindBy(id = "CTS_TS_LAND_WRK_DATE$prompt")
	WebElement calendar;

	@FindBy(id = "curdate")
	WebElement curDate;

	@FindBy(id = "CTS_TS_LAND_WRK_SEARCH")
	WebElement search;

	public boolean validateSearchBy() {
		return searchBy.isDisplayed();
	}

	public void selectTodaysDate() throws IOException, InterruptedException {
		du = new DateUtils();

		searchByLabel.click();
		searchBySelect = new Select(searchBy);
//		List<WebElement> sel = searchBySelect.getOptions();

//		for (WebElement ele : sel) {
//			System.out.println(ele.getText());
//		}
		searchBySelect.selectByVisibleText("Date");

//		String today = du.todaysDate();
//		datePicker.sendKeys(today);
		calendar.click();
		curDate.click();
		search.click();
		
		Thread.sleep(2000);
		BaseClass baseClass = new BaseClass();
		baseClass.captureScreen("Timesheet_Current_Date");

	}

//	@FindAll(@FindBy(xpath = "//div[@class='ps_box-link timesheet_period']/span/a"))
//	@CacheLookup
//	List<WebElement> timeSheets;

	public String validateDate() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> timeSheets = driver
				.findElements(By.xpath("//div[@class='ps_box-link timesheet_period']/span/a"));
//		System.out.println(timesheetWeeks.size());
		if (timeSheets.size() == 1) {
			return timeSheets.get(0).getText();
		}
		return "";
	}

//	@FindBy(id = "CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS")
//	@CacheLookup
//	WebElement status;

//	Select statusSelect;

//	@FindBy(id = "CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS_LBL")
//	WebElement Status;
	
}
