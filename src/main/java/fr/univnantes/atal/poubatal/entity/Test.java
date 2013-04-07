package fr.univnantes.atal.poubatal.entity;

import fr.univnantes.atal.poubatal.datastore.PMF;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Test {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String id;
    
    @Persistent
    private String name;
    
    @Persistent(defaultFetchGroup = "true")
    private List<TestFille> filles;
    
    public Test(String id, String name) {
        this.id = id;
        this.name = name;
        filles = new ArrayList<>();
        filles.add(new TestFille("mliou"));
        filles.add(new TestFille("azerty"));
        filles.add(new TestFille("poiuyt"));
    }
    
    public static Test load(String id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        return pm.getObjectById(Test.class, id);
    }
    
    public void makePersistent() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(this);
        } finally {
            pm.close();
        }
    } 

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the filles
     */
    public List<TestFille> getFilles() {
        return filles;
    }

    /**
     * @param filles the filles to set
     */
    public void setFilles(List<TestFille> filles) {
        this.filles = filles;
    }
}
