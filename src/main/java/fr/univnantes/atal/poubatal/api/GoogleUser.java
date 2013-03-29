package fr.univnantes.atal.poubatal.api;

public class GoogleUser {
    private String name;
    private String email;
    private String id;
    private boolean verifiedEmail;
    
    public GoogleUser() {
        name = "";
        email = "";
        id = "";
        verifiedEmail = false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the verifiedEmail
     */
    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    /**
     * @param verifiedEmail the verifiedEmail to set
     */
    public void setVerifiedEmail(boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }
    
}
