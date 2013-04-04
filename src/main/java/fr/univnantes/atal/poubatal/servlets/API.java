package fr.univnantes.atal.poubatal.servlets;

import fr.univnantes.atal.poubatal.api.APIResult;
import fr.univnantes.atal.poubatal.api.APIResponse;
import fr.univnantes.atal.poubatal.entity.Notification;
import fr.univnantes.atal.poubatal.entity.User;
import fr.univnantes.atal.poubatal.opendata.CollectePoint;
import fr.univnantes.atal.poubatal.opendata.DataManager;
import java.io.IOException;
import java.io.PrintWriter;
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
     * <code>GET</code>,
     * <code>POST</code>,
     * <code>DELETE</code> methods.
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
                    apiResponse.setResult(APIResult.authenticationRequired(accessToken));
                    out.println(apiResponse.toJson());
                }
            } else {
                APIResponse apiResponse = new APIResponse();
                apiResponse.setResult(APIResult.wrongParameters("accessToken", accessToken));
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
     * Handles the HTTP
     * <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
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
        String method = request.getMethod();
        if (method.equalsIgnoreCase("POST")) {
            String methodParam = request.getParameter("method");
            if (methodParam != null) {
                method = methodParam;
            }
        }
        method = method.toUpperCase();
        switch (pathInfo) {
            case "directory":
                switch (method) {
                    case "GET":
                        out.println(directory(request.getParameter("address")));
                        break;
                    default:
                        APIResponse apiResponse = new APIResponse();
                        apiResponse.setResult(APIResult.httpMethodNotAllowed(method, pathInfo));
                        out.println(apiResponse.toJson());
                        break;
                }
                break;
            case "address":
                switch (method) {
                    case "POST":
                        out.println(addAddress(request.getParameter("addressId"), user));
                        break;
                    case "GET":
                        out.println(addressesList(user));
                        break;
                    case "DELETE":
                        out.println(removeAddress(request.getParameter("addressId"), user));
                        break;
                    default:
                        APIResponse apiResponse = new APIResponse();
                        apiResponse.setResult(APIResult.httpMethodNotAllowed(method, pathInfo));
                        out.println(apiResponse.toJson());
                        break;
                }
                break;
            case "notification":
                switch (method) {
                    case "POST":
                        out.println(addNotification(request.getParameter("notificationId"), user));
                        break;
                    case "GET":
                        out.println(notificationList(user));
                        break;
                    case "DELETE":
                        out.println(removeNotification(request.getParameter("notificationId"), user));
                        break;
                    default:
                        APIResponse apiResponse = new APIResponse();
                        apiResponse.setResult(APIResult.httpMethodNotAllowed(method, pathInfo));
                        out.println(apiResponse.toJson());
                        break;
                }
                break;
            case "user":
                switch (method) {
                    case "DELETE":
                        out.println(deleteAccount(user));
                        break;
                    default:
                        APIResponse apiResponse = new APIResponse();
                        apiResponse.setResult(APIResult.httpMethodNotAllowed(method, pathInfo));
                        out.println(apiResponse.toJson());
                        break;
                }
                break;

            default:
                APIResponse apiResponse = new APIResponse();
                apiResponse.setResult(APIResult.nonExistentService(pathInfo));
                out.println(apiResponse.toJson());
                break;
        }
    }

    private String directory(String address) {
        if (address == null || address.trim().isEmpty()) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.success());
            apiResponse.setData(DataManager.getInstance().getPoints());
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.success());
            apiResponse.setData(DataManager.getInstance().getPoints(address, 15));
            return apiResponse.toJson();
        }
    }

    private String addAddress(String addressId, User user) {
        if (addressId == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.wrongParameters("addressId", addressId));
            return apiResponse.toJson();
        } else {
            user.addAddress(CollectePoint.getById(addressId));
            user.save();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String removeAddress(String addressId, User user) {
        if (addressId == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.wrongParameters("addressId", addressId));
            return apiResponse.toJson();
        } else {
            user.removeAddress(CollectePoint.getById(addressId));
            user.save();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String deleteAccount(User user) {
        user.deleteAccount();
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(APIResult.success());
        return apiResponse.toJson();
    }

    private String addNotification(String notificationId, User user) {
        if (notificationId == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.wrongParameters("notificationId", notificationId));
            return apiResponse.toJson();
        } else {
            user.addNotification(new Notification(notificationId));
            user.save();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String removeNotification(String notificationId, User user) {
        if (notificationId == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.wrongParameters("notificationId", notificationId));
            return apiResponse.toJson();
        } else {
            user.removeNotification(new Notification(notificationId));
            user.save();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setResult(APIResult.success());
            return apiResponse.toJson();
        }
    }

    private String addressesList(User user) {
        Set<CollectePoint> addressesList = user.getAddresses();
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(APIResult.success());
        apiResponse.setData(addressesList);
        return apiResponse.toJson();
    }

    private String notificationList(User user) {
        Set<Notification> notificationsList = user.getNotifications();
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(APIResult.success());
        apiResponse.setData(notificationsList);
        return apiResponse.toJson();
    }
}
