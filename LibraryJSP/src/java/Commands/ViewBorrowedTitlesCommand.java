/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.BorrowedDAO;
import Dtos.Borrowed;
import Dtos.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
public class ViewBorrowedTitlesCommand implements Command {

    /**
     * Used to grab all borrowedTitles by user into an arrayList. Adds arrayList
     * into session variable to be displayed to user. Otherwise, redirects user
     * error.jsp with error message. 
     *
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /**
         * This is used to display all relevant borrowed in DB. This is user
         * specific based on who is logged in.
         */
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        BorrowedDAO borrowedDao = new BorrowedDAO("librarydatabase");
        User loggedIn = (User) session.getAttribute("user");

        if (loggedIn != null) {
            //Method is ran to find all borrowed related to user.
            ArrayList<Borrowed> borrowedList = borrowedDao.getBorrowedByUserID(loggedIn.getUserID());

            //Check to see if borrowedList isnt empty and there is borrowed related to user.
            if (!(borrowedList.isEmpty())) {
                session.setAttribute("results", borrowedList);
                forwardToJsp = "myHome.jsp";
            } else {
                String errorMessage = "No borrowed Titles found.";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            String errorMessage = "Must be logged in to preform action.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }

}
