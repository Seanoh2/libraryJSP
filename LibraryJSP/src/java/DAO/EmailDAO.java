/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Password;
import Dtos.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
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

    public EmailDAO(String databaseName) {
        super(databaseName);
    }

    final String username = "RecoverySWP@gmail.com";
    final String password = "WPService";

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
            message.setText("Recovery Link: http://localhost:8084/LibraryJSP/resetPassword.jsp?" + id.toString());

            Transport.send(message);

            if (this.CreateRecoveryLog(id, email)) {
                result = true;
                Transport.send(message);
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public boolean CreateRecoveryLog(UUID id, String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        UserDAO userDao = new UserDAO("librarydatabase");
        int userID = userDao.getUserByEmail(email).getUserID();
        String hashID = Password.hashString(id.toString());
        Boolean result = null;
        int rs = 0;

        try {
            conn = getConnection();

            String query = "INSERT INTO passwordrecovery VALUES (NULL,CURRENT_TIMESTAMP(),?,?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setString(2, hashID);

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

    public User checkUUID(String userUUID, int id) {
        User result = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDAO userDao = new UserDAO("librarydatabase");

        Date time = null;
        User tempUser = new User();
        String hashedUUID = "";

        try {
            conn = getConnection();

            String query = "SELECT * FROM passwordrecovery WHERE UUID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                time = rs.getDate("Time");
                tempUser = userDao.findUserByID(rs.getInt("UserID"));
                hashedUUID = rs.getString("UUID");
                
            }
        
       if(Password.checkPassword(userUUID, hashedUUID)) {
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(time);
           calendar.add(Calendar.DAY_OF_YEAR, 7);
           
           if(calendar.getTime().compareTo(time)>0) {
               result = tempUser;
           }
       }
            
            

        } catch (SQLException e) {
            System.out.println("Exception occured in the checkUUID() method: " + e.getMessage());
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

        return result;
    }
    
    
}
