package fr.univnantes.atal.poubatal.entity;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Notification implements Serializable {

    public static final String EMAIL_NOTIFICATION = "email";
    public static final String XMPP_NOTIFICATION = "xmpp";
    public static final String ERROR_NOTIFICATION = "error";
    private static final long serialVersionUID = 1L;
    private String id;
    private String type;
    private String email;

    private Notification() {
    }

    public Notification(String type, String email) {
        switch (type) {
            case EMAIL_NOTIFICATION:
            case XMPP_NOTIFICATION:
                this.type = type;
                this.email = email;
                this.id = type + "-" + email;
                break;
            default:
                this.type = ERROR_NOTIFICATION;
                this.email = email;
                this.id = ERROR_NOTIFICATION;
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
     * @return the email
     */
    public String getEmail() {
        return email;
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
        switch (type) {
            case EMAIL_NOTIFICATION:
                sendEmail(address, binColor);
                break;
            case XMPP_NOTIFICATION:
                sendXMPP(address, binColor);
                break;
        }
    }

    private void sendEmail(Address address, String binColor) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String subject = "Ordures " + binColor + " - " + address.getStreetName();
        String msgBody = "Ramassage des ordures " + binColor
                + " demain à votre adresse " + address.getStreetName();
        try {
            javax.mail.Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("notifications@poubatal.appspotmail.com", "Notification Poubatal"));
            msg.addRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(email));
            msg.setSubject(subject);
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void sendXMPP(Address address, String binColor) {
        JID jid = new JID(email);
        String msgBody = "Ramassage des ordures " + binColor
                + " demain à votre adresse " + address.getStreetName();
        com.google.appengine.api.xmpp.Message msg = new MessageBuilder()
                .withRecipientJids(jid)
                .withBody(msgBody)
                .build();

        boolean messageSent = false;
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        if (xmpp.getPresence(jid).isValid()) {
            SendResponse status = xmpp.sendMessage(msg);
            messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);
        }
        if (!messageSent) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, "{0} not sent", email);
        }

    }
}
