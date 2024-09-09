package steps;

import helpers.container.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.factory.PageFactoryManager;
import pages.factory.Utils;
import pages.webpages.HesabimPage;

public class HesabimSteps {
    private final Context context;
    private final Utils utils;
    private final HesabimPage hesabimPage;

    public HesabimSteps(Context context){
        this.context = context;
        this.utils = PageFactoryManager.getUtils(context);
        this.hesabimPage = PageFactoryManager.getHesabimPage(context);
    }

    @And("user log out from system")
    public void userLogOutFromSystem() {
        hesabimPage.logout();
    }


    @When("user navigates to bilgilerim page")
    public void userNavigatesToBilgilerimPage() {
        hesabimPage.goToBilgilerim();
    }


    @Given("user navigates to promotion page")
    public void userNavigatesToPromotionPage() {
        hesabimPage.goToPromosyonlarim();
    }

    @Then("user verifies that return hesabim page after close promotion")
    public void userVerifiesThatReturnHesabimPageAfterClosePromotion() {
        hesabimPage.backToHesabimMenu();
        utils.assertEqualsText(hesabimPage.headerValue,"HESABIM","Could not back to hesabim page..");
    }
}
