package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APIAccount extends API {

    @Override
    protected void delete(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = authenticate(request, response);
        if (user != null) {
            user.deleteAccount();
        }        
    }
}
