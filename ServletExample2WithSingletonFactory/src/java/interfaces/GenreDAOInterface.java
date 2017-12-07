/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Dtos.Genre;

/**
 * GenreDAOInterface is used to link genres to titles.<p>
 * This allows for multiable genres for a single title.
 * @author Sean
 */
public interface GenreDAOInterface {
    
    /**
     * This will return a Genre object by their ID.<p>
     * Single Genre object.
     * @param genreID This will be used to find what genre is needed.
     * @return A Genre object from database.
     */
    public Genre getGenreByID(int genreID);
}
