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
 * @author grahamm
 */
public class NoValidActionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // There was no action to be taken, so set the page to be viewed to the error page
        String forwardToJsp = "error.jsp";
        // Get the session so we can add information to it
        HttpSession session = request.getSession();
        // Add an error message to the session to be displayed on the error page
        // This lets us inform the user about what went wrong
        session.setAttribute("errorMessage", "No valid action was supplied");
        return forwardToJsp;
    }

}
