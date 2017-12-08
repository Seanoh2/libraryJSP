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
public class NovelByIDCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        HttpSession session = request.getSession();
        int titleRequest = (Integer)session.getAttribute("titleRequest");
        
        Title title = titleDao.searchByID(titleRequest);
                    //If resonse isn't empty it is displayed to the user.
                    if (title != null) {
                        session.setAttribute("displayTitle", title);
                        forwardToJsp = "myHome.jsp";
                    } else {
                        String errorMessage = "No Titles found with ID.";
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "error.jsp";
                    }
                    
        return forwardToJsp;
    }
    
}
