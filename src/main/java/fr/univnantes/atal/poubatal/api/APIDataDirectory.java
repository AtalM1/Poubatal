/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.api;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noemi
 */
public class APIDataDirectory {
    
    private List<String> addressList;
    
    public APIDataDirectory() {
        addressList = new ArrayList();
    }    

    /**
     * @return the addressList
     */
    public List<String> getAddressList() {
        return addressList;
    }
}