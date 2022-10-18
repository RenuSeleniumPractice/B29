package generic;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutomation{

	public WebDriver driver;
	public WebDriverWait wait;
	static 
	{
		WebDriverManager.chromedriver().setup();
	}
	@BeforeMethod
	public void openApp() {
		driver=new ChromeDriver();
		String url = getValue("URL");
		long ito = Long.parseLong(getValue("ITO"));
		long eto = Long.parseLong(getValue("ETO"));
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ito));
		wait = new WebDriverWait(driver,Duration.ofSeconds(eto));
	}
	
	@AfterMethod
	public void closeApp() {
		driver.close();
	}
	
	public String getValue(String key) {
		String value="";
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(CONFIG_PATH));
			value = p.getProperty(key);
		}
		catch(Exception e) {
			
		}
		return value;
	}
}
