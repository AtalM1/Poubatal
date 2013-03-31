package fr.univnantes.atal.poubatal.datastore;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;                                                                                                                             import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import static fr.univnantes.atal.poubatal.datastore.OfyService.ofy;

@Entity
public class User {

    @Id String id;
    Set<String> addresses;
    Set<String> notifications;

    private User() {}
    
    public User(String id) {
        this.id = id;
        addresses = new TreeSet<>();
        notifications = new TreeSet<>();
    }

    public String getId() {
        return id;
    }

    // save a User in the DataStore
    public void save() {
        ofy().save().entity(this).now();
    }

    // Load a User from the DataStore, returns null if not found.
    public static User load(String id) {
        return ofy().load().type(User.class).id(id).get();
    }

    public void addAddress(String address) {
        addresses.add(address);
    }

    public void deleteAddress(String address) {
        addresses.remove(address);
    }

    public void deleteAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addNotif(String type, String value) {
        notifications.add(value);
    }

    public void deleteNotif(String notif) {
        notifications.remove(notif);
    }

    public Set<String> addressesList() {
        return Collections.unmodifiableSet(addresses);
    }

    public Set<String> notifList() {
        return Collections.unmodifiableSet(notifications);
    }
}
