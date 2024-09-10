package steps;

import helpers.container.Context;
import io.cucumber.java.en.When;
import pages.factory.PageFactoryManager;
import pages.factory.Utils;
import pages.webpages.KisiselBilgilerimPage;

public class PersonalInformationSteps {
    private final Context context;
    private final Utils utils;
    private final KisiselBilgilerimPage kisiselBilgilerimPage;

    public PersonalInformationSteps(Context context){
        this.context = context;
        this.utils = PageFactoryManager.getUtils(context);
        this.kisiselBilgilerimPage = PageFactoryManager.getKisiselBilgilerimPage(context);
    }


    @When("user verifies that member id is display properly")
    public void userVerifiesThatMemberIdIsDisplayProperly() {
        String uyeNumarasi = context.getGlobalData().getData("uyeNumarasi");
        utils.assertEquals(kisiselBilgilerimPage.getUyeNumarasi(), uyeNumarasi, "There is problem with Uye Numarasi..");
    }


}
