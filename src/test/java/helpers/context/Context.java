package helpers.context;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private static ThreadLocal<Map<String, Object>> contextData = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        contextData.get().put(key, value);
    }

    public static Object get(String key) {
        return contextData.get().get(key);
    }

    public static <T> T get(String key, Class<T> type) {
        return type.cast(contextData.get().get(key));
    }

    public static void clear() {
        contextData.get().clear();
    }
}
