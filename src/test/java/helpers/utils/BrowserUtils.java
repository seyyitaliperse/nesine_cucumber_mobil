package helpers.utils;

import helpers.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BrowserUtils {

    private static final Logger logger = LoggerFactory.getLogger(BrowserUtils.class);
    protected WebDriver driver;
    private WebDriverWait wait;
    private final int WAIT_TIME = 10000;

    public BrowserUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(WAIT_TIME));
    }

    public void click(WebElement element) {
        try {
            waitForVisibilityOfElement(element);
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Could not clicked element!");
        }
    }

    public void enterText(WebElement element, String text) {
        try {
            waitForVisibilityOfElement(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Could not send keys to element!");
        }
    }

    public String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (Exception e) {
            throw new RuntimeException("Could not get text from element!");
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForVisibilityOfElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException s) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    public void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public void assertContains(String actual, String expected, String message) {
        try {
            Assert.assertTrue(actual.contains(expected), message);
        } catch (AssertionError e) {
            String failureMessage = String.format("%s%nActual: %s%nExpected: %s", message, actual,
                    expected);
            System.err.println(failureMessage);
            throw new AssertionError(failureMessage, e);
        }
    }

    public void navigateTo(String url, WebElement element) {
        try {
            logger.info("Navigating to URL: " + url);
            driver.get(url);
            waitForVisibilityOfElement(element);
        } catch (Exception e) {
            throw new RuntimeException("Could not navigate to page!");
        }
    }



}
