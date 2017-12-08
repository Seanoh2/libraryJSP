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
public class UpdateTitleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        TitleDAO titleDao = new TitleDAO("librarydatabase");
        Title oldTitle = (Title) session.getAttribute("currentTitle");

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int onLoan = Integer.parseInt(request.getParameter("onLoan"));
        String description = request.getParameter("desc");

        if (name.isEmpty()) {
            name = oldTitle.getNovelName();
        }
        if (author.isEmpty()) {
            author = oldTitle.getAuthor();
        }
        if (description.isEmpty()) {
            description = oldTitle.getTitleDescription();
        }

        if (name != null && !name.equals("")) {
            if (author != null && !author.equals("")) {
                if (description != null && !description.equals("")) {
                    Title updateTitle = new Title(name, author, stock, onLoan, description);
                    titleDao.updateTitle(oldTitle.getTitleID(), updateTitle);

                } else {
                    String errorMessage = "Error with description";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }
            } else {
                String errorMessage = "Error with author";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            String errorMessage = "Error with name";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        //TODO;
        //Figure out how to check blank for integer

        return forwardToJsp;
    }

}
