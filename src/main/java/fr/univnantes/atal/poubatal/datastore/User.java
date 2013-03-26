package fr.univnantes.atal.poubatal.datastore;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

class User {
    private String username;
    private Map<String, Set<String>> data; // address -> notification*

    public User(String username) {
        this.username = username;
        this.data = new TreeMap<String, Set<String>>();
    }

    public void save() {
        // save current user in the DataStore
    }

    public static User load(String username) {
        // retrieve a user in the DataStore
        return null;
    }

    // Add address and notifications
    public void add(String address) {
        if (!data.containsKey(address)) {
            data.put(address, new TreeSet<String>());
        }
    }

    public void add(String address, String notification) {
        add(address);
        data.get(address).add(notification);
    }

    public void add(String address, Set<String> notifications) {
        add(address);
        data.get(address).addAll(notifications);            
    }

    // Remove address and notifications
    public void remove(String address) {
        data.remove(address);
    }

    public void remove(String address, String notification) {
        if (data.containsKey(address)) {
            data.get(address).remove(notification);
        }
    }

    public void remove(String address, Set<String> notifications) {
        if (data.containsKey(address)) {
            data.get(address).removeAll(notifications);
        }
    }
}
