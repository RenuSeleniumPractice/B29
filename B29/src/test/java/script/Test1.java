package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.LoginPage;

public class Test1 extends BaseTest{

	@Test
	public void testA() {
		
		LoginPage l = new LoginPage(driver);
		l.setUserName("admin");
		l.setPassword("manager");
		String title = driver.getTitle();
		Reporter.log(title,true);
		String data2 = Excel.getData("./data/testdata.xlsx", "sheet2", 1, "UserName");
		Reporter.log(data2,true);
		String data1 = Excel.getData("./data/testdata.xlsx", "sheet1", 1, 1);
		Reporter.log(data1,true);
	}
}
