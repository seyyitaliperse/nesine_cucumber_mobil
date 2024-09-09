package steps;

import helpers.container.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.factory.PageFactoryManager;
import pages.factory.Utils;
import pages.webpages.DashboardPage;
import pages.webpages.UyelikGirisiPage;

public class DashboardSteps {
    private final Context context;
    private final Utils utils;
    private final UyelikGirisiPage uyelikGirisiPage;
    private final DashboardPage dashboardPage;

    public DashboardSteps(Context context){
        this.context = context;
        this.uyelikGirisiPage = PageFactoryManager.getUyelikGirisiPage(context);
        this.utils = PageFactoryManager.getUtils(context);
        this.dashboardPage = PageFactoryManager.getDashboardPage(context);
    }

    @Then("user verifies that logged out successfully")
    public void userVerifiesThatLoggedOutSuccessfully() {
        utils.assertVisibilityOfElement(dashboardPage.uyelikGirisiMenuButton,"Could not logout from system!");
    }

    @Given("user get member id from dashboard")
    public void userGetMemberIdFromDashboard() {
        String uyeNumarasi = utils.getText(dashboardPage.uyeNoValue);
        context.getGlobalData().setData("uyeNumarasi", uyeNumarasi);
    }

    @Then("user navigates to nesine login page")
    public void userNavigatesToNesineLoginPage() {
        dashboardPage.acceptCookie();
        dashboardPage.openUyelikGirisiPage();
    }


}
