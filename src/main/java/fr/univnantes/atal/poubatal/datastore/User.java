package fr.univnantes.atal.poubatal.datastore;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class User {

    private String id;
    private Set<String> addresses;
    private Set<String> notifications;

    private User(String id) {
        this.id = id;
        addresses = new TreeSet<>();
        notifications = new TreeSet<>();
    }

    public void save() {
        // save current user in the DataStore
    }

    public static User load(String id) {
        User user = new User(id);
        for (int i = 1; i <= 3; i++) {
            user.addAddress(i + ", Rue de " + id);
        }
        // retrieve a user in the DataStore
        return user;
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
