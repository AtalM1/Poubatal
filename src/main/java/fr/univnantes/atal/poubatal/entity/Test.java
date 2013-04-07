package fr.univnantes.atal.poubatal.entity;

import fr.univnantes.atal.poubatal.datastore.PMF;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Test {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    public String id;
    
    @Persistent
    public String name;
    
//    @Persistent
//    public List<TestFille> filles;
    
    public Test(String id, String name) {
        this.id = id;
        this.name = name;
//        filles = new ArrayList<>();
//        TestFille fille = new TestFille("test");
//        filles.add(fille);
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
}
