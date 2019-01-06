/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.GoogleMapLocation;
import com.optimalpath.duminda.model.Matrix;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.MatrixDetails;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Duminda
 */

public class MatrixDAOImpl implements MatrixFacadeDAO {

    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    public MatrixDAOImpl() {

      entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        em = entityManagerFactory.createEntityManager();

      

    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<MatrixDetails> copyMatrixToDetails(List<Matrix> matrixtList) {
        List<MatrixDetails> matrixDetailsList = new ArrayList<MatrixDetails>();
        Iterator<Matrix> i = matrixtList.iterator();
        while (i.hasNext()) {
            Matrix matrix = (Matrix) i.next();

            MatrixDetails reviewDetails = new MatrixDetails(matrix.getMatrixId(),
                    matrix.getDistance(), matrix.getTime(),
                    matrix.getGooglemapLocationId1().getGoogleMapLocationId(), matrix.getGooglemapLocationId2().getGoogleMapLocationId());
            matrixDetailsList.add(reviewDetails);
        }
        return matrixDetailsList;
    }

    private MatrixDetails copyMatrixToDetails(Matrix location) {
        MatrixDetails details = new MatrixDetails(location.getMatrixId(),
                location.getDistance(),
                location.getTime(), location.getGooglemapLocationId1().getGoogleMapLocationId(),
                location.getGooglemapLocationId2().getGoogleMapLocationId());
        return details;
    }

    @Override
    public List<MatrixDetails> getAllLocationsPathsForLocationID1(int id1) {
        List<Matrix> matrixList = null;
        try {
            matrixList = (List<Matrix>) em.createNamedQuery(
                    "Matrix.findByLocationID1")
                    .setParameter("googlemapLocationId1", new GoogleMapLocation(id1))
                    .getResultList();

            return copyMatrixToDetails(matrixList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public MatrixDetails createMatrix(MatrixDetails details) {
        try {

            Matrix matrix = new Matrix(details.getDistance(), details.getTime());
            GoogleMapLocation location1 = em.find(GoogleMapLocation.class, details.getLocationId1());
            GoogleMapLocation location2 = em.find(GoogleMapLocation.class, details.getLocationId2());
            matrix.setGooglemapLocationId1(location1);
            matrix.setGooglemapLocationId2(location2);
            em.persist(matrix);
            em.flush();
            details.setMatrixId(matrix.getMatrixId());
            return details;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public MatrixDetails getMatrixDetails(int location1, int location2) {
        Matrix matrixList = null;
        try {
            matrixList = (Matrix) em.createNamedQuery("Matrix.findByMatrix")
                    .setParameter("googlemapLocationId1", new GoogleMapLocation(location1))
                    .setParameter("googlemapLocationId2", new GoogleMapLocation(location2))
                    .getSingleResult();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return copyMatrixToDetails(matrixList);
    }
}
