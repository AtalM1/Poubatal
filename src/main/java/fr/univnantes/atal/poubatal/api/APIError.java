/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.api;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Noemi
 */
public class APIError {

    public static final int SUCCESS = 0;
    public static final int SERVICE_NON_EXISTING = 1;
    private int id;
    private Map<Integer, String> messages;

    public APIError() {
        this(SUCCESS);
    }
    
    public APIError(int id) {
        this.id = id;
        messages = new HashMap();
        messages.put(SUCCESS, "succès");
        messages.put(SERVICE_NON_EXISTING, "le service demandé n'existe pas");
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        String ret = messages.get(id);
        if (ret != null) {
            return ret;
        } else {
            return "erreur inconnue";
        }
    }
}
