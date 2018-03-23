package com.myntra.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.myntra.qa.util.TestUtil;
import com.myntra.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver=null;
	public static Properties prop=null;
	public static EventFiringWebDriver edriver=null;
	public static WebEventListener eventListner=null;
	protected static Logger logger = Logger.getLogger(TestBase.class.getName());
	private static FileInputStream fiStream = null;
	public static final String envPropFileName = "C:\\SeleniumMavenProject\\SQSSeleniumProject\\src\\main\\java\\com\\myntra\\qa\\config\\config.properties";
	
	public TestBase()
	{
		//method to read properties file
		try
		{
			logger.info("reading environment properties file");
			fiStream = new FileInputStream(envPropFileName);
			prop = new Properties();
			prop.load(fiStream);
			
		}catch(FileNotFoundException e)
		{
			logger.error("Environment prop file is not found", e);
		}catch(IOException e)
		{
			logger.error("Environment prop file is not loaded", e);
		}
	}
	
	public static void initialization() throws IOException
	{
		String browserName = prop.getProperty("browser");
		
			if(browserName.equalsIgnoreCase("Mozilla"))
			{
				//Save the path of the XPI files as per your saved location
				//String firebugPath = "C:\\Users\\telango\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\s4i5slfl.default\\extensions\\firebug@software.joehewitt.com.xpi";
				//String firepathPath = "C:\\Users\\telango\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\s4i5slfl.default\\extensions\\FireXPath@pierre.tholence.com.xpi";
				
				//Create a new Profile for the new settings
				//FirefoxProfile profile = new FirefoxProfile();
				// Pass the XPIs path to the profile
				//profile.addExtension(new File(firebugPath));
				//profile.addExtension(new File(firepathPath));
				
				//Set default Firebug preferences and FirePath preferences
				//String ext = "extensions.firebug.";
				//String ext1 = "extensions.firepath.";
				
				/*profile.setPreference(ext + "currentVersion", "2.0.19");
				profile.setPreference(ext1 + "currentVersion", "0.9.7");
				profile.setPreference(ext + "allPagesActivation", "on");
				profile.setPreference(ext + "defaultPanelName", "net");
				profile.setPreference(ext + "net.enableSites", true);
				*/
				driver = new FirefoxDriver();
				
			}
			
			if(browserName.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")+ "\\src\\main\\resources\\chromedriver_win32_2.23\\chromedriver.exe");
				//"C:\\eclipse-standard-kepler-SR2-win32-x86_64\\chromedriver_win32_2.23\\chromedriver.exe"
				driver = new ChromeDriver();
				
			}
			if(browserName.equalsIgnoreCase("Iexplore"))
			{
				System.setProperty("webdriver.ie.driver",
						"D:/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}
			
			edriver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListner = new WebEventListener();
			edriver.register(eventListner);
			
			driver = edriver;
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
	}

}
