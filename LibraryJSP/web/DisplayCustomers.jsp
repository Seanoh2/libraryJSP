<%@page import="java.util.ArrayList"%>
<%@page import="Dtos.Customer"%>
<%@page import="Daos.CustomerDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display All Customers</title>
    </head>
    <body>
        <a href="index.html">Back to index</a>
        <h1>Customer list</h1>
        <%
            // Get the Customer list from the session
            ArrayList<Customer> customers = (ArrayList<Customer>) session.getAttribute("customerList");
            // If there is a customer list to use in the session (and it's not empty)
            // Carrying out this check avoids the page breaking when the session times out
            
            //Hi im tom
            if (customers != null && !customers.isEmpty())
            {
        %>
        <!-- set up table structure -->
        <table>
            <tr>
                <th>Number</th>
                <th>Name</th>
                <th>Last Name</th>
                <th>First Name</th>
            </tr>
            <%
                // Loop to print out all of the rows
                for (Customer c : customers)
                {
            %>
            <!-- Create a row for this customer -->
            <tr>
                <!-- Create a cell for each component of this customer's information and fill it with 
                     data in this customer's object -->
                <td><%=c.getCustomerNumber()%></td>
                <td><%=c.getCustomerName()%></td>
                <td><%=c.getContactLastName()%></td>
                <td><%=c.getContactFirstName()%></td>
            </tr>
            <%
                    // Close the loop
                }
            } else
            {
                out.println("No customers found. Please try again.");
            }
            %>
        </table>
    </body>
</html>
