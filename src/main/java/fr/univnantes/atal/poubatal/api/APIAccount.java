package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Noemi
 */
public class APIAccount extends API {

    @Override
    protected void delete(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String oauth = request.getParameter("oauth");
        if (oauth == null) {
            error(response, HttpServletResponse.SC_UNAUTHORIZED, "The 'oauth' parameter is missing");
        } else {
            int result = User.delete(oauth);
            switch (result) {
                case -1:
                    error(response, HttpServletResponse.SC_UNAUTHORIZED, "The oauth access token is incorrect");
                    break;
                case 0:
                    error(response, HttpServletResponse.SC_NOT_FOUND, "This account does not exist on Poubatal");
                    break;
            }

        }
    }
}
