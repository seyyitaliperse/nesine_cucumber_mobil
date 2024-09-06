package helpers.container;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import helpers.factory.DriverFactory;
import pages.webpages.LoginPage;

public class ContainerSetup {

    private static MutablePicoContainer container;

    public static void setup() {
        container = new DefaultPicoContainer();
        container.addComponent(DriverFactory.class);
        container.addComponent(LoginPage.class);
    }

    public static <T> T getComponent(Class<T> type) {
        return container.getComponent(type);
    }
}
