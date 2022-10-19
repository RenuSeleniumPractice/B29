package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.EnterTimeTrackPage;
import page.LoginPage;

/*
 * 
 */

public class ValidLogin extends BaseTest{
	@Test(priority = 1)
	public void testValidLogin(){
		String un=Excel.getData(XL_PATH, "ValidLogin", 1, 0);
		String pw=Excel.getData(XL_PATH, "ValidLogin", 1, 1);
		String eTitle=Excel.getData(XL_PATH, "ValidLogin", 1, 2);
		String fMsg=Excel.getData(XL_PATH, "ValidLogin", 1, 3);
		
		// 1. Enter Valid User name(admin)
		LoginPage l = new LoginPage(driver);
		l.setUserName(un);
		//2. Enter valid Password(manager)
		l.setPassword(pw);
		//3. Click on Login button
		l.clickLoginButton();
		//4. Verify that home page is displayed
		EnterTimeTrackPage e = new EnterTimeTrackPage(driver);
		boolean result = e.verifyHomePageIsDisplayed(wait, eTitle);
		Assert.assertTrue(result, fMsg);
	}
}
