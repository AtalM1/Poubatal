package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.servlets.APIResult;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noemi
 */
public class APIResultTest {
    
    public APIResultTest() {
    }
    
    /**
     * Test of success() static method, of class APIResult.
     */
    @Test
    public void testSuccess() {
        System.out.println("Test success()");
        APIResult instance = APIResult.success();
        assertEquals(instance.getId(), 0);
    }
    
    /**
     * Test of nonExistentService() static method, of class APIResult.
     */
    @Test
    public void testServiceNonExisting() {
        System.out.println("Test nonExistentService()");
        APIResult instance = APIResult.nonExistentService("Test detail");
        assertEquals(instance.getId(), 1);
    }
    
    /**
     * Test of wrongParameters() static method, of class APIResult.
     */
    @Test
    public void testWrongParameters() {
        System.out.println("Test wrongParameters()");
        APIResult instance = APIResult.wrongParameters("name", "value");
        assertEquals(instance.getId(), 2);
    }
}