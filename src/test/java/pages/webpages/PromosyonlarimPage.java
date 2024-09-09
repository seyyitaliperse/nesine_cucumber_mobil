package pages.webpages;

import helpers.container.Context;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class PromosyonlarimPage extends BasePage {
    public PromosyonlarimPage(Context context) {
        super(context);
        PageFactory.initElements(context.getDriver(), this);
    }

    @FindBy(xpath = "//android.widget.EditText[@resource-id='code']")
    public WebElement promosyonKodunuzInput;
    @FindBy(xpath = "//android.widget.EditText[@resource-id='securityCode']")
    private WebElement guvenlikKodunuzInput;
    @FindBy(id = "CaptchaImage")
    private WebElement captchaImage;

    public void fillKodGirisiComponents(String promotionCode, String securityCode){
        enterText(promosyonKodunuzInput, promotionCode);
        enterText(guvenlikKodunuzInput, securityCode);
    }



}
