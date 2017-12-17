package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sean
 */
public class DAO {

    private String databaseName;

    /**
     *
     * @param databaseName
     */
    public DAO(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Used to create a connection to the database, so that database information
     * can be manipulated. Constructor requires database name so that you can
     * connect to other databases if needed.
     *
     * @return Connection to database.
     */
    public Connection getConnection() {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex1) {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        } catch (SQLException ex2) {
            System.out.println("Connection failed " + ex2.getMessage());
            System.exit(2);
        }
        return con;
    }

    /**
     * Used to close and disconnect database connction if connection is found.
     *
     * @param con Used to identify what connection to close.
     */
    public void freeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }

}
