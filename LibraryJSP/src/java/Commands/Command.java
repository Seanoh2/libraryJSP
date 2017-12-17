/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grahamm
 */
public interface Command {

    /**
     * Used as a basis for all commands for website.
     * Used with command factory to create a basis for all commands to execute and run.
     * @param request Used to grab POST info.
     * @param response Used to create cookies and status of request.
     * @return String of webpage to redirect to.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response);
}
