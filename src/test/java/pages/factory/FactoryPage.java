package pages.factory;

import helpers.readers.ConfigurationReader;
import helpers.utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class FactoryPage extends BrowserUtils {

    protected String baseUrl;

    public FactoryPage(WebDriver driver) {
        super(driver);
        this.baseUrl = ConfigurationReader.get("baseUrl");
    }

    public void navigateTo(String path, WebElement element) {
        String fullUrl = baseUrl + path;
        driver.get(fullUrl);
        waitForVisibilityOfElement(element);
    }

}
