package pages.factory;

import helpers.container.Context;
import helpers.readers.ConfigurationReader;
import helpers.utils.BrowserUtils;

public abstract class BasePage extends BrowserUtils {

    protected String baseUrl;

    public BasePage(Context context) {
        super(context);
        this.baseUrl = ConfigurationReader.get("baseUrl");
    }

}
