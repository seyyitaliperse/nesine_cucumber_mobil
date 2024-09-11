package pages.webpages;

import helpers.container.Context;
import helpers.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class UyelikGirisiPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(UyelikGirisiPage.class);

    public UyelikGirisiPage(Context context) {
        super(context);
        PageFactory.initElements(context.getDriver(), this);
    }

    //Evidence
    @FindBy(id = "com.pordiva.nesine.android:id/tvBalanceValue")
    public WebElement balanceEvidence;

    //Inputs
    @FindBy(id = "com.pordiva.nesine.android:id/username_edit")
    private WebElement userNameInput;
    @FindBy(xpath = "//android.widget.EditText[@resource-id='com.pordiva.nesine.android:id/password_edit']")
    private WebElement passwordInput;
    @FindBy(id = "com.pordiva.nesine.android:id/login_btn")
    private WebElement loginButton;

    //Dialog
    @FindBy(id = "android:id/message")
    public WebElement invalidCredentialsDialogDetails;

    public void login(String userName, String password) {
        try {
            logger.info("Logging in Nesine application..");
            enterText(userNameInput, userName);
            enterText(passwordInput, password);
            tap(loginButton);
            waitForVisibilityOfElement(balanceEvidence);
        } catch (Exception e) {
            logger.error("Login attempt failed: " + e.getMessage());
            throw new RuntimeException("Failed to log in with username: " + userName, e);
        }
    }

    public void loginNegative(String userName, String password) {
        try {
            logger.info("Trying to log in Nesine Application with negative credentials..");
            enterText(userNameInput, userName);
            enterText(passwordInput, password);
            tap(loginButton);
        } catch (Exception e) {
            logger.error("Negative login attempt failed: " + e.getMessage());
            throw new RuntimeException("Failed to log in with username: " + userName, e);
        }
    }


}