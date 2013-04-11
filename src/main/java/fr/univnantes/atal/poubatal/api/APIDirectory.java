package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.entity.Address;
import fr.univnantes.atal.poubatal.opendata.DataManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APIDirectory extends API {

    @Override
    protected void get(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<Address> addresses = DataManager.getInstance().getPoints();
        if (addresses == null) {
            error(response, HttpServletResponse.SC_NO_CONTENT, "Nantes OpenData is unavailable.");
        } else {
            if (addresses.isEmpty()) {
                error(response, HttpServletResponse.SC_NO_CONTENT, "Nantes OpenData is unavailable.");
            } else {
                data(response, addresses);
            }
        }
    }
}
