package pages.webpages;

import helpers.container.Context;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HesabimPage extends DashboardPage {

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

    public void logout(){
        logger.info("Logging out..");
        openHesabimMenu();
        tap(cikisButton);
        tap(cikisDevamButton);
    }

    public void goToPromosyonlarim(){
        logger.info("Opening Promosyonlarim Page..");
        openHesabimMenu();
        tap(promosyonlarimButton);
    }

    public void goToBilgilerim(){
        logger.info("Opening Kisisel Bilgilerim Page..");
        openHesabimMenu();
        tap(bilgilerimButton);
    }

    public void backToHesabimMenu(){
        logger.info("Going back to hesabim menu");
        tap(backToHesabimMenuButton);
    }
}
