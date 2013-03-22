package fr.univnantes.atal.poubatal.api;

/**
 *
 * @author Noemi
 */
public class APIError {

    public static final APIError SERVICE_NON_EXISTING = new APIError(0, "Service dosen't exist.");
    
    private int id;
    private String message;
    
    private APIError(int id, String message) {
        this.id = id;
        this.message = message;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
