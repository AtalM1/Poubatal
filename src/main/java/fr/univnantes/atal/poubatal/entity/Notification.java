package fr.univnantes.atal.poubatal.entity;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Notification implements Comparable<Notification> {
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key; // Unused key

    @Persistent
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
