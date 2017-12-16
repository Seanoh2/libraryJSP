/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

import java.util.Date;

/**
 *
 * @author Sean
 */
public class Email {
    
    private int ID;
    private Date Time;
    private User User;
    private String UUID;
    
    /**
     * Used to check emails inserted into the database<p>
     * Main Constructor
     * Allows for easier control of data from DB.
     * 
     * @param ID Used to identify record in the database.
     * @param Time Used to identify when they applied for recovery, as well to check if it is still valid.
     * @param UserID Used to identify what user needs a password recovery.
     * @param UUID Used to verify who is attempting the recovery.
     */
    public Email(int ID, Date Time, User User, String UUID) {
        this.ID = ID;
        this.Time = Time;
        this.User = User;
        this.UUID = UUID;
    }
    
    /**
     * Used to create temp objects that will be pushed into the database.
     * This exists as there is no way to easily find what the id is currently until it is already added to DB.
     * Only use when creating an object that hasn't been in the database already.
     * Allows for easier control of data from DB.
     * 
     * @param Time Used to identify when they applied for recovery, as well to check if it is still valid.
     * @param UserID Used to identify what user needs a password recovery.
     * @param UUID Used to verify who is attempting the recovery.
     */
    public Email(Date Time, User User, String UUID) {
        this.Time = Time;
        this.User = User;
        this.UUID = UUID;
    }

    /**
     * Default Constructor
     * No Info/Parameters.
     */
    public Email() {
    }


    public int getID() {
        return ID;
    }


    public void setID(int ID) {
        this.ID = ID;
    }


    public Date getTime() {
        return Time;
    }


    public void setTime(Date Time) {
        this.Time = Time;
    }


    public User getUser() {
        return User;
    }


    public void setUser(User User) {
        this.User = User;
    }


    public String getUUID() {
        return UUID;
    }


    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
    
    
}
