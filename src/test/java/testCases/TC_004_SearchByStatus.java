package testCases;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.StatusSelect;
import testBase.BaseClass;

public class TC_004_SearchByStatus extends BaseClass{

	StatusSelect st;
	
	@Test
	public void test_validateStatus() throws Exception {
		
		logger.info(" Starting TC_004_SearchByStatus ");
		st = new StatusSelect(driver);
		
		SoftAssert sa = new SoftAssert();
		
		boolean[] statusValidation = st.validateStatus();
		System.out.println("bool size: "+statusValidation.length);
		for(boolean bool:statusValidation) {
			
			System.out.println(bool+" in validation! ");
			sa.assertEquals(bool, true);
		}
		sa.assertAll();
		
		for (int i=0;i<statusValidation.length;i++) {
			et.setCellData("Status", i, 0, String.valueOf(statusValidation[i]));
		}

		logger.info(" Finished TC_004_SearchByStatus ");
	}
}
