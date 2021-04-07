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
        
        String uuid = (String) request.getParameter("uuid");
        
        if (uuid != null)
        {
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AccountService as = new AccountService();
        String uuid = (String) request.getParameter("uuid");
        String newPassword = (String) request.getParameter("newPassword");
        
        System.out.println("UUID: " + uuid);
        
        if (uuid != null)
        {
            System.out.println("UUID != null");
            as.changePassword(uuid, newPassword);
        }
        else
        {
            String email = (String) request.getParameter("resetPasswordEmail");
            String path = getServletContext().getRealPath("/WEB-INF");
            String url = request.getRequestURL().toString();

            as.resetPassword(email, path, url);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        /*
        if (uuid == null)
        {
            String email = (String) request.getAttribute("resetPasswordEmail");
            String path = getServletContext().getRealPath("/WEB-INF");
            String url = request.getRequestURL().toString();

            AccountService as = new AccountService();
            as.resetPassword(email, path, url);
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else 
        {
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
        }
        */
    }

}
