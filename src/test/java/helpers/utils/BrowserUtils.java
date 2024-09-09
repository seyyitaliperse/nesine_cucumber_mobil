package helpers.utils;

import helpers.container.Context;
import helpers.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BrowserUtils {

    protected static final Logger logger = LoggerFactory.getLogger(BrowserUtils.class);
    private final WebDriverWait wait;
    private final Context context;

    public BrowserUtils(Context context) {
        this.context = context;
        this.wait = context.getWebDriverWait();
    }

    public void tap(WebElement element) {
        try {
            waitForVisibilityOfElement(element);
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Could not click element!", e);
        }
    }

    public void enterText(WebElement element, String text) {
        try {
            waitForVisibilityOfElement(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Could not send keys to element!", e);
        }
    }

    public String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (Exception e) {
            throw new RuntimeException("Could not get text from element!", e);
        }
    }

    public String getInputValue(WebElement element) {
        waitForVisibilityOfElement(element);
        return element.getAttribute("value");
    }

    public boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement element, long timeoutInSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(context.getDriver(), Duration.ofMillis(timeoutInSeconds));
            customWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //WAIT METHODS
    public void waitForVisibilityOfElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException s) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    //ASSERTION METHODS
    public void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public void assertEqualsText(WebElement element, String expected, String message) {
        String actualText = getText(element);
        Assert.assertEquals(actualText, expected, message);
    }

    public void assertContainsText(WebElement actualElement, String expected, String message) {
        String actualText = getText(actualElement);

        try {
            Assert.assertTrue(actualText.contains(expected), message);
        } catch (AssertionError e) {
            String failureMessage = String.format("%s%nActual: %s%nExpected: %s", message, actualText, expected);
            System.err.println(failureMessage);
            throw new AssertionError(failureMessage, e);
        }
    }

    public void assertVisibilityOfElement(WebElement element, String message) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible: " + message);
        } catch (Exception e) {
            String failureMessage = String.format("Element is not visible. %s", message);
            logger.error(failureMessage);
            throw new AssertionError(failureMessage, e);
        }
    }

    public String copyElement(WebElement element) {
        try {
            String text = element.getText();
            if (text != null && text.contains("Copy")) {
                return text;
            }
        } catch (Exception e) {
            logger.error("Failed to get text from the element.", e);
        }
        return null;
    }
}
