/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author sildar
 */
class CollectableDays extends ArrayList<String>{

    CollectableDays(String dayString) {
        
        dayString = dayString.replaceAll("et", " ");
        dayString = dayString.replaceAll("-", " ");
        dayString = dayString.replaceAll("\\s+", " ");
        
        if (!dayString.contains("mixte")){
            this.addAll(Arrays.asList(dayString.split(" ")));
        }
        else
        {
            //handle mixtes ?
        }
        
    }
    
}
