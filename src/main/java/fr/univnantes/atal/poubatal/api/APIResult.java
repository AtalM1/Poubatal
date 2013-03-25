package fr.univnantes.atal.poubatal.api;

/**
 *
 * @author Noemi
 */
public class APIResult {

    public static final APIResult SUCCESS = new APIResult(0, "Success.");
    public static final APIResult SERVICE_NON_EXISTING = new APIResult(1, "Service dosen't exist.");
    public static final APIResult WRONG_PARAMETERS = new APIResult(2, "One or more parameters are wrong");
    
    private int id;
    private String message;
    
    private APIResult(int id, String message) {
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
