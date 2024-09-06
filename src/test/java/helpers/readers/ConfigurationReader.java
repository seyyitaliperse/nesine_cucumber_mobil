package helpers.readers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final String CONFIG_PATH;
    private static Properties properties;

    public static String env;

    static {
        env = System.getProperty("CONFIG_PATH", "test");
        switch (env) {
            case "test":
                CONFIG_PATH = "config/test_config.properties";
                break;
            case "staging":
                CONFIG_PATH = "config/staging_config.properties";
                break;
            default:
                throw new RuntimeException("Invalid environment provided: " + env);
        }

        try (FileInputStream input = new FileInputStream(CONFIG_PATH)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_PATH, e);
        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }
}
