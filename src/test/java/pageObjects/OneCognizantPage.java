package pageObjects;
 
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class OneCognizantPage extends BasePage {
	HomePage hPage;

 
	public OneCognizantPage(WebDriver driver) {
		super(driver);
		hPage = new HomePage(driver);
	}
	
	
	@FindBy(xpath="//input[@id=\"oneC_searchAutoComplete\"]")
	WebElement searchBox;
	@FindBy(xpath="//div[@class='quickActionsResultText'][normalize-space()='Submit Timesheet']")
	WebElement Timesheet;

	
	public void search() throws InterruptedException {
		
		
		Set<String> winIds = driver.getWindowHandles();
		for(String id:winIds) {
			if(!id.equals(hPage.parentId)) {
				driver.switchTo().window(id);
			}
		}
//		Searching Timesheet
		searchBox.sendKeys("Timesheet");
		Thread.sleep(2000);
//		Clicked "ESA Timesheet"
		Timesheet.click();
	}
	

}