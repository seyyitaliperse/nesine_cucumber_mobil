package steps;

import helpers.container.Context;
import helpers.readers.JsonDataReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.factory.PageFactoryManager;
import pages.factory.Utils;
import pages.webpages.UyelikGirisiPage;

public class LoginSteps {
    private final Context context;
    private final UyelikGirisiPage uyelikGirisiPage;
    private final Utils utils;
    private final String fileName = "login";

    public LoginSteps(Context context){
        this.context = context;
        this.uyelikGirisiPage = PageFactoryManager.getUyelikGirisiPage(context);
        this.utils = PageFactoryManager.getUtils(context);
    }



    @When("user log in nesine dashboard")
    public void userLogInNesineDashboard() {
        String tckn = JsonDataReader.getUserCredential("user","tckn");
        String password = JsonDataReader.getUserCredential("user","password");
        uyelikGirisiPage.login(tckn, password);
    }

    @Then("user verifies that logged in successfully")
    public void userVerifiesThatLoggedInSuccessfully() {
        utils.assertVisibilityOfElement(uyelikGirisiPage.balanceEvidence,"Could not login system!");
    }

    @When("user attempts to log in with invalid credentials")
    public void userAttemptsToLogInWithInvalidCredentials() {
        String tckn = JsonDataReader.getUserCredential("user","tckn");
        String password = "axasdjasjdj";
        uyelikGirisiPage.loginNegative(tckn, password);
    }

    @And("user should see an invalid credential dialog error details")
    public void userShouldSeeAnInvalidCredentialDialogErrorDetails() {
        String dialogDetail = JsonDataReader.getFeatureData(fileName, "invalidCredentials", "detail");

        utils.assertContainsText(uyelikGirisiPage.invalidCredentialsDialogDetails, dialogDetail, "Invalid credentials first dialog details is not as expected..");
    }


}
