/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univnantes.atal.poubatal.servlets;

import fr.univnantes.atal.poubatal.api.APIDataDirectory;
import fr.univnantes.atal.poubatal.api.APIError;
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
                APIDataDirectory data = new APIDataDirectory();
                data.getAddressList().add("adresse 1");
                data.getAddressList().add("adresse 2");
                data.getAddressList().add("adresse 3");
                APIResponse apiResponse = new APIResponse(new APIError(), data);
                out.println(apiResponse.toJson());
            } else if (request.getPathInfo().equalsIgnoreCase("/add")) {
                APIResponse apiResponse = new APIResponse();
                out.println(apiResponse.toJson());
            } else if (request.getPathInfo().equalsIgnoreCase("/unknown-error")) {
                APIResponse apiResponse = new APIResponse();
                apiResponse.setError(new APIError(5));
                out.println(apiResponse.toJson());
            } else {
                APIResponse apiResponse = new APIResponse();
                apiResponse.setError(new APIError(APIError.SERVICE_NON_EXISTING));
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
}
