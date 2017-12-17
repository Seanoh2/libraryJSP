/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.ratingDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tom Warren
 */
public class getAverageRatingCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        
        ratingDAO ratingdao = new ratingDAO("librarydatabase");
        
        int titleID = Integer.parseInt(request.getParameter("titleID"));
        
        if (titleID !=0){
            double rating = Double.parseDouble(ratingdao.getAverageRatingByID(titleID));
            session.setAttribute("rating", rating);
            forwardToJsp = "ratingDisplay.jsp";
        } else {
            String errorMessage = "no titleId supplied";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
        
    }
}