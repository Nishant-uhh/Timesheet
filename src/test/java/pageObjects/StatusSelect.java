package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class StatusSelect extends BasePage {

	public StatusSelect(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "#CTS_TS_LAND_WRK_CTS_TS_SEARCH_LBL")
	WebElement searchByLabel;

	@FindBy(id = "CTS_TS_LAND_WRK_CTS_TS_SEARCH")
	WebElement searchBy;

	Select searchBySelect;

	@FindBy(id = "CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS_LBL")
	WebElement statusLabel;

	@FindBy(id = "CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS")
	WebElement status;

	Select statusSelect;

	@FindBy(id = "CTS_TS_LAND_WRK_SEARCH")
	WebElement search;

	
	
	public boolean[] validateStatus() throws InterruptedException {
		searchByLabel.click();
		searchBySelect = new Select(searchBy);
		searchBySelect.selectByVisibleText("Status");

		Thread.sleep(4000);
		
		statusLabel.click();
		statusSelect = new Select(status);

		List<WebElement> options = statusSelect.getOptions();
		int size = options.size();
		
		String[] statusText = new String[size];
		for(int i=1; i<size; i++) {
			statusText[i] = options.get(i).getText();;
		}

		boolean[] statusValidation = new boolean[size - 1];
		String msg;

		for (int i = 1; i < size; i++) {
			statusLabel.click();
			statusSelect.selectByIndex(i);			
			Thread.sleep(3000);
			search.click();

			try {
				Thread.sleep(2000);
				msg = driver.findElement(By.id("shortmsg")).getText();
				driver.findElement(By.id("#ICOK")).click();
				statusValidation[i - 1] = true;
				continue;
			} catch (Exception e) {
	//				System.out.println("exception!");
				msg = "";
			}
	
			Thread.sleep(3000);
	
			List<WebElement> statuses = driver.findElements(
					By.xpath("//div[@class='ps_box-group psc_layout timesheet_period_group_box']/div[2]"));
			boolean flag = true;
			for (WebElement s : statuses) {
				String timesheetStatus = s.getText();
				flag = statusText[i].equals(timesheetStatus);
				if (flag == false) {
					System.out.println(timesheetStatus+"review !!");
					break;
				}
			}
			statusValidation[i - 1] = flag;
		}
		return statusValidation;
	}

}