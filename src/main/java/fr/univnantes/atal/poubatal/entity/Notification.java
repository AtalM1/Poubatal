package fr.univnantes.atal.poubatal.entity;

import com.google.appengine.api.datastore.Key;
import fr.univnantes.atal.poubatal.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Notification {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key; // Unused key
    @Persistent
    private String id;
    @Persistent
    private String type;
    @Persistent
    private Map<String, String> properties;

    private Notification() {
    }

    public Notification(String type, Map<String, String> properties) {
        switch (type) {
            case Constants.EMAIL_NOTIFICATION:
                this.type = type;
                this.properties = properties;
                this.id = type + "-" + properties.get("email");
                break;
            default:
                this.type = Constants.ERROR_NOTIFICATION;
                break;
        }


    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the properties
     */
    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
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
            Notification other = (Notification) obj;
            return getId().equals(other.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    public void send(Address address, String binColor) {
        System.out.println("SEND NOTIFICATION: " + type);
        switch (type) {
            case Constants.EMAIL_NOTIFICATION:
                System.out.println("GOGO MAAAAIIIILLLLLLL");
                break;
        }
    }
}
