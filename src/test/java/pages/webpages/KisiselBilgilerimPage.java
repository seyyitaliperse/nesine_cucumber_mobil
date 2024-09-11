package pages.webpages;

import helpers.container.Context;
import helpers.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class KisiselBilgilerimPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(KisiselBilgilerimPage.class);

    public KisiselBilgilerimPage(Context context) {
        super(context);
        PageFactory.initElements(context.getDriver(), this);
    }

    @FindBy(xpath = "//android.view.View[@resource-id=\"nsn-myInfoAndSettings\"]/android.view.View[2]/android.view.View[2]/android.widget.Button")
    private WebElement uyeNumarasiCopyButton;

    public String getUyeNumarasi() {
        try {
            logger.info("Copying uye numarasi..");
            return copyElement(uyeNumarasiCopyButton);
        } catch (Exception e) {
            logger.error("Failed to copy uye numarasi: " + e.getMessage());
            throw new RuntimeException("Error occurred while copying uye numarasi", e);
        }
    }




}
