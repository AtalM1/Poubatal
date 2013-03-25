package fr.univnantes.atal.poubatal.api;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noemi
 */
public class APIResponseTest {
    
    public APIResponseTest() {
    }

    /**
     * Test of toJson method, of class APIResponse.
     */
    @Test
    public void testToJson() {
        System.out.println("Test toJson");
        APIResponse instance = new APIResponse();
        instance.getMap().put("result", APIResult.SERVICE_NON_EXISTING);
        String expResult = "{\"result\":{\"id\":";
        String result = instance.toJson();
        assertTrue(result.startsWith(expResult));
    }
}