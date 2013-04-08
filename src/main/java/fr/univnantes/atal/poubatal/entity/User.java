package fr.univnantes.atal.poubatal.entity;

import fr.univnantes.atal.poubatal.tools.Oauth;
import fr.univnantes.atal.poubatal.datastore.PMF;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String id;
    @Persistent(defaultFetchGroup = "true")
    private Set<Address> addresses;
    @Persistent(defaultFetchGroup = "true")
    private Set<Notification> notifications;

    private User() {
    }

    public User(String id, String email) {
        this.id = id;
        addresses = new HashSet<>();
        notifications = new HashSet<>();
        notifications.add(new Notification(email));
    }

    // Make the user persistent
    public void save() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(this);
        } finally {
            pm.close();
        }
    }

    public static User load(String accessToken) {
        // Récupération du GoogleUser avec l'accessToken
        Map<String, String> userData = Oauth.getGoogleUser(accessToken);
        if (userData == null) {
            return null;
        }
        String id = userData.get("user_id");
        String email = userData.get("email");

        // Récupération du User avec l'ID Google
        PersistenceManager pm = PMF.get().getPersistenceManager();
        User detached;
        try {
            User user = pm.getObjectById(User.class, id);
            detached = pm.detachCopy(user);
        } catch (JDOObjectNotFoundException e) {
            detached = new User(id, email);
        }        
        return detached;
    }

    public Set<Address> getAddresses() {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        return Collections.unmodifiableSet(addresses);
    }

    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        addresses.remove(address);
    }

    public Set<Notification> getNotifications() {
        if (notifications == null) {
            notifications = new HashSet<>();
        }
        return Collections.unmodifiableSet(notifications);
    }

    public void addNotification(Notification notification) {
        if (notifications == null) {
            notifications = new HashSet<>();
        }
        notifications.add(notification);
    }

    public void removeNotification(Notification notification) {
        if (notifications == null) {
            notifications = new HashSet<>();
        }
        notifications.remove(notification);
    }

    public void deleteAccount() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        pm.makePersistent(this);
        pm.deletePersistent(this);
    }
}
