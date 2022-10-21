package generic;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutomation{

	public WebDriver driver;
	public WebDriverWait wait;
	public String remoteAddr="http://localhost:4444";
	 
	@BeforeMethod
	public void openApp() throws MalformedURLException {
	
		String url = getValue("URL");
		long ito = Long.parseLong(getValue("ITO"));
		long eto = Long.parseLong(getValue("ETO"));
		String grid=getValue("GRID");
		String 	browserName=getValue("BROWSER");
			
		if(grid.equalsIgnoreCase("YES"))
		{
			Reporter.log("Opening "+browserName +" in remote system", true);
			URL gridUrl= new URL(remoteAddr);
			if(browserName.equalsIgnoreCase("chrome"))
			{
				Reporter.log("Opening chrome in remote system", true);
				driver=new RemoteWebDriver(gridUrl,new ChromeOptions()); 
			}
			else
			{
				Reporter.log("Opening firefox in remote system", true);
				driver=new RemoteWebDriver(gridUrl,new FirefoxOptions()); 
			}
			
		}
		else
		{
			if(browserName.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ito));
		wait = new WebDriverWait(driver,Duration.ofSeconds(eto));
	}
	
	@AfterMethod
	public void closeApp() {
		driver.quit();
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
