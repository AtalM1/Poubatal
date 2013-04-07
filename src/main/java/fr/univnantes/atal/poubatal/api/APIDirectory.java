package fr.univnantes.atal.poubatal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.univnantes.atal.poubatal.tools.json.JSON;
import fr.univnantes.atal.poubatal.opendata.CollectePoint;
import fr.univnantes.atal.poubatal.opendata.DataManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APIDirectory extends API {

    @Override
    protected void get(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Set<CollectePoint> addresses = DataManager.getInstance().getPoints();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                out.println(JSON.data(addresses));
            } catch (JsonProcessingException ex) {
                error(request, response, ex.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}
