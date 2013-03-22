package fr.univnantes.atal.poubatal.api;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noemi
 */
public class APIErrorTest {
    
    public APIErrorTest() {
    }
    
    /**
     * Test of SERVICE_NON_EXISTING static object, of class APIError.
     */
    @Test
    public void testServiceNonExisting() {
        System.out.println("Test SERVICE_NON_EXISTING");
        APIError instance = APIError.SERVICE_NON_EXISTING;
        assertEquals(instance.getId(), 0);
    }
}