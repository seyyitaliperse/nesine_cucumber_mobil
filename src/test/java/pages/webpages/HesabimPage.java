package pages.webpages;

import helpers.container.Context;
import helpers.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HesabimPage extends DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(HesabimPage.class);
    public HesabimPage(Context context) {
        super(context);
        PageFactory.initElements(context.getDriver(), this);
    }

    @FindBy(id = "com.pordiva.nesine.android:id/title")
    public WebElement headerValue;
    @FindBy(id = "com.pordiva.nesine.android:id/logout_btn")
    private WebElement cikisButton;
    @FindBy(id = "android:id/button1")
    private WebElement cikisDevamButton;

    @FindBy(id = "com.pordiva.nesine.android:id/my_promotions_btn")
    private WebElement promosyonlarimButton;
    @FindBy(id = "com.pordiva.nesine.android:id/personal_info_btn")
    private WebElement bilgilerimButton;
    @FindBy(id = "com.pordiva.nesine.android:id/back")
    private WebElement backToHesabimMenuButton;

    public void logout() {
        try {
            logger.info("Logging out..");
            openHesabimMenu();
            tap(cikisButton);
            tap(cikisDevamButton);
        } catch (Exception e) {
            logger.error("Logout attempt failed: " + e.getMessage());
            throw new RuntimeException("Failed to log out", e);
        }
    }

    public void goToPromosyonlarim() {
        try {
            logger.info("Opening Promosyonlarim Page..");
            openHesabimMenu();
            tap(promosyonlarimButton);
        } catch (Exception e) {
            logger.error("Failed to open Promosyonlarim page: " + e.getMessage());
            throw new RuntimeException("Failed to navigate to Promosyonlarim page", e);
        }
    }

    public void goToBilgilerim() {
        try {
            logger.info("Opening Kisisel Bilgilerim Page..");
            openHesabimMenu();
            tap(bilgilerimButton);
        } catch (Exception e) {
            logger.error("Failed to open Kisisel Bilgilerim page: " + e.getMessage());
            throw new RuntimeException("Failed to navigate to Kisisel Bilgilerim page", e);
        }
    }

    public void backToHesabimMenu() {
        try {
            logger.info("Going back to hesabim menu");
            tap(backToHesabimMenuButton);
        } catch (Exception e) {
            logger.error("Failed to go back to hesabim menu: " + e.getMessage());
            throw new RuntimeException("Failed to navigate back to Hesabim menu", e);
        }
    }
}
