/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Email;
import Dtos.Password;
import Dtos.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Sean
 */
public class EmailDAO extends DAO {

    /**
     * Used to create connection to database to manipulate information.
     *
     * @param databaseName Used to identify what database to connect to.
     */
    public EmailDAO(String databaseName) {
        super(databaseName);
    }

    final String username = "RecoverySWP@gmail.com";
    final String password = "WPService";

    /**
     * Used to send recovery email to user to reset password. If valid, UUID is
     * generated and sent with email to identify and verify user. Log is also
     * created in database to record this. Otherwise, result is set to false to
     * indicate failure.
     *
     * @param email Used to identify who is requesting information
     * @return
     */
    public boolean sendEmailRecovery(String email) {
        boolean result = false;
        UUID id = UUID.randomUUID();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("RecoverySWP@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Password Recovery");
            message.setText("Recovery Link: http://localhost:8084/LibraryJSP/resetPassword.jsp?ID=" + id.toString());

            if (this.CreateRecoveryLog(id, email)) {
                result = true;
                Transport.send(message);
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Used to create a record of recovery request in database. This is done to
     * verify user and to set and time limit on request. If valid, UUID is
     * encrypted so that if DB is compromised, user information is not breached
     * by log. Otherwise, result set to false to indicate failure.
     *
     * @param id Generated UUID to verify user.
     * @param email Used to retrieve UserID and save to log.
     * @return boolean of result of recovery log
     */
    public boolean CreateRecoveryLog(UUID id, String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        UserDAO userDao = new UserDAO("librarydatabase");
        int userID = userDao.getUserByEmail(email).getUserID();
        Boolean result = null;
        int rs = 0;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(id.toString().getBytes());
            String encryptedString = new String(messageDigest.digest());

            conn = getConnection();

            String query = "INSERT INTO passwordrecovery VALUES (NULL,CURRENT_TIMESTAMP(),?,?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setString(2, encryptedString);

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
                System.out.println("Exception occured in the finally section in the CreateRecoveryLog() method");
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
     * Used to remove recovery log once reset of password is complete and sent.
     * This is done to clear space of DB and to increase security.
     *
     * @param userID Identify what record needs to be deleted.
     * @return boolean of if removal was successful.
     */
    public boolean removeRecovereyLog(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        Boolean result = null;
        int rs = 0;

        try {
            conn = getConnection();

            String query = "DELETE FROM passwordrecovery WHERE UserID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
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
                System.out.println("Exception occured in the finally section in the removeRecovereyLog() method");
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
     * Used to check if recovery attempt is still valid. UUID is checked, then
     * date is check to see if still within week of request. If valid, User
     * details are returned.
     * Otherwise, Null user is returned.
     *
     * @param userUUID Used to check what record and if valid.
     * @return User object.
     */
    public User checkUUID(String userUUID) {
        User result = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDAO userDao = new UserDAO("librarydatabase");

        Email check = new Email();

        try {
            conn = getConnection();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(userUUID.getBytes());
            String encryptedString = new String(messageDigest.digest());

            String query = "SELECT * FROM passwordrecovery WHERE UUID = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, encryptedString);
            rs = ps.executeQuery();

            if (rs.next()) {
                check.setTime(rs.getDate("Time"));
                check.setUser(userDao.findUserByID(rs.getInt("UserID")));
                check.setUUID(rs.getString("UUID"));
                check.setID(rs.getInt("ID"));

            }

            if (check != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(check.getTime());
                calendar.add(Calendar.DAY_OF_YEAR, 7);

                if (calendar.getTime().compareTo(check.getTime()) > 0) {
                    result = check.getUser();
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the checkUUID() method: " + e.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                System.out.println("Exception occured in the finally section of the checkUUID() method: " + e.getMessage());
            }
        }

        return result;
    }

}
