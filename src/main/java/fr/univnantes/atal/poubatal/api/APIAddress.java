package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.entity.Notification;
import fr.univnantes.atal.poubatal.entity.User;
import fr.univnantes.atal.poubatal.entity.Address;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APIAddress extends API {

    @Override
    protected void delete(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = authenticate(request, response);
        if (user != null) {
            String address = request.getParameter("address");
            if (address == null) {
                error(response, HttpServletResponse.SC_BAD_REQUEST, "'address' parameter is missing");
            } else {
                user.removeAddress(Address.getById(address));
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
            Set<Address> addresses = user.getAddresses();
            data(response, addresses);
        }
    }

    @Override
    protected void post(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = authenticate(request, response);
        if (user != null) {
            String address = request.getParameter("address");
            if (address == null) {
                error(response, HttpServletResponse.SC_BAD_REQUEST, "'address' parameter is missing");
            } else {
                user.addAddress(Address.getById(address));
                user.save();
            }
        }
    }
}
