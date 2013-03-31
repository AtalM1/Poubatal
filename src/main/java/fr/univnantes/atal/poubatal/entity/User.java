package fr.univnantes.atal.poubatal.entity;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import fr.univnantes.atal.poubatal.Oauth;
import static fr.univnantes.atal.poubatal.datastore.OfyService.ofy;
import java.util.Map;

@Entity
public class User {

    @Id
    private String id;
    private Set<Address> addresses;
    private Set<Notification> notifications;

    private User() {
    }

    public User(String id, String email) {
        this.id = id;
        addresses = new TreeSet<>();
        notifications = new TreeSet<>();
        notifications.add(new Notification(email));
    }

    // save a User in the DataStore
    public void save() {
        ofy().save().entity(this).now();
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
        User user = ofy().load().type(User.class).id(id).get();
        if (user == null) {
            user = new User(id, email);
            user.save();
        }
        return user;
    }

    public Set<Address> getAddresses() {
        if (addresses == null) {
            addresses = new TreeSet<>();
        }
        return Collections.unmodifiableSet(addresses);
    }

    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new TreeSet<>();
        }
        addresses.add(address);
        save();
    }

    public void removeAddress(Address address) {
        if (addresses == null) {
            addresses = new TreeSet<>();
        }
        addresses.remove(address);
        save();
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
        save();
    }

    public void removeNotification(Notification notification) {
        if (notifications == null) {
            notifications = new TreeSet<>();
        }
        notifications.remove(notification);
        save();
    }

    public void deleteAccount() {
        ofy().delete().entity(this);
    }
}
