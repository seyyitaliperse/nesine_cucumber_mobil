package hooks;

import helpers.container.Context;
import helpers.readers.JsonDataReader;
import io.cucumber.java.Before;
import pages.factory.PageFactoryManager;
import pages.webpages.DashboardPage;
import pages.webpages.UyelikGirisiPage;

public class LoginHooks {
    private final UyelikGirisiPage uyelikGirisiPage;
    private final DashboardPage dashboardPage;
    private Context context;

    public LoginHooks(Context context){
        this.context = context;
        this.uyelikGirisiPage = PageFactoryManager.getUyelikGirisiPage(context);
        this.dashboardPage = PageFactoryManager.getDashboardPage(context);
    }

    @Before(value = "@_userLogin", order = 10)
    public void userLogin() {
        dashboardPage.acceptCookie();
        dashboardPage.openUyelikGirisiPage();

        String email = JsonDataReader.getUserCredential("user", "tckn");
        String password = JsonDataReader.getUserCredential("user", "password");
        uyelikGirisiPage.login(email, password);
    }


}
