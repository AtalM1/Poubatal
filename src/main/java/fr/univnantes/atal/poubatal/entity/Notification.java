package fr.univnantes.atal.poubatal.entity;

import com.google.appengine.api.datastore.Key;
import fr.univnantes.atal.poubatal.Constants;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@PersistenceCapable
public class Notification {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key; // Unused key
    @Persistent
    private String id;
    @Persistent
    private String type;
    @Persistent(defaultFetchGroup = "true")
    private Map<String, String> properties;

    private Notification() {
    }

    public Notification(String type, Map<String, String> properties) {
        switch (type) {
            case Constants.EMAIL_NOTIFICATION:
                this.type = type;
                this.properties = properties;
                this.id = type + "-" + properties.get("email");
                break;
            default:
                this.type = Constants.ERROR_NOTIFICATION;
                this.properties = properties;
                this.id = Constants.ERROR_NOTIFICATION;
                break;
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the properties
     */
    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            Notification other = (Notification) obj;
            return getId().equals(other.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    public void send(Address address, String binColor) {
        System.out.println("SEND NOTIFICATION: " + type);
        switch (type) {
            case Constants.EMAIL_NOTIFICATION:
                sendEmail(address, binColor);
                break;
        }
    }

    private void sendEmail(Address address, String binColor) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
     
        String email = properties.get("email");
        String subject = "Poubatal - Ordures " + binColor;
        String msgBody = "Notification automatique pour vous prévenir du ramassage des ordures " + binColor
                + " à votre adresse " + address.getStreetName();
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("notifications@poubatal.appspotmail.com", "Notification Poubatal"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));
            msg.setSubject(subject);
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
