package fr.univnantes.atal.poubatal.datastore;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

class User {
    private String username;
    private Set<String> addresses;
    private Set<String> notifications;

    public User(String username) {
        this.username = username;
        addresses = new TreeSet<String>();
        notifications = new TreeSet<String>();
    }

    public void save() {
        // save current user in the DataStore
    }

    public static User load(String username) {
        // retrieve a user in the DataStore
        return null;
    }

    // Add address and notifications
    public void addAddress(String address) {
        addresses.add(address);
    }

    public void addNotifications(String notification) {
        notifications.add(notification);
    }

    // Remove address and notifications
    public void removeAddress(String address) {
        addresses.remove(address);
    }

    public void remove(String notification) {
        notifications.remove(notification);
    }
}
