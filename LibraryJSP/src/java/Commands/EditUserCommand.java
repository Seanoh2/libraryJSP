/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDAO;
import Dtos.Title;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
public class EditUserCommand implements Command {

    /**
     * Used to edit users information, exception being password which is done apart.
     * Any blanks on form are replaced with original information.
     * If blanks still present, Error appears.
     * Otherwise, Info is replaced and session is updated to show this.
     * 
     * 
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser != null) {
            Integer userID = currentUser.getUserID();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String country = request.getParameter("country");
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            Integer isAdmin = currentUser.getIsAdmin();

            if (userID == null) {
                userID = currentUser.getUserID();
            }
            if (email == null) {
                email = currentUser.getEmail();
            }
            if (password == null) {
                password = currentUser.getPassword();
            }
            if (firstName == null) {
                firstName = currentUser.getFirstName();
            }
            if (lastName == null) {
                lastName = currentUser.getLastName();
            }
            if (country == null) {
                country = currentUser.getCountry();
            }
            if (addressLine1 == null) {
                addressLine1 = currentUser.getAddressLine1();
            }
            if (addressLine2 == null) {
                addressLine2 = currentUser.getAddressLine2();
            }
            if (isAdmin == null) {
                isAdmin = currentUser.getIsAdmin();
            }

            if (userID != null || userID > 0) {
                if (email != null || !email.isEmpty()) {
                    if (password != null || !password.isEmpty()) {
                        if (firstName != null || !firstName.isEmpty()) {
                            if (lastName != null || !lastName.isEmpty()) {
                                if (country != null || !country.isEmpty()) {
                                    if (addressLine1 != null || !addressLine1.isEmpty()) {
                                        if (addressLine2 != null || !addressLine2.isEmpty()) {
                                            if (isAdmin != null || isAdmin > 0) {
                                                UserDAO userDao = new UserDAO("librarydatabase");
                                                User attemptUpdate = new User(userID, email, password, firstName, lastName, country, addressLine1, addressLine2, isAdmin);
                                                if (userDao.updateUser(attemptUpdate)) {
                                                    String message = "Update Complete!";
                                                    session.setAttribute("user", attemptUpdate);
                                                    session.setAttribute("message", message);
                                                    forwardToJsp = "myHome.jsp";
                                                } else {
                                                    String errorMessage = "Error updating user details.";
                                                    session.setAttribute("errorMessage", errorMessage);
                                                    forwardToJsp = "error.jsp";
                                                }

                                            } else {
                                                String errorMessage = "Invalid input for isAdmin";
                                                session.setAttribute("errorMessage", errorMessage);
                                                forwardToJsp = "error.jsp";
                                            }
                                        } else {
                                            String errorMessage = "Invalid input for addressLine2";
                                            session.setAttribute("errorMessage", errorMessage);
                                            forwardToJsp = "error.jsp";
                                        }
                                    } else {
                                        String errorMessage = "Invalid input for addressLine1";
                                        session.setAttribute("errorMessage", errorMessage);
                                        forwardToJsp = "error.jsp";
                                    }
                                } else {
                                    String errorMessage = "Invalid input for country";
                                    session.setAttribute("errorMessage", errorMessage);
                                    forwardToJsp = "error.jsp";
                                }
                            } else {
                                String errorMessage = "Invalid input for lastName";
                                session.setAttribute("errorMessage", errorMessage);
                                forwardToJsp = "error.jsp";
                            }
                        } else {
                            String errorMessage = "Invalid input for firstName";
                            session.setAttribute("errorMessage", errorMessage);
                            forwardToJsp = "error.jsp";
                        }
                    } else {
                        String errorMessage = "Invalid input for password";
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "error.jsp";
                    }
                } else {
                    String errorMessage = "Invalid input for email";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }
            } else {
                String errorMessage = "Invalid input for userID";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            String errorMessage = "Must be logged in as user.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
