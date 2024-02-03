package testCases;
 
 
import java.io.IOException;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;
 
public class TC_001_HomePage extends BaseClass{
	@Test(priority=1)
	public void verify_userDetails(){
		HomePage bp=new HomePage(driver);
 
		try {
			logger.info("**** starting TC_001_BeCognizantPage  *****");
 
			bp.clickUserDetails();
			logger.info("Clicked on UserDetails link");
			et.setCellData("User", 0, 0, bp.getUserName());
			logger.info("username received");
			et.setCellData("User", 1, 0, bp.getUserEmail());
			logger.info("email received");
		} 
		catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test(priority=2)
	public void verify_OneCognizant() {
		HomePage bp=new HomePage(driver);
		bp.isoneCognizantPageExists();
		logger.info("OneCognizant page exist");
		bp.clickOneCognizant();
		logger.info("Successfully clicked on oneCognizant");
	}
 
}