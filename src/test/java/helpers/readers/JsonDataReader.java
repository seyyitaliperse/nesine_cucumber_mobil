package helpers.readers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JsonDataReader {

    private static final String userCredentials = ConfigurationReader.get("userCredentials");
    private static final String commonData = ConfigurationReader.get("commonDataPath");

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

    public static String getFeatureData(String fileName, String member, String key) {
        JsonObject jsonObject = getJsonObject(commonData + fileName + ".json");

        JsonObject nestedObject = jsonObject.getAsJsonObject(member);
        if (nestedObject == null) {
            throw new IllegalStateException("No options available for member: " + member);
        }

        JsonElement jsonElement = nestedObject.get(key);

        if (jsonElement == null) {
            throw new IllegalStateException("No data available for key: " + key);
        }

        return getRandomValueFromJsonElement(jsonElement, key);
    }

    private static String getRandomValueFromJsonElement(JsonElement jsonElement, String key) {
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray.size() == 0) {
                throw new IllegalStateException("No options available for key: " + key);
            }
            List<String> options = getJsonArrayAsList(jsonArray);
            int randomIndex = new Random().nextInt(options.size());
            return options.get(randomIndex);
        } else if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            return jsonPrimitive.getAsString();
        } else {
            throw new IllegalStateException("Unsupported data type for key: " + key);
        }
    }

    private static List<String> getJsonArrayAsList(JsonArray jsonArray) {
        List<String> options = new ArrayList<>();
        jsonArray.forEach(option -> options.add(option.getAsString()));
        return options;
    }
}
