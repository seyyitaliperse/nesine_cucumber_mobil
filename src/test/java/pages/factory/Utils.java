package pages.factory;

import helpers.container.Context;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Utils extends BasePage {
    public Utils(Context context) {
        super(context);
        PageFactory.initElements(context.getDriver(), this);
    }

    //This class usage for only prefix
}
