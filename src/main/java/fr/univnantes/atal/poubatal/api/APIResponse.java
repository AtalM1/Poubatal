/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public APIResponse(Map map) {
        this.map = map;
    }
    
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();   // Création du mapper json
        try {
            return mapper.writeValueAsString(getMap());
        } catch (JsonProcessingException ex) {
            return "erreur : " + ex.getMessage();
        }
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }
    
}
