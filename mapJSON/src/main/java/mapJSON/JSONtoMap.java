package mapJSON;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//public class JSONToMap {
//    public static void main(String[] args) {
//        try {
//            // Read the JSON content from the file
//            String jsonContent = new String(Files.readAllBytes(Paths.get("data.json")));
//
//            // Parse the JSON content into a JSONObject
//            JSONObject jsonObject = new JSONObject(jsonContent);
//
//            // Convert nested JSON contents into a simple map data structure
//            Map<String, Object> resultMap = jsonToMap(jsonObject);
//
//            // Print the result
//            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue());
//            }
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
//        Map<String, Object> map = new HashMap<>();
//        Iterator<String> keys = json.keys();
//
//        while (keys.hasNext()) {
//            String key = keys.next();
//            Object value = json.get(key);
//
//            if (value instanceof JSONObject) {
//                value = jsonToMap((JSONObject) value);
//            }
//
//            map.put(key, value);
//        }
//        return map;
//    }
//}



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

public class JSONtoMap {
    public static void main(String[] args) {
        try {
            // Read the JSON content from the file
            String jsonContent = new String(Files.readAllBytes(Paths.get("D:\\IT Vedant\\Ysquare_Task\\input.json")));

            // Parse the JSON content into a JsonNode using Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonContent);

            // Convert JSON node into a simple map data structure
            Map<String, Object> resultMap = jsonToMap(jsonNode,"");

            // Print the result
            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> jsonToMap(JsonNode jsonNode, String parentKey) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(jsonNode, Map.class);
        
        if (parentKey.isEmpty()) {
            map.remove(parentKey); // Remove the parent key if it's not empty
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof JsonNode) {
                map.putAll(jsonToMap((JsonNode) entry.getValue(), entry.getKey()));
            }
        }
        return map;
    }
}
