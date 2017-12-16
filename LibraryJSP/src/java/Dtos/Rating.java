/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

/**
 *
 * @author Tomwozzer
 */
public class Rating {
    
    int ratingID;
    int titleID;
    int rating;

    public Rating(int ratingID, int titleID, int rating){
        this.ratingID = ratingID;
        this.titleID = titleID;
        this.rating = rating;
    }
    
    public Rating(int titleID, int rating) {
        this.titleID = titleID;
        this.rating = rating;
    }

    public Rating() {
    }

    public int getRatingID() {
        return ratingID;
    }

    public int getTitleID() {
        return titleID;
    }

    public int getRating() {
        return rating;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.ratingID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        if (this.ratingID != other.ratingID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rating{" + "ratingID=" + ratingID + ", titleID=" + titleID + ", rating=" + rating + '}';
    }
    
    
    
}
