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
     * Test of Addresses attribute, of class User.
     */
    @Test
    public void testAddresses() {
        String csv = "\"RIVOLI\",\"LIBELLE\",\"COMMUNE\",\"MOT_DIRECTEUR\",\"STATUT\",\"TENANT\",\"ABOUTISSANT\",\"PRESTATION_COLLECTE\",\"TYPE_COLLECTE\",\"OBS_PRESTATION_COLLECTE\",\"BLEU_JOUR_COLLECTE\",\"JAUNE_JOUR_COLLECTE\",\"OBS_JOUR_COLLECTE\",\"QUARTIER\",\"OBS_QUARTIER\"";
        System.out.println("Test Set<Addresses>");
        User user = new User("idTest", "notif");
        Address address1 = new Address(csv);
        Address address2 = new Address(csv);
        Address address3 = new Address(csv);
        Address address4 = new Address(csv);
        user.addAddress(address1);
        user.addAddress(address1);
        user.addAddress(address2);
        user.addAddress(address3);
        user.removeAddress(address4);
        int expResult = 0;
        int result = user.getAddresses().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of Notifications attribute, of class User.
     */
    @Test
    public void testNotifications() {
        System.out.println("Test Set<Notification>");
        User user = new User("idTest", "notif");
        Notification notification1 = new Notification(Notification.EMAIL_NOTIFICATION, "notif");
        Notification notification2 = new Notification(Notification.EMAIL_NOTIFICATION, "notif");
        Notification notification3 = new Notification(Notification.EMAIL_NOTIFICATION, "notif");
        Notification notification4 = new Notification(Notification.EMAIL_NOTIFICATION, "notif");
        user.addNotification(notification1);
        user.addNotification(notification1);
        user.addNotification(notification2);
        user.addNotification(notification3);
        user.removeNotification(notification4);
        int expResult = 0;
        int result = user.getNotifications().size();
        assertEquals(expResult, result);
    }
}