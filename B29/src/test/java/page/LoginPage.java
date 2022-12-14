package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.WebGeneric;

public class LoginPage {

	@FindBy(id="username")
	private WebElement unTB;
	@FindBy(name="pwd")
	private WebElement password;
	@FindBy(xpath="//div[.=\"Login \"]")
	private WebElement loginBTN;
	@FindBy(xpath = "//span[contains(.,'invalid')]")
	private WebElement errMsg;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String 	un) {
		unTB.sendKeys(un);
	}
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLoginButton() {
		loginBTN.click();
	}
	
	public boolean verifyErrMsgIsDisplayed(WebDriverWait wait) {
		return WebGeneric.verifyElementDisplayed(wait, errMsg);
	}
}
