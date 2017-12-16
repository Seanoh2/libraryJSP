/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.ratingDAO;
import Dtos.Rating;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tomwozzer
 */
public class rateCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        String forwardToJsp = "";
        HttpSession session = request.getSession();
        
        ratingDAO ratingdao = new ratingDAO("librarydatabase");
       
        int titleID = Integer.parseInt(request.getParameter("titleID"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        
            if (titleID !=0){
                if(rating != 0){
                    Rating r1 = new Rating(titleID, rating);
                    ratingdao.addReview(r1);
                    forwardToJsp = "ratingSuccess.jsp";
                } else {
                    String errorMessage = "no rating supplied";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "error.jsp";
                }
            } else {
                String errorMessage = "no titleId supplied";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

        return forwardToJsp;
    }
    
}
