package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author Trevor Erixon
 */
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uuid = request.getParameter("uuid");
        
        if (uuid == null)
        {
            String email = (String) request.getAttribute("resetPasswordEmail");
            String path = getServletContext().getRealPath("not sure what goes here");
            String url = request.getRequestURL().toString();

            AccountService as = new AccountService();
            as.resetPassword(email, path, url);
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else 
        {
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        }

    }

}
