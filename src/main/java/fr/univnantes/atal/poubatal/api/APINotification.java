package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.Constants;
import fr.univnantes.atal.poubatal.NotificationPropertiesFactory;
import fr.univnantes.atal.poubatal.entity.Notification;
import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APINotification extends API {

    @Override
    protected void delete(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = authenticate(request, response);
        if (user != null) {
            String notification = request.getParameter("notification");
            if (notification == null) {
                error(response, HttpServletResponse.SC_BAD_REQUEST, "The 'notification' parameter is missing");
            } else {
                Notification objNotification = user.getNotificationById(notification);
                if (objNotification == null) {
                    error(response, HttpServletResponse.SC_NOT_FOUND, "This notification is not registered in this account");
                } else {
                    if (user.removeNotification(objNotification)) {
                        user.save();
                    } else {
                        error(response, HttpServletResponse.SC_NOT_FOUND, "This notification is not registered in this account");
                    }
                }
            }
        }
    }

    @Override
    protected void get(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = authenticate(request, response);
        if (user != null) {
            Set<Notification> notifications = user.getNotifications();
            user.save();
            data(response, notifications);
        }
    }

    @Override
    protected void post(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = authenticate(request, response);
        if (user != null) {
            String type = request.getParameter("type");
            if (type == null) {
                error(response, HttpServletResponse.SC_BAD_REQUEST, "The 'type' parameter is missing");
            } else {
                switch (type) {
                    case Constants.EMAIL_NOTIFICATION:
                        String email = request.getParameter("email");
                        if (email == null) {
                            error(response, HttpServletResponse.SC_BAD_REQUEST, "The 'email' parameter is missing");
                        } else {
                            if (user.addNotification(
                                    new Notification(type, NotificationPropertiesFactory.getEmailProperties(email)))) {
                                user.save();
                            } else {
                                error(response, HttpServletResponse.SC_CONFLICT, "This notification is already registered in this account");
                            }
                        }
                        break;
                    default:
                        error(response, HttpServletResponse.SC_BAD_REQUEST, "The notification 'type' is incorrect");
                        break;
                }

            }
        }
    }
}
