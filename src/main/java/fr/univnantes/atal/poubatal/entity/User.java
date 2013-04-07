package fr.univnantes.atal.poubatal.entity;

import fr.univnantes.atal.poubatal.Oauth;
import fr.univnantes.atal.poubatal.datastore.PMF;
import fr.univnantes.atal.poubatal.opendata.CollectePoint;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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
    private Set<CollectePoint> addresses;
    @Persistent(defaultFetchGroup = "true")
    private Set<Notification> notifications;

    private User() {
    }

    public User(String id, String email) {
        this.id = id;
        addresses = new TreeSet<>();
        notifications = new TreeSet<>();
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
        User user = pm.getObjectById(User.class, id);
        if (user == null) {
            user = new User(id, email);
        }
        User detached = pm.detachCopy(user);
        return detached;
    }

    public Set<CollectePoint> getAddresses() {
        if (addresses == null) {
            addresses = new TreeSet<>();
        }
        return Collections.unmodifiableSet(addresses);
    }

    public void addAddress(CollectePoint address) {
        if (addresses == null) {
            addresses = new TreeSet<>();
        }
        addresses.add(address);
    }

    public void removeAddress(CollectePoint address) {
        if (addresses == null) {
            addresses = new TreeSet<>();
        }
        addresses.remove(address);
    }

    public Set<Notification> getNotifications() {
        if (notifications == null) {
            notifications = new TreeSet<>();
        }
        return Collections.unmodifiableSet(notifications);
    }

    public void addNotification(Notification notification) {
        if (notifications == null) {
            notifications = new TreeSet<>();
        }
        notifications.add(notification);
    }

    public void removeNotification(Notification notification) {
        if (notifications == null) {
            notifications = new TreeSet<>();
        }
        notifications.remove(notification);
    }

    public void deleteAccount() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        pm.deletePersistent(this);
    }
}
