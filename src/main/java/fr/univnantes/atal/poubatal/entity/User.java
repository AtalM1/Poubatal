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

    public static User load(String oauth) {
        // Récupération du GoogleUser avec l'accessToken
        Map<String, String> userData = Oauth.getGoogleUser(oauth);
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
            pm.makePersistent(user);
            detached = pm.detachCopy(user);
        } catch (JDOObjectNotFoundException e) {
            detached = new User(id, email);
        } finally {
            pm.close();
        }
        return detached;
    }

    public static int delete(String oauth) {
        // Récupération du GoogleUser avec l'accessToken
        Map<String, String> userData = Oauth.getGoogleUser(oauth);
        if (userData == null) {
            return -1;
        }
        String id = userData.get("user_id");

        // Récupération du User avec l'ID Google
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            User user = pm.getObjectById(User.class, id);
            pm.deletePersistent(user);
            return 1;
        } catch (JDOObjectNotFoundException e) {
            return 0;
        } finally {
            pm.close();
        }
    }

    public void save() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(this);
        } finally {
            pm.close();
        }
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    public boolean addAddress(Address address) {
        return addresses.add(address);
    }

    public boolean removeAddress(Address address) {
        return addresses.remove(address);
    }

    public Set<Notification> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }

    public boolean addNotification(Notification notification) {
        return notifications.add(notification);
    }

    public boolean removeNotification(Notification notification) {
        return notifications.remove(notification);
    }
}
