package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.tools.json.JSON;
import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class API extends HttpServlet {

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
        try {
            String method = request.getParameter("method");
            if (method != null) {
                switch (method.toLowerCase()) {
                    case "put":
                        put(request, response);
                        break;
                    case "delete":
                        delete(request, response);
                        break;
                    default:
                        error(response, HttpServletResponse.SC_BAD_REQUEST, "Bad parameter 'method': " + method);
                }
            } else {
                post(request, response);
            }
        } catch (Throwable e) {
            error(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, new Exception(e));
        }
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
    protected void post(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String protocol = request.getProtocol();
        if (protocol.endsWith("1.1")) {
            error(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "HTTP method POST is not supported by this URL");
        } else {
            error(response, HttpServletResponse.SC_BAD_REQUEST, "HTTP method POST is not supported by this URL");
        }
    }

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
        try {
            get(request, response);
        } catch (Throwable e) {
            error(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, new Exception(e));
        }

    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void get(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String protocol = request.getProtocol();
        if (protocol.endsWith("1.1")) {
            error(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "HTTP method GET is not supported by this URL");
        } else {
            error(response, HttpServletResponse.SC_BAD_REQUEST, "HTTP method GET is not supported by this URL");
        }
    }

    /**
     * Handles the HTTP
     * <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            put(request, response);
        } catch (Throwable e) {
            error(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, new Exception(e));
        }
    }

    /**
     * Handles the HTTP
     * <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void put(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String protocol = request.getProtocol();
        if (protocol.endsWith("1.1")) {
            error(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "HTTP method PUT is not supported by this URL");
        } else {
            error(response, HttpServletResponse.SC_BAD_REQUEST, "HTTP method PUT is not supported by this URL");
        }
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
        try {
            delete(request, response);
        } catch (Throwable e) {
            error(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, new Exception(e));
        }
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
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String protocol = request.getProtocol();
        if (protocol.endsWith("1.1")) {
            error(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "HTTP method DELETE is not supported by this URL");
        } else {
            error(response, HttpServletResponse.SC_BAD_REQUEST, "HTTP method DELETE is not supported by this URL");
        }
    }

    /**
     * Send a JSON formated HTTP error
     *
     * @param response servlet response
     * @param status status code of the error
     * @param error detail of the error
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void error(
            HttpServletResponse response,
            int status,
            String error)
            throws ServletException, IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(JSON.error(error));
        }
    }

    /**
     * Send a JSON formated HTTP error
     *
     * @param response servlet response
     * @param status status code of the error
     * @param exception the error exception
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void error(
            HttpServletResponse response,
            int status,
            Throwable exception)
            throws ServletException, IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(JSON.error(exception));
        }
    }

    /**
     * Send a JSON formated data
     *
     * @param response servlet response
     * @param data the data object
     * @param status status code of the error
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void data(
            HttpServletResponse response,
            Object data)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(JSON.data(data));
        }
    }

    protected User authenticate(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String oauth = request.getParameter("oauth");
        if (oauth == null) {
            error(response, HttpServletResponse.SC_UNAUTHORIZED, "'oauth' parameter is missing");
            return null;
        } else {
            User user = User.load(oauth);
            if (user == null) {
                error(response, HttpServletResponse.SC_UNAUTHORIZED, "The oauth access token is incorrect");
                return null;
            } else {
                return user;
            }
        }
    }
}
