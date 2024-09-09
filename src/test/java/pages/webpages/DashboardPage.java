package pages.webpages;

import helpers.container.Context;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class DashboardPage extends BasePage {

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


    public void acceptCookie(){
        logger.info("Accepting cookie..");
        tap(cookiesAcceptButton);
    }

    public void openHesabimMenu(){
        logger.info("Opening Hesabim Menu..");
        tap(hesabimMenu);
        if (isElementVisible(dialogAlertClose,2000)){
            tap(dialogAlertClose);
        }
    }

    public void openUyelikGirisiPage(){
        logger.info("Navigating to Uyelik Girisi Page..");
        tap(uyelikGirisiMenuButton);
    }
}
