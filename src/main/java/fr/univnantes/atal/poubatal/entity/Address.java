package fr.univnantes.atal.poubatal.entity;

import com.googlecode.objectify.annotation.Embed;

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
}
