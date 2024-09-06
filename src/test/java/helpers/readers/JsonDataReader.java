package helpers.readers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonDataReader {

    private static final String userCredentials = ConfigurationReader.get("userCredentials");

    public static String getUserCredential(String key) {
        return getJsonValue(userCredentials, key);
    }

    public static String getUserCredential(String member, String key) {
        return getJsonValue(userCredentials, member, key);
    }

    public static String getUserCredential(String member, String key, String anotherKey) {
        return getJsonValue(userCredentials, member, key, anotherKey);
    }

    //FACTORY
    private static JsonObject getJsonObject(String filePath) {
        try {
            return JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath, e);
        }
    }

    private static String getJsonValue(String filePath, String key) {
        JsonObject jsonObject = getJsonObject(filePath);
        return jsonObject.get(key).getAsString();
    }

    private static String getJsonValue(String filePath, String member, String key) {
        JsonObject jsonObject = getJsonObject(filePath);
        return jsonObject.getAsJsonObject(member).get(key).getAsString();
    }

    private static String getJsonValue(String filePath, String member, String key, String anotherKey) {
        JsonObject jsonObject = getJsonObject(filePath);
        return jsonObject.getAsJsonObject(member).getAsJsonObject(key).get(anotherKey).getAsString();
    }
}
