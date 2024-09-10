package hooks;

import helpers.container.Context;
import helpers.factory.DriverFactory;
import helpers.logger.LoggerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class MainHooks {

    private static final Logger logger = LoggerFactory.getLogger(MainHooks.class);
    private Context context;
    private WebDriver driver;

    public MainHooks(Context context){
        this.context = context;
    }

    @Before(order = 1)
    public void setUp() {
        DriverFactory.initializeDriver();
        driver = DriverFactory.getDriver();
        context.setDriver(driver);
        context.setWebDriverWait(DriverFactory.getWebDriverWait());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (driver != null) {
                driver.quit();
            }
        }));
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.getStatus().name().equals("PASSED")){
            successLog(scenario);
        }

        if (scenario.getStatus().name().equals("FAILED")){
            failedLog(scenario);
            captureScreenShoot(scenario);
        }
        if (driver != null){
            driver.quit();
        }
    }

    public void captureScreenShoot(Scenario scenario){
        final byte[] screenshoot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshoot, "image/png", "screenshot");
    }

    public void successLog(Scenario scenario){
        logger.info("Passed! -> Scenario Name: " + scenario.getName());
    }

    public void failedLog(Scenario scenario){
        logger.info("Failed! -> Scenario Name: " + scenario.getName());
    }
}
