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
public class DeleteUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        UserDAO userDao = new UserDAO("librarydatabase");
        User currentUser = (User) session.getAttribute("user");
        String errorMessage;

        if (currentUser.getUserID() == 1) {
            int userChoice = Integer.parseInt(request.getParameter("userChoice"));
            int result = userDao.removeUser(userChoice);
            if (userChoice != 0) {
                switch (result) {
                    case 1:
                        String message = "User has been deleted from server.";
                        forwardToJsp = "myHome.jsp";
                        break;
                    case 2:
                        errorMessage = "User is an admin. Cannot delete.";
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "error.jsp";
                        break;
                    case 3:
                        errorMessage = "No user found with ID.";
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "error.jsp";
                        break;
                    default:
                        errorMessage = "Error deleting user from database.";
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "error.jsp";
                        break;
                }
            } else {
                errorMessage = "No user ID entered";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            errorMessage = "Admin account must be used to preform action.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
