/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solène
 */
public class UserTest {
    


    /**
     * Test of Notifications attribute, of class User.
     */
    @Test
    public void testNotifications() {
        System.out.println("Test Set<Notification>");
        User user = new User("idTest", "notif");
        Notification notification1 = new Notification("notif");
        Notification notification2 = new Notification("notif");
        user.addNotification(notification1);
        user.addNotification(notification1);
        user.addNotification(notification2);
        int expResult = 1;
        int result = user.getNotifications().size();
        assertEquals(expResult, result);
    }
}