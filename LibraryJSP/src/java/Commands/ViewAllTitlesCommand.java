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
public class ViewAllTitlesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        ArrayList<Title> results = titleDao.viewAllTitles();

        if (results == null || results.isEmpty()) {
            String errorMessage = "Error grabbing titles from Database";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        } else {
            session.setAttribute("results", results);
            forwardToJsp = "myHome.jsp";
        }

        return forwardToJsp;
    }
}
