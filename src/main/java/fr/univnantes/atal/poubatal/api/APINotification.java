package fr.univnantes.atal.poubatal.api;

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
                error(response, HttpServletResponse.SC_BAD_REQUEST, "'notification' parameter is missing");
            } else {
                user.removeNotification(new Notification(notification));
                user.save();
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
            data(response, notifications);
        }
    }

    @Override
    protected void post(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = authenticate(request, response);
        if (user != null) {
            String notification = request.getParameter("notification");
            if (notification == null) {
                error(response, HttpServletResponse.SC_BAD_REQUEST, "'notification' parameter is missing");
            } else {
                user.addNotification(new Notification(notification));
                user.save();
            }
        }
    }
}
