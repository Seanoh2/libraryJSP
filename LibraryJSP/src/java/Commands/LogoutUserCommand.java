/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Seanoh
 */
public class LogoutUserCommand implements Command {

    /**
     * Simple command to logout user.
     * Session variable for user is removed and user is redirected to login.
     * 
     * 
     * @param request Used to grab POST data and information of session.
     * @param response Not used in the method but can be used to set cookies.
     * @return String of webpage to redirect to.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "login.jsp";
        HttpSession session = request.getSession();
        
        session.removeAttribute("user");
        return forwardToJsp;
    }
    
}
