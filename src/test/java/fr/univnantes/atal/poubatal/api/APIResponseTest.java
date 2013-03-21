/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.api;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noemi
 */
public class APIResponseTest {
    
    public APIResponseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of default constructor, of class APIResponse.
     */
    @Test
    public void testDefaultConstructor() {
        System.out.println("Test default constructor");
        APIResponse instance = new APIResponse();
        String expResult = new APIError(APIError.SUCCESS).getMessage() + "-" + null;
        String result = instance.getError().getMessage() + "-" + instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Test of toJson method, of class APIResponse.
     */
    @Test
    public void testToJson() {
        System.out.println("Test toJson");
        APIResponse instance = new APIResponse(new APIError(APIError.SUCCESS), null);
        String expResult = "{\"error\":{\"id\":0,\"message\":\"succès\"},\"data\":null}";
        String result = instance.toJson();
        assertEquals(expResult, result);
    }
}