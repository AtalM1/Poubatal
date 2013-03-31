package fr.univnantes.atal.poubatal.servlets;

import fr.univnantes.atal.poubatal.api.APIResult;
import fr.univnantes.atal.poubatal.api.APIResponse;
import fr.univnantes.atal.poubatal.entity.Address;
import fr.univnantes.atal.poubatal.entity.Notification;
import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Noemi
 */
public class API extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String accessToken = request.getParameter("oauth");
        try (PrintWriter out = response.getWriter()) {
            if (accessToken != null) {
                User user = User.load(accessToken);
                if (user != null) {
                    requestDispatcher(request, out, user);
                } else {
                    APIResponse apiResponse = new APIResponse();
                    APIResult result = APIResult.authenticationRequired();
                    result.setDetail("Invalid oauth access token : '" + accessToken + "'.");
                    apiResponse.getMap().put("result", result);
                    out.println(apiResponse.toJson());
                }
            } else {
                APIResponse apiResponse = new APIResponse();
                APIResult result = APIResult.wrongParameters();
                result.setDetail("Parameter 'oauth' is missing.");
                apiResponse.getMap().put("result", result);
                out.println(apiResponse.toJson());
            }
        }
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void requestDispatcher(HttpServletRequest request, PrintWriter out, User user) {
        String pathInfo = request.getPathInfo().substring(1).toLowerCase();
        switch (pathInfo) {
            case "directory":
                out.println(directory(request.getParameter("address")));
                break;
            case "add-address":
                out.println(addAddress(request.getParameter("addressId"), user));
                break;
            case "remove-address":
                out.println(removeAddress(request.getParameter("addressId"), user));
                break;
            case "delete-account":
                out.println(deleteAccount(user));
                break;
            case "add-notification":
                out.println(addNotification(request.getParameter("notificationId"), user));
                break;
            case "remove-notification":
                out.println(removeNotification(request.getParameter("notificationId"), user));
                break;
            case "addresses-list":
                out.println(addressesList(user));
                break;
            case "notifications-list":
                out.println(notificationList(user));
                break;
            default:
                APIResponse apiResponse = new APIResponse();
                APIResult result = APIResult.nonExistentService();
                result.setDetail("Service '" + pathInfo + "' dosen't exist.");
                apiResponse.getMap().put("result", result);
                out.println(apiResponse.toJson());
                break;
        }
    }

    private String directory(String address) {
        if (address == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'address' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            List<String> data = new ArrayList();
            data.add(address + " 1");
            data.add(address + " 2");
            data.add(address + " 3");
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            apiResponse.getMap().put("data", data);
            return apiResponse.toJson();
        }
    }

    private String addAddress(String addressId, User user) {
        if (addressId == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'addressId' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.addAddress(new Address(addressId));
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String removeAddress(String addressId, User user) {
        if (addressId == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'addressId' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.removeAddress(new Address(addressId));
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String deleteAccount(User user) {
        user.deleteAccount();
        APIResponse apiResponse = new APIResponse();
        apiResponse.getMap().put("result", APIResult.success());
        return apiResponse.toJson();
    }

    private String addNotification(String notificationId, User user) {
        if (notificationId == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'notificationId' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.addNotification(new Notification(notificationId));
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String removeNotification(String notificationId, User user) {
        if (notificationId == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'notificationId' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.removeNotification(new Notification(notificationId));
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String addressesList(User user) {
        Set<Address> addressesList = user.getAddresses();
        APIResponse apiResponse = new APIResponse();
        apiResponse.getMap().put("result", APIResult.success());
        apiResponse.getMap().put("data", addressesList);
        return apiResponse.toJson();
    }

    private String notificationList(User user) {
        Set<Notification> notificationsList = user.getNotifications();
        APIResponse apiResponse = new APIResponse();
        apiResponse.getMap().put("result", APIResult.success());
        apiResponse.getMap().put("data", notificationsList);
        apiResponse.getMap().put("toto", "prout");
        return apiResponse.toJson();
    }
}
