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

/**
 *
 * @author Noemi
 */
@PersistenceCapable
public class User {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String id;
    @Persistent(defaultFetchGroup = "true", serialized = "true")
    private Set<Address> addresses;
    @Persistent(defaultFetchGroup = "true", serialized = "true")
    private Set<Notification> notifications;

    private User() {
        addresses = new HashSet<>();
        notifications = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * @param email
     */
    public User(String id, String email) {
        this.id = id;
        addresses = new HashSet<>();
        notifications = new HashSet<>();
        notifications.add(new Notification(Notification.EMAIL_NOTIFICATION, email));
    }

    /**
     *
     * @param oauth
     * @return
     */
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
        User user, detached;
        try {
            user = pm.getObjectById(User.class, id);
            detached = pm.detachCopy(user);
        } catch (JDOObjectNotFoundException e) {
            user = new User(id, email);
            pm.makePersistent(user);
            detached = pm.detachCopy(user);

        } finally {
            pm.close();
        }
        return detached;
    }

    /**
     *
     * @param oauth
     * @return
     */
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

    /**
     *
     * @param notificationId
     * @return
     */
    public Notification getNotificationById(String notificationId) {
        Notification notification = null;
        LoopNotifications:
        for (Notification current : notifications) {
            if (current.getId().equals(notificationId)) {
                notification = current;
                break LoopNotifications;
            }
        }
        return notification;
    }

    /**
     *
     */
    public void save() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(this);
        } finally {
            pm.close();
        }
    }

    /**
     *
     * @return
     */
    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    /**
     *
     * @param address
     * @return
     */
    public boolean addAddress(Address address) {
        return addresses.add(address);
    }

    /**
     *
     * @param address
     * @return
     */
    public boolean removeAddress(Address address) {
        return addresses.remove(address);
    }

    /**
     *
     * @return
     */
    public Set<Notification> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }

    /**
     *
     * @param notification
     * @return
     */
    public boolean addNotification(Notification notification) {
        return notifications.add(notification);
    }

    /**
     *
     * @param notification
     * @return
     */
    public boolean removeNotification(Notification notification) {
        return notifications.remove(notification);
    }
}
