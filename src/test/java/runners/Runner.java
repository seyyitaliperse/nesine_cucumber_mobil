package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import helpers.factory.DriverFactory;

@CucumberOptions(
        features = "src/test/resources/scenarios",
        glue = {"steps", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        dryRun = false,
        tags = "@smoke"
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @org.testng.annotations.DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
