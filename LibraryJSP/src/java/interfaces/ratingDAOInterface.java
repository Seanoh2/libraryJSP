/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Dtos.Rating;
import java.util.ArrayList;

/**
 *
 * @author Tomwozzer
 */
public interface ratingDAOInterface {
    public double getAverageRatingByID(int id);
    public boolean addReview(Rating r1);
}
