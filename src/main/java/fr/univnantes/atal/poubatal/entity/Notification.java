package fr.univnantes.atal.poubatal.entity;

import com.googlecode.objectify.annotation.Embed;

@Embed
public class Notification implements Comparable<Notification> {

    private String id;

    public Notification() {}

    public Notification(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Notification t) {
        return this.getId().compareToIgnoreCase(t.getId());
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
}
