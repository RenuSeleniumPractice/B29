package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.LoginPage;

public class InvalidLogin extends BaseTest{
	@Test(priority = 1)
	public void testValidLogin(){
		
		int rc=Excel.getRow(XL_PATH, "InvalidLogin");
		for(int i=1;i<=rc;i++) {
			//Read data from excel
			String un=Excel.getData(XL_PATH, "InvalidLogin", i, "Username");
			String pw=Excel.getData(XL_PATH, "InvalidLogin", i, "Password");
			String fMsg=Excel.getData(XL_PATH, "InvalidLogin", i, "FailMsg");
		
		// 1. Enter Valid User name(admin)
		LoginPage l = new LoginPage(driver);
		l.setUserName(un);
		//2. Enter valid Password(manager)
		l.setPassword(pw);
		//3. Click on Login button
		l.clickLoginButton();
		//4. Verify that home page is displayed
		boolean result = l.verifyErrMsgIsDisplayed(wait);
		Assert.assertTrue(result, fMsg);
	}
  }
}
