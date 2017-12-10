/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDAO;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author grahamm
 */
public class LoginUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        // Get the information entered into the form by the user
        // Get the parameters from the previous page       
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        //now you have the information confrim its valid information
        if (username != null && password != null && !username.equals("")) {
            //Call your DAO method 
            UserDAO userDao = new UserDAO("librarydatabase");
            User u = userDao.login(username, password);

            if (u != null) {
                session.setAttribute("user", u);
                forwardToJsp = "myHome.jsp";
            } else {
                String errorMessage = "Invalid credentials.";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            String errorMessage = "Not all information entered.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }

}
