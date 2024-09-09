package pages.webpages;

import helpers.container.Context;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class UyelikGirisiPage extends BasePage {

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

    public void login(String userName, String password){
        enterText(userNameInput, userName);
        enterText(passwordInput, password);
        tap(loginButton);
        waitForVisibilityOfElement(balanceEvidence);
    }

    public void loginNegative(String userName, String password){
        enterText(userNameInput, userName);
        enterText(passwordInput, password);
        tap(loginButton);
    }









}