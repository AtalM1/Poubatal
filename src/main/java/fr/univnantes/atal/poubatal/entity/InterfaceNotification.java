package fr.univnantes.atal.poubatal.entity;

public interface InterfaceNotification {
    
    /**
     * Send the notification
     * @param address The collect point address
     * @param binColor The bin color
     */
    public void send(Address address, String binColor);
    
    public String getId();
}
