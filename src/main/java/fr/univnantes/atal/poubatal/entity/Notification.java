package fr.univnantes.atal.poubatal.entity;

import com.googlecode.objectify.annotation.Embed;

@Embed
public class Notification implements Comparable<Notification> {

    private String id;

    private Notification() {}

    public Notification(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Notification t) {
        System.out.println(this.id + " COMPARED TO " + t.id + " -> " + this.id.compareToIgnoreCase(t.id));
        return this.id.compareToIgnoreCase(t.id);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
}
