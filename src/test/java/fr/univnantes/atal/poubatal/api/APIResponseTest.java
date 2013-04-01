package fr.univnantes.atal.poubatal.api;

import java.text.Normalizer;
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
        String toto = "étudîationèf";
        System.out.println(Normalizer.normalize(toto, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", ""));
        APIResponse instance = new APIResponse();
        APIResult apiResult = APIResult.nonExistentService("Test detail");
        instance.setResult(apiResult);
        String expResult = "{\"result\":{\"id\":" + apiResult.getId() + ",\"message\":\"" + apiResult.getMessage() + "\",\"detail\":\"" + apiResult.getDetail() + "\"}}";
        String result = instance.toJson();
        assertEquals(expResult, result);
    }
}