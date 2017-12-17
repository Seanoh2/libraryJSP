/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Rating;
import interfaces.ratingDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tomwozzer
 */

public class ratingDAO extends DAO implements ratingDAOInterface{
    
    public ratingDAO(String databaseName) {
        super(databaseName);
    }
    @Override
    public double getAverageRatingByID(int id) {
    
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Rating r1 = null;
        ArrayList<Rating> ratings = new ArrayList();
        try {
            con = getConnection();

            String query = "SELECT * from ratings where titleID = ?";
            ps.setInt(1, id);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()){
                r1 = new Rating(rs.getInt("ratingID"), rs.getInt("titleID"), rs.getInt("rating"));
                ratings.add(r1);
            }
        }
        
        catch (SQLException e) {
            System.out.println("Exception occured in the viewAverageRatingByID() method: " + e.getMessage());
        
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
                System.out.println("Exception occured in the finally section in the viewAverageRatingByID() method");
            }
        }

        double rating = 0;
        
        for (int i = 0; i < ratings.size(); i++){
            rating = rating + ratings.get(i).getRating();
        }
        
        rating = rating / ratings.size();
        
        return rating;
    }
        
    @Override
    public boolean addReview(Rating r1){
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        
        try{
            conn = getConnection();
            String query = "INSERT INTO ratings VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, r1.getRatingID());
            ps.setInt(2, r1.getTitleID());
            ps.setInt(3, r1.getRating());
            rs = ps.executeUpdate();
            
            if (rs > 0) {
                System.out.println("Rating added successfully");
                return true;
            } else {
                System.out.println("Rating not added: error");
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Exception occured in the addRating() method: " + e.getMessage());
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addRating() method");
                e.getMessage();
            }
    
        }
        
        return true;
    }
    
}