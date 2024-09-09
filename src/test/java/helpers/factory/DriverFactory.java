package helpers.factory;

import helpers.readers.ConfigurationReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static WebDriverWait getWebDriverWait(){
        return new WebDriverWait(getDriver(), Duration.ofMillis(Long.parseLong(ConfigurationReader.get("waitMillis"))));
    }

    public static void initializeDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", ConfigurationReader.get("platformName"));
        caps.setCapability("appium:deviceName", ConfigurationReader.get("deviceName"));
        caps.setCapability("appium:platformVersion", ConfigurationReader.get("platformVersion"));
        caps.setCapability("appium:automationName", ConfigurationReader.get("automationName"));
        caps.setCapability("appium:appPackage", ConfigurationReader.get("appPackage"));
        caps.setCapability("appium:appActivity", ConfigurationReader.get("appActivity"));
        caps.setCapability("appium:newCommandTimeout", 300);

        String appiumServerURL = ConfigurationReader.get("appiumServerURL");

        try {
            driver.set(new AppiumDriver(new URL(appiumServerURL), caps));
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium Server URL: " + e.getMessage());
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
