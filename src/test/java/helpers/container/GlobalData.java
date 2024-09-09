package helpers.container;

import java.util.HashMap;
import java.util.Map;

public class GlobalData {
    Map<String, String> stepData;

    public GlobalData(){
        stepData = new HashMap<>();
    }

    public void setData(String key, String value){
        stepData.put(key, value);
    }

    public String getData(String key){
        return stepData.get(key);
    }
}
