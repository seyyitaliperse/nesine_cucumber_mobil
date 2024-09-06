package steps;

import helpers.factory.DriverFactory;
import pages.factory.InjectionFactory;
import pages.webpages.LoginPage;

public class LoginSteps {
    private final LoginPage loginPage;

    public LoginSteps(){
        this.loginPage = InjectionFactory.getLoginPage(DriverFactory.getDriver());
    }


}
