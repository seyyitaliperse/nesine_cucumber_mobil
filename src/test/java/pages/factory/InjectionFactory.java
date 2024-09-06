package pages.factory;

import org.openqa.selenium.WebDriver;
import helpers.utils.BrowserUtils;
import pages.webpages.LoginPage;

public class InjectionFactory {

    private static ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();
    private static ThreadLocal<BrowserUtils> browserUtils = new ThreadLocal<>();

    public static LoginPage getLoginPage(WebDriver driver) {
        if (loginPage.get() == null) {
            loginPage.set(new LoginPage(driver));
        }
        return loginPage.get();
    }

    public static BrowserUtils getBrowserUtils(WebDriver driver) {
        if (browserUtils.get() == null) {
            browserUtils.set(new BrowserUtils(driver));
        }
        return browserUtils.get();
    }
}