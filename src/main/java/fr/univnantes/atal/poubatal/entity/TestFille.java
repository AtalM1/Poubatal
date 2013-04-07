package fr.univnantes.atal.poubatal.entity;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class TestFille {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key; // Unused key
    
    @Persistent
    private String prout;

    public TestFille(String prout) {
        this.prout = prout;
    }

    /**
     * @return the prout
     */
    public String getProut() {
        return prout;
    }

    /**
     * @param prout the prout to set
     */
    public void setProut(String prout) {
        this.prout = prout;
    }
}
