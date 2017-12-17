/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.EmailDAO;
import DAO.UserDAO;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
public class ResetPasswordCommand implements Command {

    /**
     * Used when email link has be sent and clicked, UUID is hashed and checked,
     * if valid than user enters new password, hashed than added to system.
     * If invalid, user is sent to error.jsp with error message.
     *
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        EmailDAO emailDao = new EmailDAO("librarydatabase");
        UserDAO userDao = new UserDAO("librarydatabase");

        User userRequest = userDao.findUserByID(Integer.parseInt(request.getParameter("user")));
        String UUID = request.getParameter("UUID");
        String password = request.getParameter("password");

        if (userRequest != null) {
            if (UUID != null) {
                if (userDao.updatePassword(password, userRequest)) {
                    String message = "Reset password Complete";
                    session.setAttribute("message", message);
                    forwardToJsp = "login.jsp";

                } else {
                    String errorMessage = "Error updating password";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }

            } else {
                String errorMessage = "Invalid UUID";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        } else {
            String errorMessage = "Error with userID";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
