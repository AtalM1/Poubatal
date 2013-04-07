package fr.univnantes.atal.poubatal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.univnantes.atal.poubatal.entity.Notification;
import fr.univnantes.atal.poubatal.entity.User;
import fr.univnantes.atal.poubatal.tools.json.JSON;
import java.io.IOException;
import java.io.PrintWriter;
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
                error(request, response, "'notification' parameter is missing", HttpServletResponse.SC_BAD_REQUEST);
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
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter out = response.getWriter()) {
                try {
                    out.println(JSON.data(notifications));
                } catch (JsonProcessingException ex) {
                    error(request, response, ex.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
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
                error(request, response, "'notification' parameter is missing", HttpServletResponse.SC_BAD_REQUEST);
            } else {
                user.addNotification(new Notification(notification));
                user.save();
            }
        }
    }
}
