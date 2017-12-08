/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Dtos.TitleGenre;
import java.util.ArrayList;

/**
 *
 * @author Sean
 */
public interface TitleGenreDAOInterface {

    /**
     * This will return a list of TitleGenre objects by the titleID.<p>
     * TitleGenre objects store Title and Genre objects.
     * @param titleID Used to find relevant genres.
     * @return ArrayList of TitleGenre objects.
     */
    public ArrayList<TitleGenre> getGenreByTitleID(int titleID);
}
