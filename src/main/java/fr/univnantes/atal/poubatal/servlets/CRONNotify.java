package fr.univnantes.atal.poubatal.servlets;

import fr.univnantes.atal.poubatal.Constants;
import fr.univnantes.atal.poubatal.datastore.PMF;
import fr.univnantes.atal.poubatal.entity.Address;
import fr.univnantes.atal.poubatal.entity.Notification;
import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CRONNotify extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(User.class);
        try {
            List<User> users = (List<User>) query.execute();
            DateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.FRANCE);
            Date date = new Date();
            String day = dateFormat.format(date);
            for (User user : users) {
                for (Address address : user.getAddresses()) {
                    if (address.getBlueBin().contains(day)) {
                        for (Notification notification : user.getNotifications()) {
                            notification.send(address, Constants.BLUE_BIN);
                        }
                    }
                    if (address.getYellowBin().contains(day)) {
                        for (Notification notification : user.getNotifications()) {
                            notification.send(address, Constants.YELLOW_BIN);
                        }
                    }
                }
            }
        } finally {
            query.closeAll();
            pm.close();
        }
    }
}
