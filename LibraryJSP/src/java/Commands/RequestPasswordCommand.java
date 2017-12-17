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
public class RequestPasswordCommand implements Command {

    /**
     * Used for user to request a password recovery email. If information is
     * valid and email is in database, User is sent a email with link and UUID
     * to identify request. Otherwise, Redirected to error with error message.
     *
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        UserDAO userDao = new UserDAO("librarydatabase");
        EmailDAO emailDao = new EmailDAO("librarydatabase");
        String email = request.getParameter("email");

        if (email != null && !email.isEmpty()) {
            User checkEmail = userDao.getUserByEmail(email);

            if (checkEmail != null) {
                if (emailDao.sendEmailRecovery(email)) {
                    session.setAttribute("message", "Recovery message have been sent.");
                    forwardToJsp = "login.jsp";
                } else {
                    String errorMessage = "Error sending request password.";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }

            } else {
                String errorMessage = "Email not found in system.";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        } else {
            String errorMessage = "Email must be filled in.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
