/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Commands.Command;
import Commands.CommandFactory;
import Commands.NoActionSuppliedCommand;
import Commands.NoValidActionCommand;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grahamm
 */
@WebServlet(name = "FrontController", urlPatterns
        = {
            "/FrontController"
        })
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // Process Request - this is a method that will deal with post AND get requests
    // It is called by the doPost and doGet methods (shown below) and has the code
    // for how to deal with any request made by a client
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set up a String to hold the name of the web page the user will see
        // at the end of this request (the name of the page to be viewed)
        String forwardToJsp = null;
        
        // Check what the user is trying to do by checking the action variable
        String action = request.getParameter("action");
        
        // Create a command object to hold what we action we wish to take
        Command command = CommandFactory.createCommand(action);
        //CommandFactory cf = new CommandFactory();
        //Command command = cf.createCommand(action);
        
        // Run the command that was created. Remember, the command created 
        // will depend on the value stored in the action field
        forwardToJsp = command.execute(request, response);
        
        // Send back the name of the page for the user to view
        response.sendRedirect(forwardToJsp);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
