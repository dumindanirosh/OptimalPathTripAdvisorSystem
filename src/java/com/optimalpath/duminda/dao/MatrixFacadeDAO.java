/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.MatrixDetails;
import java.util.List;


/**
 *
 * @author Duminda
 */

public interface MatrixFacadeDAO {

    public List<MatrixDetails> getAllLocationsPathsForLocationID1(int id1);

    public MatrixDetails createMatrix(MatrixDetails details);

    public MatrixDetails getMatrixDetails(int location1, int location2);
    
}
