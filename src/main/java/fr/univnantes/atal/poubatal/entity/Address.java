package fr.univnantes.atal.poubatal.entity;

import com.googlecode.objectify.annotation.Embed;
import fr.univnantes.atal.poubatal.opendata.CollectePoint;
import fr.univnantes.atal.poubatal.opendata.DataManager;

@Embed
public class Address implements Comparable<Address> {

    private String id;

    public Address() {}

    public Address(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Address t) {
        return this.getId().compareToIgnoreCase(t.getId());
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    public CollectePoint getCollectePoint() {
        CollectePoint point = null;
        LoopCollectePoint:
        for (CollectePoint current : DataManager.getInstance().getPoints()) {
            if (current.getId().equals(id)) {
                point = current;
                break LoopCollectePoint;
            }
        }
        return point;
    }
}
