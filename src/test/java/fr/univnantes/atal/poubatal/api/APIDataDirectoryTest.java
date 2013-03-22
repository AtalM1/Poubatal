package fr.univnantes.atal.poubatal.api;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noemi
 */
public class APIDataDirectoryTest {
    
    public APIDataDirectoryTest() {
    }
    
    /**
     * Test of adressList variable, of class APIDataDirectory.
     */
    @Test
    public void testAddressList() {
        System.out.println("Test addressList");
        String address = "test";
        APIDataDirectory instance = new APIDataDirectory();
        instance.getAddressList().add(address);
        assertEquals(instance.getAddressList().get(0), address);
    }
}