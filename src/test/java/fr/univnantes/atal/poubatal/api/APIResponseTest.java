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
        APIResult apiResult = APIResult.nonExistentService("Test detail");
        instance.getMap().put("result", apiResult);
        String expResult = "{\"result\":{\"id\":" + apiResult.getId() + ",\"message\":\"" + apiResult.getMessage() + "\",\"detail\":\"" + apiResult.getDetail() + "\"}}";
        String result = instance.toJson();
        assertEquals(expResult, result);
    }
}