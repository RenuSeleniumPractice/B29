package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import page.EnterTimeTrackPage;
import page.LoginPage;

public class Test1 extends BaseTest{

	@Test
	public void testA() {
		
		LoginPage l = new LoginPage(driver);
		l.setUserName("admin");
		l.setPassword("manager");
		l.clickLoginButton();
		EnterTimeTrackPage e = new EnterTimeTrackPage(driver);
		boolean result = e.verifyHomePageIsDisplayed(wait, "Enter");
		//Assert.assertEquals(result, true);
		Assert.assertTrue(result);
		
	}
}
