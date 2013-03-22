/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.api;

import java.util.List;
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