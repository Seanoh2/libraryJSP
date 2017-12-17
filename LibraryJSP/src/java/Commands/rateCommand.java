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
        
        int titleID  = Integer.parseInt(request.getParameter("titleID"));
        
        int rating = Integer.parseInt(request.getParameter("rating"));
        
        //Rating r1 = (Rating)session.getAttribute("r1");
        
        //int titleID = r1.getTitleID();
        //int rating = r1.getRating();
        
        Rating r1 = new Rating(titleID, rating);
        
            if (titleID !=0){
                if(rating != 0){
                    ratingdao.addReview(r1);
                    session.setAttribute("message", "Thank you for your review");
                    forwardToJsp = "myHome.jsp";
                    
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
