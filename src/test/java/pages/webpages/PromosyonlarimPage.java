package pages.webpages;

import helpers.container.Context;
import helpers.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class PromosyonlarimPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(PromosyonlarimPage.class);

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

    public void fillKodGirisiComponents(String promotionCode, String securityCode) {
        try {
            logger.info("Filling kod girisi page component as promotion code " + promotionCode + " and as security code: " + securityCode);
            enterText(promosyonKodunuzInput, promotionCode);
            enterText(guvenlikKodunuzInput, securityCode);
        } catch (Exception e) {
            logger.error("Failed to fill kod girisi components: " + e.getMessage());
            throw new RuntimeException("Error occurred while filling kod girisi components", e);
        }
    }

}
