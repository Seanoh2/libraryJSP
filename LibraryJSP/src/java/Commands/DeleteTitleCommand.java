/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.TitleDAO;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
public class DeleteTitleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        User currentUser = (User) session.getAttribute("user");
        String errorMessage;

        if (currentUser.getUserID() == 1) {
            int userChoice = Integer.parseInt(request.getParameter("userChoice"));
            if (userChoice != 0) {
                boolean result = titleDao.deleteTitleById(userChoice);
                if (result) {
                    String message = "Title removed from database.";
                    forwardToJsp = "myHome.jsp";
                } else {
                    errorMessage = "Error deleting title.";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }
            } else {
                errorMessage = "No title ID found";
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
