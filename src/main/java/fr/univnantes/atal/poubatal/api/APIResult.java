package fr.univnantes.atal.poubatal.api;

/**
 *
 * @author Noemi
 */
public class APIResult {

    public static final APIResult SUCCESS = new APIResult(0, "Success.");
    public static final APIResult SERVICE_NON_EXISTING = new APIResult(1, "Service dosen't exist.");
    public static final APIResult WRONG_PARAMETERS = new APIResult(2, "One or more parameters are wrong.");
    public static final APIResult JSON_PROCESSING_EXCEPTION = new APIResult(3, "Json processing exception.");
    
    private int id;
    private String message;
    private String detail;
    
    private APIResult(int id, String message) {
        this.id = id;
        this.message = message;
        this.detail = "";
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

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
