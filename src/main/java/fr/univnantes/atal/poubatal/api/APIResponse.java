/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Noemi
 */
public class APIResponse {
    
    private APIError error;
    private APIData data;
    
    public APIResponse() {
        this.error = new APIError(APIError.SUCCESS);
    }
    
    public APIResponse(APIError error, APIData data) {
        this.error = error;
        this.data = data;
    }

    /**
     * @return the error
     */
    public APIError getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(APIError error) {
        this.error = error;
    }

    /**
     * @return the data
     */
    public APIData getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(APIData data) {
        this.data = data;
    }
    
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();   // Création du mapper json
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return "erreur : " + ex.getMessage();
        }
    }
    
}
