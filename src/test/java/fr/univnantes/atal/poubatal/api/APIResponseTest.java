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
     * Test of toJson method, of class APIResponse.
     */
    @Test
    public void testToJson() {
        System.out.println("Test toJson");
        APIResponse instance = new APIResponse();
        String expResult = "toto";
        String result = "toto";
        assertEquals(expResult, result);
    }
}