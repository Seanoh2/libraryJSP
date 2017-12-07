
<%@page import="java.util.ArrayList"%>
<%@page import="Dtos.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results of Update</title>
    </head>
    <body>
        <%
            // Get the result from the session (how many customers were updated)
            Object resultValue = session.getAttribute("numUpdated");
            // If we have a result value to use, then display it
            if (resultValue != null)
            {
                // Cast it to a number
                Integer result = (Integer) resultValue;
        %> 
        <!-- display the number of customers updated -->
        There were <%=result%> Customers updated in the database.

        <%
            // Check if there were customers updated. If so, get the list of Customers and display them
            if (result > 0)
            {
                // Get the list of updated customers
                ArrayList<Customer> updatedCustomers = (ArrayList<Customer>) session.getAttribute("updatedCustomers");
                // If there is a list of updated customers to use
                if (updatedCustomers != null)
                {
                    // Deal with displaying
        %>
        <table>
            <tr>
                <th>Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Credit Limit</th>
            </tr>
            <%
                for (Customer c : updatedCustomers)
                {
            %>
            <!-- Create a row for this customer -->
            <tr>
                <!-- Create a cell for each component of this customer's information and fill it with 
                     data in this customer's object -->
                <td><%=c.getCustomerName()%></td>
                <td><%=c.getContactFirstName()%></td>
                <td><%=c.getContactLastName()%></td>
                <td><%=c.getCreditLimit()%></td>
            </tr>
            <%
                    }
                    // We have finished with the list of updated customers list 
                    // so now we can remove the value from the session
                    session.removeAttribute("updatedCustomers");
                }
            %>
        </table>

        <%
            }
            // We have finished with the numUpdated value 
            // so now we can remove the value from the session
            session.removeAttribute("numUpdated");
        } else
        {
        %>
        No customer update information found.
        <%
            }
        %>
        
        <div><a href="index.html">Back to index</a></div>
    </body>
</html>
