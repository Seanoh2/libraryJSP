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
public class NovelByNameCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        HttpSession session = request.getSession();
        String titleRequest = (String) request.getParameter("titleRequest");

        if (titleRequest != null) {
            ArrayList<Title> titles = titleDao.searchByNovelName(titleRequest);
            //If resonse isn't empty it is displayed to the user.
            if (titles != null) {
                session.setAttribute("results", titles);
                forwardToJsp = "myHome.jsp";
            } else {
                String errorMessage = "No Title found with title name";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        } else {
            String errorMessage = "Error with Name request.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
