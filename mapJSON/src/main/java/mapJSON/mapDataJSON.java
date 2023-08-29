package mapJSON;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class mapDataJSON {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            File jsonFile = new File("D:\\IT Vedant\\Ysquare_Task\\input.json"); 
            TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};
            
            Map<String, Object> jsonMap = objectMapper.readValue(jsonFile, typeReference);
            
            // Print the loaded JSON map
            printMap(jsonMap, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printMap(Map<?, ?> map, String parentKey) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
        	String key= parentKey.isEmpty() ? entry.getKey().toString() : parentKey + "." + entry.getKey();
                      
            if (entry.getValue() instanceof Map) 
            {
            	if(!parentKey.isEmpty())
            	{
                   printMap((Map<?, ?>) entry.getValue(), key);
            	}else {
            	printMap((Map<?, ?>) entry.getValue(), "");
            }
            	}else {
                System.out.println(key+" : "+entry.getValue());
            }
        }
    }
}
