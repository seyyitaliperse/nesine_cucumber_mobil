package pages.factory;

import helpers.container.Context;
import pages.webpages.*;

public class PageFactoryManager {
    private static Utils utils;
    private static UyelikGirisiPage uyelikGirisiPage;
    private static DashboardPage dashboardPage;
    private static PromosyonlarimPage promosyonlarimPage;
    private static KisiselBilgilerimPage kisiselBilgilerimPage;
    private static HesabimPage hesabimPage;


    public static UyelikGirisiPage getUyelikGirisiPage(Context context) {
        return uyelikGirisiPage == null ? new UyelikGirisiPage(context) : uyelikGirisiPage;
    }

    public static Utils getUtils(Context context) {
        return utils == null ? new Utils(context) : utils;
    }

    public static DashboardPage getDashboardPage(Context context) {
        return dashboardPage == null ? new DashboardPage(context) : dashboardPage;
    }

    public static PromosyonlarimPage getPromosyonlarimPage(Context context) {
        return promosyonlarimPage == null ? new PromosyonlarimPage(context) : promosyonlarimPage;
    }

    public static KisiselBilgilerimPage getKisiselBilgilerimPage(Context context) {
        return kisiselBilgilerimPage == null ? new KisiselBilgilerimPage(context) : kisiselBilgilerimPage;
    }

    public static HesabimPage getHesabimPage(Context context) {
        return hesabimPage == null ? new HesabimPage(context) : hesabimPage;
    }
}
