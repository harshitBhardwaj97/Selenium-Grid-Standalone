package selenium.grid.example;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGridTest {

	WebDriver driver;
	String gridUrl = "http://localhost:4444/";

	@BeforeMethod
	@Parameters({ "browser" })
	public void setup(String browser) throws MalformedURLException {

		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability("browserName", "chrome");
			driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("browserName", "firefox");
			driver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
			break;
		case "microsoftedge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setCapability("browserName", "MicrosoftEdge");
			driver = new RemoteWebDriver(new URL(gridUrl), edgeOptions);
			break;
		case "safari":
			SafariOptions safariOptions = new SafariOptions();
			safariOptions.setCapability("browserName", "safari");
			driver = new RemoteWebDriver(new URL(gridUrl), safariOptions);
			break;
		default:
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}

		driver.manage().window().maximize();
		driver.get("https://google.com");
	}

	@Test
	public void seleniumGridTest() {
		String title = driver.getTitle();
		assertEquals("Google", title, "Title doesn't match !");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
