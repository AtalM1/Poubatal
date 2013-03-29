package fr.univnantes.atal.poubatal.servlets;

import fr.univnantes.atal.poubatal.api.APIResult;
import fr.univnantes.atal.poubatal.api.APIResponse;
import fr.univnantes.atal.poubatal.api.GoogleUser;
import fr.univnantes.atal.poubatal.api.Oauth;
import fr.univnantes.atal.poubatal.datastore.User;
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
                GoogleUser gUser = Oauth.getGoogleUser(accessToken);
                if (gUser != null) {
                    String userId = gUser.getId();
                    User user = User.load(userId);
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
                out.println(addAddress(request.getParameter("address"), user));
                break;
            case "delete-address":
                out.println(deleteAddress(request.getParameter("address"), user));
                break;
            case "delete-account":
                out.println(deleteAccount(user));
                break;
            case "add-notif":
                out.println(addNotif(request.getParameter("type"), request.getParameter("value"), user));
                break;
            case "delete-notif":
                out.println(deleteNotif(request.getParameter("notif"), user));
                break;
            case "addresses-list":
                out.println(addressesList(user));
                break;
            case "notif-list":
                out.println(apiNotifList(user));
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

    private String addAddress(String address, User user) {
        if (address == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'address' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.addAddress(address);
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String deleteAddress(String address, User user) {
        if (address == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'address' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.deleteAddress(address);
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

    private String addNotif(String type, String value, User user) {
        if (type == null || value == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'type' or 'value' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.addNotif(type, value);
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String deleteNotif(String notif, User user) {
        if (notif == null) {
            APIResponse apiResponse = new APIResponse();
            APIResult apiResult = APIResult.wrongParameters();
            apiResult.setDetail("Parameter 'notif' is missing.");
            apiResponse.getMap().put("result", APIResult.wrongParameters());
            return apiResponse.toJson();
        } else {
            user.deleteNotif(notif);
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String addressesList(User user) {
        Set<String> addressesList = user.addressesList();
        APIResponse apiResponse = new APIResponse();
        apiResponse.getMap().put("result", APIResult.success());
        apiResponse.getMap().put("data", addressesList);
        return apiResponse.toJson();
    }

    private String apiNotifList(User user) {
        Set<String> notifList = user.notifList();
        APIResponse apiResponse = new APIResponse();
        apiResponse.getMap().put("result", APIResult.success());
        apiResponse.getMap().put("data", notifList);
        return apiResponse.toJson();
    }
}
