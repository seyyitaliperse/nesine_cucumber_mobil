package pages.webpages;

import helpers.container.Context;
import helpers.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class DashboardPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    public DashboardPage(Context context) {
        super(context);
        PageFactory.initElements(context.getDriver(), this);
    }

    //Header elements
    @FindBy(id = "com.pordiva.nesine.android:id/tvMemberNoValue")
    public WebElement uyeNoValue;

    //Cookies
    @FindBy(id = "com.pordiva.nesine.android:id/btnAccept")
    private WebElement cookiesAcceptButton;
    @FindBy(id = "com.pordiva.nesine.android:id/btnLogin")
    public WebElement uyelikGirisiMenuButton;

    //Hesabim Elements
    @FindBy(id = "com.pordiva.nesine.android:id/ivMember")
    private WebElement hesabimMenu;
    @FindBy(id = "com.pordiva.nesine.android:id/tvClose")
    private WebElement dialogAlertClose;


    public void acceptCookie() {
        try {
            logger.info("Accepting cookie..");
            tap(cookiesAcceptButton);
        } catch (Exception e) {
            logger.error("Failed to accept cookie: " + e.getMessage());
            throw new RuntimeException("Error occurred while accepting cookies", e);
        }
    }

    public void openHesabimMenu() {
        try {
            logger.info("Opening Hesabim Menu..");
            tap(hesabimMenu);
            if (isElementVisible(dialogAlertClose, 2000)) {
                tap(dialogAlertClose);
            }
        } catch (Exception e) {
            logger.error("Failed to open Hesabim Menu: " + e.getMessage());
            throw new RuntimeException("Error occurred while opening Hesabim Menu", e);
        }
    }

    public void openUyelikGirisiPage() {
        try {
            logger.info("Opening to Uyelik Girisi Page..");
            tap(uyelikGirisiMenuButton);
        } catch (Exception e) {
            logger.error("Failed to open Uyelik Girisi Page: " + e.getMessage());
            throw new RuntimeException("Error occurred while opening Uyelik Girisi Page", e);
        }
    }

}
