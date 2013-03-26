package fr.univnantes.atal.poubatal.api;

import org.junit.Test;
import static org.junit.Assert.*;

public class OauthTest {
    
    /**
     * Test of verifyOauth method, of class Oauth.
     */
    @Test
    public void testVerifyOauth() {
        System.out.println("Test verifyOauth with wrong token");
        String token = "toto";
        boolean expResult = false;
        boolean result = Oauth.verifyOauth(token);
        assertEquals(expResult, result);
    }
}