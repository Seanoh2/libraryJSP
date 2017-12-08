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
public class AddTitleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        int stock = Integer.parseInt(request.getParameter("stock"));

        if (name != null && !name.isEmpty()) {
            if (author != null && !author.isEmpty()) {
                if (description != null && !author.isEmpty()) {
                    Title newTitle = new Title(name, author, 0, 0, description);
                    titleDao.addTitle(newTitle);
                } else {
                    String errorMessage = "No description supplied";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }
            } else {
                String errorMessage = "No author supplied";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        } else {
            String errorMessage = "No name supplied";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
