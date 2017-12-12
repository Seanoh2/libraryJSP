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
public class SelectBorrowedCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        BorrowedDAO borrowedDao = new BorrowedDAO("librarydatabase");

        int titleID = (Integer) session.getAttribute("borrowedChoice");
        Title titleChoice = titleDao.searchByID(titleID);
        User loggedInUser = (User) session.getAttribute("user");
        Borrowed attemptAdd = new Borrowed(loggedInUser, titleChoice, null, 0);

        if (borrowedDao.checkIfAlreadyBorrowed(loggedInUser.getUserID(), titleID) == null) {
            if (borrowedDao.addBorrowed(attemptAdd)) {
                //updating title info to decrease stock and increase onLoan.
                titleChoice.setStock((titleChoice.getStock() - 1));
                titleChoice.setOnLoan((titleChoice.getOnLoan() + 1));
                titleDao.updateTitle(titleChoice.getTitleID(), titleChoice);
                forwardToJsp = "myHome.jsp";
            } else {
                String errorMessage = "Error registering borrowed title.";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            String errorMessage = "Title already registered by user.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;

    }

}
