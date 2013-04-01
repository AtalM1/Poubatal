package fr.univnantes.atal.poubatal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Noemi
 */
public class APIResponse {

    private Map map;

    public APIResponse() {
        map = new HashMap();
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();   // Création du mapper json
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            APIResult result = APIResult.jsonException();
            return "{\"result\":{\"id\":" + result.getId() + ",\"message\":\"" + result.getMessage() + "\",\"detail\":\"" + ex.getMessage() + "\"}}";
        }
    }
    
    public void setResult(APIResult result) {
        map.put("result", result);
    }
    
    public void setData(Object data) {
        map.put("data", data);
    }
}
