package hooks;

import helpers.container.Context;
import helpers.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MainHooks {

    private Context context;

    public MainHooks(Context context){
        this.context = context;
    }

    @Before(order = 1)
    public void setUp() {
        DriverFactory.initializeDriver();
        context.setDriver(DriverFactory.getDriver());
        context.setWebDriverWait(DriverFactory.getWebDriverWait());
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) context.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        DriverFactory.quitDriver();
    }
}
