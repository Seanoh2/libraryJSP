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
 * @author Sean
 */
public class SignUpUserCommand implements Command {

    /**
     * Used to allow a user to register into the database.
     * All information is check to be valid or email isn't used already.
     * If valid, password is hashed and added to DB.
     * Otherwise, user is redirected to error with error message.
     *
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        UserDAO userDao = new UserDAO("librarydatabase");
        HttpSession session = request.getSession();
        // Get the information entered into the form by the user
        // Get the parameters from the previous page       
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String country = request.getParameter("country");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        int isAdmin = 0; //Users cannot be signed up as admin, must be promoted ny an existing admin.
        User u = new User(email, password, firstName, lastName, country, addressLine1, addressLine2, isAdmin);

        User result = userDao.getUserByEmail(email);
        //now you have the information confrim its valid information
        if (result == null) {
            //Attempting to add User to DB
            boolean addUser = userDao.addUser(u);

            //If the user was added sucessfully, they are told so and than logged in automatically.
            if (addUser) {
                session.setAttribute("user", u);
                forwardToJsp = "myHome.jsp";
            } else {
                //If any issues arised, they are not added and told so.
                String errorMessage = "Error adding user to database";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        } else {
            //If the email is in use, the user is told so and not added to DB, as emails need to be unique to allow login.
            String errorMessage = "Email already in use.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        //Forward user to correct location 
        return forwardToJsp;
    }
}
