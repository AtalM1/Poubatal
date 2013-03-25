/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.servlets;

import fr.univnantes.atal.poubatal.api.APIDataDirectory;
import fr.univnantes.atal.poubatal.api.APIResult;
import fr.univnantes.atal.poubatal.api.APIResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter out = response.getWriter();
        try {
            if (request.getPathInfo().equalsIgnoreCase("/directory")) {
                out.println(apiDirectory(request.getParameter("address"), "oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/add-address")) {
                out.println(apiAddAddress(request.getParameter("address"), "oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/delete-address")) {
                out.println(apiDeleteAddress(request.getParameter("address"), "oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/new-account")) {
                out.println(apiNewAccount("oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/delete-account")) {
                out.println(apiDeleteAccount("oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/add-notif")) {
                out.println(apiAddNotif(request.getParameter("type"), request.getParameter("value"), "oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/delete-notif")) {
                out.println(apiAddNotif(request.getParameter("notif"), "oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/address-list")) {
                out.println(apiAddressList("oauth"));
            } else if (request.getPathInfo().equalsIgnoreCase("/notif-list")) {
                out.println(apiAddressList("oauth"));
            } else {
                APIResponse apiResponse = new APIResponse();
                apiResponse.getMap().put("result", APIResult.SERVICE_NON_EXISTING);
                out.println(apiResponse.toJson());
            }
        } finally {
            out.close();
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

    private String apiDirectory(String address, String oauthKey) {
        // Gestion des erreurs de paramètres
        if (address == null || oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIDataDirectory data = new APIDataDirectory();
            data.getAddressList().add(address + " 1");
            data.getAddressList().add(address + " 2");
            data.getAddressList().add(address + " 3");
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            apiResponse.getMap().put("data", data);
            return apiResponse.toJson();
        }

    }
    
    private String apiAddAddress(String address, String oauthKey) {
        // Gestion des erreurs de paramètres
        if (address == null || oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
    
    private String apiDeleteAddress(String address, String oauthKey) {
        // Gestion des erreurs de paramètres
        if (address == null || oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
    
    private String apiNewAccount(String oauthKey) {
        // Gestion des erreurs de paramètres
        if (oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
    
    private String apiDeleteAccount(String oauthKey) {
        // Gestion des erreurs de paramètres
        if (oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
    
    private String apiAddNotif(String type, String value, String oauthKey) {
        // Gestion des erreurs de paramètres
        if (type == null || value == null || oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
    
    private String apiAddNotif(String notif, String oauthKey) {
        // Gestion des erreurs de paramètres
        if (notif == null || oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
    
    private String apiAddressList(String oauthKey) {
        // Gestion des erreurs de paramètres
        if (oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
    
    private String apiNotifList(String oauthKey) {
        // Gestion des erreurs de paramètres
        if (oauthKey == null) {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.WRONG_PARAMETERS);
            return apiResponse.toJson();
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.getMap().put("result", APIResult.SUCCESS);
            return apiResponse.toJson();
        }
    }
}
