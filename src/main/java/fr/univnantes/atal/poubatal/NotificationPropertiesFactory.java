package fr.univnantes.atal.poubatal;

import java.util.HashMap;
import java.util.Map;

public class NotificationPropertiesFactory {

    public static Map<String, String> getEmailProperties(String email) {
        Map<String, String> properties = new HashMap<>();
        properties.put("email", email);
        return properties;
    }
}
