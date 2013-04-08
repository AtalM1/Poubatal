package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.entity.Address;
import fr.univnantes.atal.poubatal.opendata.DataManager;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APIDirectory extends API {

    @Override
    protected void get(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Set<Address> addresses = DataManager.getInstance().getPoints();
        data(response, addresses);
    }
}
