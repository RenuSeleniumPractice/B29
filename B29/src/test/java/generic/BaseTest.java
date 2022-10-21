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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutomation{

	public WebDriver driver;
	public WebDriverWait wait;
	 
	@Parameters({"grid","remoteAddr","browserName"})
	@BeforeMethod
	public void openApp(@Optional("")String grid,@Optional("") String remoteAddr,@Optional("") String browserName) throws MalformedURLException {
	
		String url = getValue("URL");
		long ito = Long.parseLong(getValue("ITO"));
		long eto = Long.parseLong(getValue("ETO"));
		
		if(browserName.equals(""))
		{
			grid=getValue("GRID");
		}
		if(remoteAddr.equals(""))
		{
			remoteAddr= getValue("REMOTE");
		}
		if(browserName.equals(""))
		{
			browserName = getValue("BROWSER");
		}
			
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
