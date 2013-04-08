package fr.univnantes.atal.poubatal.entity;

import fr.univnantes.atal.poubatal.Constants;
import java.util.Objects;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class EmailNotification extends Notification {

    @Persistent
    private String email;

    private EmailNotification() {
    }

    public EmailNotification(String email) {
        this.id = Constants.EMAIL_NOTIFICATION + "-" + email;
        this.email = email;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            EmailNotification other = (EmailNotification) obj;
            return email.equals(other.email);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public void send(Address address, String binColor) {
        System.out.println("GOGO MAAAAIIIILLLLLLL");
    }
}
