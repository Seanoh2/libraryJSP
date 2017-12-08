/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.TitleDAO;
import Dtos.Title;
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
        String titleRequest = session.getAttribute("titleRequest").toString();
        
        Title title = titleDao.searchByNovelName(titleRequest);

                    //If resonse isn't empty it is displayed to the user.
                    if (title != null) {
                        session.setAttribute("displayTitle", title);
                        forwardToJsp = "myHome.jsp";
                    } else {
                        String errorMessage = "No Title found with title name";
                        session.setAttribute("errorMessage", errorMessage);
                        forwardToJsp = "error.jsp";
                    }
                    
        return forwardToJsp;
    }
    
}
