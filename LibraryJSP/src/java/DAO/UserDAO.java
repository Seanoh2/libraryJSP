/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Password;
import Dtos.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import interfaces.UserDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.MessageDigest;

/**
 *
 * @author Sean
 * @version 1.0
 * @since 14/10/17
 */
public class UserDAO extends DAO implements UserDAOInterface {

    /**
     * Used to create connection to database to manipulate information.
     *
     * @param databaseName Used to identify what database to connect to.
     */
    public UserDAO(String databaseName) {
        super(databaseName);
    }

    /**
     * This will allow the user to call users by their first name and last name
     * Both variables must be exactly right, Case sensitive
     *
     * @param firstName parameter is used to find the user in the db.
     * @param lastName parameter is used to find the user in the db.
     * @return ArrayList with users from the db matching the first and last name
     * parameters
     */
    @Override
    public ArrayList<User> selectUserByUsername(String firstName, String lastName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        ArrayList<User> userList = new ArrayList();

        try {
            conn = getConnection();

            String query = "SELECT * FROM users WHERE firstName = ? AND lastName = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);

            while (rs.next()) {
                u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setCountry(rs.getString("country"));
                u.setAddressLine1(rs.getString("addressLine1"));
                u.setAddressLine2(rs.getString("addressLine2"));
                u.setUserID(rs.getInt("userID"));
                u.setIsAdmin(rs.getInt("isAdmin"));
                userList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectUserByUsername() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the selectUserByUsername() method: " + e.getMessage());
            }
        }
        return userList;
    }

    /**
     * This will allow the user to call users by their name. This will check
     * first and last name, if the substring is inside. Case sensitive.
     *
     * @param name used to find a user if the first name or last name contains
     * this String
     * @return ArrayList with users from the database with a name containing the
     * name parameter
     */
    @Override
    public ArrayList<User> selectUserContainingName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        ArrayList<User> userList = new ArrayList();

        try {
            conn = getConnection();

            String query = "SELECT * FROM users WHERE firstName LIKE ? OR lastName LIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");

