package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.entity.User;
import fr.univnantes.atal.poubatal.entity.Address;
import fr.univnantes.atal.poubatal.opendata.DataManager;
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
                error(response, HttpServletResponse.SC_BAD_REQUEST, "The 'address' parameter is missing");
            } else {
                Address objAddress = DataManager.getAddressById(address);
                if (objAddress == null) {
                    error(response, HttpServletResponse.SC_NOT_FOUND, "This address does not exist in Nantes OpenData");
                } else {
                    if (user.removeAddress(objAddress)) {
                        user.save();
                    } else {
                        error(response, HttpServletResponse.SC_NOT_FOUND, "This address is not registered in this account");
                    }
                }
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
            user.save();
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
                error(response, HttpServletResponse.SC_BAD_REQUEST, "The 'address' parameter is missing");
            } else {
                Address objAddress = DataManager.getAddressById(address);
                if (objAddress == null) {
                    error(response, HttpServletResponse.SC_NOT_FOUND, "This address does not exist in Nantes OpenData");
                } else {
                    if (user.addAddress(objAddress)) {
                        user.save();
                    } else {
                        error(response, HttpServletResponse.SC_CONFLICT, "This address is already registered in this account");
                    }
                }
            }
        }
    }
}
