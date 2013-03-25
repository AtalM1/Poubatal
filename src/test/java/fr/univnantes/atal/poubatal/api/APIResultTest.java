package fr.univnantes.atal.poubatal.api;

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
     * Test of SUCCESS static object, of class APIResult.
     */
    @Test
    public void testSuccess() {
        System.out.println("Test SUCCESS");
        APIResult instance = APIResult.SUCCESS;
        assertEquals(instance.getId(), 0);
    }
    
    /**
     * Test of SERVICE_NON_EXISTING static object, of class APIResult.
     */
    @Test
    public void testServiceNonExisting() {
        System.out.println("Test SERVICE_NON_EXISTING");
        APIResult instance = APIResult.SERVICE_NON_EXISTING;
        assertEquals(instance.getId(), 1);
    }
    
    /**
     * Test of WRONG_PARAMETERS static object, of class APIResult.
     */
    @Test
    public void testWrongParameters() {
        System.out.println("Test WRONG_PARAMETERS");
        APIResult instance = APIResult.WRONG_PARAMETERS;
        assertEquals(instance.getId(), 2);
    }
}