package fr.univnantes.atal.poubatal.entity;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import static fr.univnantes.atal.poubatal.datastore.OfyService.ofy;

@Entity
public class User {

    @Id
    String id;
    Set<Address> addresses;
    Set<Notification> notifications;

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

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    public Set<Notification> getNotifications() {
        return Collections.unmodifiableSet(notifications);
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public void removeNotification(Notification notification) {
        notifications.remove(notification);
    }

    public void deleteAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