            while (rs.next()) {
                u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setCountry(rs.getString("country"));
                u.setAddressLine1(rs.getString("addressLine1"));
                u.setAddressLine2(rs.getString("addressLine2"));
                u.setUserID(rs.getInt("userID"));
                u.setIsAdmin(rs.getInt("isAdmin"));
                userList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectUserContainingName() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the selectUserByUsername() method: " + e.getMessage());
            }
        }
        return userList;
    }

    /**
     * This will check the database for a user with userID of the param and
     * return the user
     *
     * @param userID This Parameter is used to find a user with an id equal to
     * this parameter
     * @return User object which matches the ID of the param
     */
    @Override
    public User findUserByID(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM users WHERE userID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            if (rs.next()) {
                u = new User(rs.getInt("userID"), rs.getString("email"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("country"), rs.getString("addressLine1"), rs.getString("addressLine2"), rs.getInt("isAdmin"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findUserByID() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the findUserByID() method: " + e.getMessage());
            }
        }
        return u;
    }

    /**
     * This method will take input from the console and create a user object.
     *
     * @param u
     * @return boolean indicating if the add was executed or not.
     */
    @Override
    public boolean addUser(User u) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Boolean result = null;

        try {
            String hashedPassword = Password.hashString(u.getPassword());
            conn = getConnection();
            String query = "INSERT INTO users VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, NULL)";
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getEmail());
            ps.setString(2, hashedPassword);
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getCountry());
            ps.setString(6, u.getAddressLine1());
            ps.setString(7, u.getAddressLine2());
            ps.setInt(8, u.getIsAdmin());

            rs = ps.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Constraint Exception occurred: " + e.getMessage());
            // Set the rowsAffected to -1, this can be used as a flag for the display section
            rs = -1;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the addUser() method");
            }
        }
        if (rs > 0) {
            result = true;
        } else {
            result = false;
        }

        return result;

    }

    /**
     * This allows the user to login to the system with their email and
     * password. Email and Password are case sensitive.
     *
     * @param email Used to identify who is logging in.
     * @param password Used to confirm client is this user.
     * @return boolean result to if it was successful.
     */
    @Override
    public User login(String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM users WHERE email = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next() && Password.checkPassword(password, rs.getString("password"))) {
                u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setCountry(rs.getString("country"));
                u.setAddressLine1(rs.getString("addressLine1"));
                u.setAddressLine2(rs.getString("addressLine2"));
                u.setUserID(rs.getInt("userID"));
                u.setIsAdmin(rs.getInt("isAdmin"));
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the login method:");
            System.err.println("\t" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the login method:\n" + e.getMessage());
            }
        }
        return u;
    }

    /**
     * This will allow for a lookup of user in database. Only using single user
     * object as email needs to be unique.
     *
     * @param email Used to find user in database
     * @return User object with info if found.
     */
    @Override
    public User getUserByEmail(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = getConnection();
            String query = "SELECT * FROM users WHERE email = ?";
            ps = con.prepareStatement(query);

            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                // Get the pieces of a customer from the resultset
                user.setUserID(rs.getInt("userID"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setCountry(rs.getString("country"));
                user.setAddressLine1(rs.getString("addressLine1"));
                user.setAddressLine2(rs.getString("addressLine2"));
                user.setIsAdmin(rs.getInt("isAdmin"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserByEmail() method");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the getUserByEmail() method");
            }
        }

        return user;
    }

    /**
     * This will remove a user from the db User to be removed can't be an admin
     *
     * @param id to find user to be removed.
     * @return error code to indicate whether user was removed, user is an admin
     * or user does not exist.
     */
    public int removeUser(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        User temp = null;
        UserDAO userDAO = new UserDAO("librarydatabase");

        temp = userDAO.findUserByID(id);
        if (temp == null) {
            return 3;//user does not exist
        } else if (temp.getIsAdmin() == 1) {
            return 2;//user is an admin
        } else {
            try {
                conn = getConnection();
                String query = "DELETE FROM users WHERE userID = ?";
                ps = conn.prepareStatement(query);

                ps.setInt(1, id);
                rs = ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Exception occured in the getUserByEmail() method");
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        freeConnection(conn);
                    }
                } catch (SQLException e) {
                    System.out.println("Exception occured in the finally section in the removeTitle() method");
                }
            }
            //Indicates success
            return 1;
        }

    }
    
    /**
     * Used to update users personal information.
     *
     * @param attemptUpdate what user needs information updated.
     * @return boolean result to if it was successful.
     */

    @Override
    public boolean updateUser(User attemptUpdate) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Boolean result = null;

        try {
            conn = getConnection();
            String query = "UPDATE users SET email=?, firstName=?, lastName=?, country=?, addressLine1=?, addressLine2 =? WHERE userID=?";
            ps = conn.prepareStatement(query);
            int userID = attemptUpdate.getUserID();
            String email = attemptUpdate.getEmail();
            String firstName = attemptUpdate.getFirstName();
            String lastName = attemptUpdate.getLastName();
            String country = attemptUpdate.getCountry();
            String addressLine1 = attemptUpdate.getAddressLine1();
            String addressLine2 = attemptUpdate.getAddressLine2();

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, email);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, country);
            ps.setString(5, addressLine1);
            ps.setString(6, addressLine2);
            ps.setInt(7, userID);

            // Execute the query
            rs = ps.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Constraint Exception occurred: " + e.getMessage());
            // Set the rowsAffected to -1, this can be used as a flag for the display section
            rs = -1;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } // Now that the program has completed its database access component, 
        // close the open access points (resultset, preparedstatement, connection)
        // Remember to close them in the OPPOSITE ORDER to how they were opened
        // Opening order: Connection -> PreparedStatement -> ResultSet
        // Closing order: ResultSet -> PreparedStatement -> Connection
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the updateUser() method");
            }
        }

        if (rs > 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Used to update a users password once request has been validated. Password
     * is hashed than added to ensure security.
     *
     * @param password Users new password.
     * @param user Used to identify what user needs password need updating.
     * @return boolean of whether result was complete or not.
     */
    public boolean updatePassword(String password, User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        EmailDAO emailDao = new EmailDAO("librarydatabase");
        int rs = 0;
        Boolean result = false;

        try {
            conn = getConnection();
            String query = "UPDATE users SET password=? WHERE userID=?";
            ps = conn.prepareStatement(query);
            String hashedPassword = Password.hashString(password);

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, hashedPassword);
            ps.setInt(2, user.getUserID());

            // Execute the query
            rs = ps.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Constraint Exception occurred: " + e.getMessage());
            // Set the rowsAffected to -1, this can be used as a flag for the display section
            rs = -1;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } // Now that the program has completed its database access component, 
        // close the open access points (resultset, preparedstatement, connection)
        // Remember to close them in the OPPOSITE ORDER to how they were opened
        // Opening order: Connection -> PreparedStatement -> ResultSet
        // Closing order: ResultSet -> PreparedStatement -> Connection
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the updatePassword() method");
            }
        }

        if (rs > 0) {
            result = true;
            emailDao.removeRecovereyLog(user.getUserID());
        } else {
            result = false;
        }

        return result;

    }
}
