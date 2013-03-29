package fr.univnantes.atal.poubatal.api;

/**
 *
 * @author Noemi
 */
public class APIResult {

    private int id;
    private String message;
    private String detail;
    
    private APIResult(int id, String message) {
        this.id = id;
        this.message = message;
        this.detail = "";
    }
    
    public static APIResult success() {
        return new APIResult(0, "Success.");
    }
    
    public static APIResult nonExistentService() {
        return new APIResult(1, "Service dosen't exist.");
    }
    
    public static APIResult wrongParameters() {
        return new APIResult(2, "One or more parameters are wrong.");
    }
    
    public static APIResult jsonException() {
        return new APIResult(3, "Json processing exception.");
    }
    
    public static APIResult authenticationRequired() {
        return new APIResult(4, "Authentication required.");
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
