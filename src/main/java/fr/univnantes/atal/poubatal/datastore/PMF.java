package fr.univnantes.atal.poubatal.datastore;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 *
 * @author Noemi
 */
public final class PMF {

    private static final PersistenceManagerFactory pmfInstance =
            JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {
    }

    /**
     *
     * @return
     */
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}