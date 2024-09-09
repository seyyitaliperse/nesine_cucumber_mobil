package pages.webpages;

import helpers.container.Context;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.factory.BasePage;

public class KisiselBilgilerimPage extends BasePage {
    public KisiselBilgilerimPage(Context context) {
        super(context);
        PageFactory.initElements(context.getDriver(), this);
    }

    @FindBy(xpath = "//android.view.View[@resource-id=\"nsn-myInfoAndSettings\"]/android.view.View[2]/android.view.View[2]/android.widget.Button")
    private WebElement uyeNumarasiCopyButton;

    public String getUyeNumarasi(){
        logger.info("Copied uye numarasi..");
        return copyElement(uyeNumarasiCopyButton);
    }




}
