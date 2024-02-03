package pageObjects;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BaseClass;
 
public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
	}
 
	public String parentId=driver.getWindowHandle();
 
	
	@FindBy(xpath="//div[@id=\"O365_MainLink_MePhoto\"]")
	WebElement userBtn;
	@FindBy(xpath="//div[@id=\"mectrl_currentAccount_primary\"]")
	WebElement userName;
	@FindBy(xpath="//div[@id=\"mectrl_currentAccount_secondary\"]")
	WebElement userEmail;
	@FindBy(xpath="//div[contains(text(),'OneCognizant')]")
	WebElement oneCognizant;
	
	
	public void clickUserDetails() throws InterruptedException, IOException {
		Thread.sleep(5000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",userBtn);
		
		Thread.sleep(500);
		BaseClass baseClass = new BaseClass();
		baseClass.captureScreen("User Details");
 
	}
	public String getUserName() {
		String s1=userName.getText();
		System.out.println(s1);
		return s1;
	}
	public String getUserEmail() {
		String s2= userEmail.getText();
		System.out.println(s2);
		return s2;
	}
	public boolean isoneCognizantPageExists()
	{
//		Actions action=new Actions(driver);
//		action.moveByOffset(60,24).perform();
		try {
			Thread.sleep(3000);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",oneCognizant);
			return (oneCognizant.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}
	public void clickOneCognizant() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",oneCognizant);
	}
 
}