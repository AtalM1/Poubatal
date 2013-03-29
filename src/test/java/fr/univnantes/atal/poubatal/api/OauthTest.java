package fr.univnantes.atal.poubatal.api;

import org.junit.Test;
import static org.junit.Assert.*;

public class OauthTest {
    
    /**
     * Test of verifyOauth method, of class Oauth.
     */
    @Test
    public void testVerifyOauth() throws Exception {
        System.out.println("Test verifyOauth with wrong token");
        String token = "toto";
        GoogleUser expResult = null;
        GoogleUser result = Oauth.getGoogleUser(token);
        assertEquals(expResult, result);
    }
}