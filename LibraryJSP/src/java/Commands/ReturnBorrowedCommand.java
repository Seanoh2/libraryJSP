/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.BorrowedDAO;
import DAO.TitleDAO;
import Dtos.Borrowed;
import Dtos.Title;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
public class ReturnBorrowedCommand implements Command {

    /**
     * Used to return a borrowed title from DB. This is typically done after
     * user has payed overdue fees. If valid, stock and onloan is changed to
     * reflect added title. Otherwise, redirected to error.jsp with error
     * message.
     *
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();

        User loggedIn = (User) session.getAttribute("user");

        if (loggedIn != null) {
            int borrowedInt = Integer.parseInt(request.getParameter("borrowed"));
            BorrowedDAO borrowedDao = new BorrowedDAO("librarydatabase");
            TitleDAO titleDao = new TitleDAO("librarydatabase");

            Borrowed borrowedChoice = borrowedDao.getBorrowedByID(borrowedInt);
            Title returnedTitle;

            //Title information is updated, first borrowed status is set to 1 to indicate it is no longer on loan.
            borrowedDao.updateStatus(borrowedChoice.getBorrowedID(), 1);

            //Next, update done to title stock levels to indicate a title is returned.
            returnedTitle = borrowedChoice.getTitle();
            returnedTitle.setStock((returnedTitle.getStock() + 1));

            //Finally, onLoan is decreased to allow show less titles are on loan.
            returnedTitle.setOnLoan((returnedTitle.getOnLoan() - 1));

            if (titleDao.updateTitle(returnedTitle.getTitleID(), returnedTitle)) {
                session.setAttribute("message", "Return complete!");
                forwardToJsp = "returnTitle.jsp";
            } else {
                String errorMessage = "Error updating database";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        } else {
            String errorMessage = "You must be logged in to preform action.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
