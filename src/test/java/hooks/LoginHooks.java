package hooks;

import helpers.factory.DriverFactory;
import pages.factory.InjectionFactory;
import pages.webpages.LoginPage;

public class LoginHooks {
    private final LoginPage loginPage;

    public LoginHooks(){
        this.loginPage = InjectionFactory.getLoginPage(DriverFactory.getDriver());
    }


}
