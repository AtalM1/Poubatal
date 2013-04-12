package fr.univnantes.atal.poubatal.cron;

import fr.univnantes.atal.poubatal.Constants;
import fr.univnantes.atal.poubatal.datastore.PMF;
import fr.univnantes.atal.poubatal.entity.Address;
import fr.univnantes.atal.poubatal.entity.Notification;
import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Noemi
 */
public class CRONNotify extends HttpServlet {

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
        Extent<User> extent = pm.getExtent(User.class, false);
        try {
            DateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.FRANCE);
            long millis = 1000 * 60 * 60 * 24;
            String day = dateFormat.format(new Date().getTime() + millis);
            for (User user : extent) {
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
            extent.closeAll();
            pm.close();
        }
    }
}
