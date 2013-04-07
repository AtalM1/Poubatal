package fr.univnantes.atal.poubatal.api;

import fr.univnantes.atal.poubatal.tools.json.JSON;
import fr.univnantes.atal.poubatal.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class API extends HttpServlet {

    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static final ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);

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
                    error(request, response, "Bad parameter 'method': " + method, HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            post(request, response);
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
        String msg = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            error(request, response, msg, HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            error(request, response, msg, HttpServletResponse.SC_BAD_REQUEST);
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
        get(request, response);
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
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            error(request, response, msg, HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            error(request, response, msg, HttpServletResponse.SC_BAD_REQUEST);
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
        put(request, response);
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
        String msg = lStrings.getString("http.method_put_not_supported");
        if (protocol.endsWith("1.1")) {
            error(request, response, msg, HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            error(request, response, msg, HttpServletResponse.SC_BAD_REQUEST);
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
        delete(request, response);
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
        String msg = lStrings.getString("http.method_delete_not_supported");
        if (protocol.endsWith("1.1")) {
            error(request, response, msg, HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            error(request, response, msg, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    /**
     * Send a JSON formated HTTP error
     *
     * @param request servlet request
     * @param response servlet response
     * @param error detail of the error
     * @param status status code of the error
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void error(
            HttpServletRequest request,
            HttpServletResponse response,
            String error,
            int status)
            throws ServletException, IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(JSON.error(error));
        }
    }

    protected User authenticate(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String oauth = request.getParameter("oauth");
        if (oauth == null) {
            error(request, response, "'oauth' parameter is missing", HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        } else {
            User user = User.load(oauth);
            if (user == null) {
                error(request, response, "The oauth access token is incorrect", HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            } else {
                return user;
            }
        }
    }
}
