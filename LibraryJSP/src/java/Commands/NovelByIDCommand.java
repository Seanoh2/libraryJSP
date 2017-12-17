/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.TitleDAO;
import Dtos.Title;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sean
 */
public class NovelByIDCommand implements Command {

    /**
     * Used to check database for title by ID. An title is set in
     * session to show results, redirected to home if any results found.
     * Otherwise, redirected to error.jsp with error message.
     *
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        HttpSession session = request.getSession();
        int titleRequest = Integer.parseInt(request.getParameter("titleRequest"));

        if (titleRequest != 0) {
            Title title = titleDao.searchByID(titleRequest);
            //If resonse isn't empty it is displayed to the user.
            if (title != null) {
                session.setAttribute("results", title);
                forwardToJsp = "myHome.jsp";
            } else {
                String errorMessage = "No Titles found with ID.";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        } else {
            String errorMessage = "Error with ID request.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }

}
