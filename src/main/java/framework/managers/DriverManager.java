package framework.managers;

import framework.utils.PropConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static framework.utils.PropConst.TYPE_BROWSER;

public class DriverManager {
    private WebDriver driver;
    private final TestPropManager propManager = TestPropManager.getINSTANCE();

    private static DriverManager INSTANCE = null;

    private DriverManager() {
    }

    public static DriverManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        switch (propManager.getProperty(TYPE_BROWSER)) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropConst.PATH_CHROME_DRIVER_WINDOWS));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", propManager.getProperty(PropConst.PATH_GECKO_DRIVER_WINDOWS));
                driver = new FirefoxDriver();
                break;
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("84.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://130.193.49.85:4444/wd/hub").toURL(),
                            capabilities);
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default: break;
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
