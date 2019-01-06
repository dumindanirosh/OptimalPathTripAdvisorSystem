/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.LocationImage;
import com.optimalpath.duminda.model.LocationImagePK;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.LocationImageDetails;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Duminda
 */

public class LocationImageDAOImpl implements LocationImageDAO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    public LocationImageDAOImpl() {

      entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        em = entityManagerFactory.createEntityManager();

      

    }

    public void persist(Object object) {
        em.persist(object);
    }

    
       private List<LocationImageDetails> copyLocationImagesToDetails(List<LocationImage> lcs) {
        List<LocationImageDetails> imageList = new ArrayList<LocationImageDetails>();
        Iterator<LocationImage> i = lcs.iterator();
        while (i.hasNext()) {
            LocationImage locationImage = (LocationImage) i.next();
            LocationImageDetails details = new LocationImageDetails(locationImage.getLocationImageName(),
                    locationImage.getLocationImageUrl());
                    locationImage.getLocationImagePK();
                
            details.setLocationId(locationImage.getLocationImagePK().getLocationId());
            details.setLocationId(locationImage.getLocationImagePK().getLocationImageId());
            
            imageList.add(details);
        }
        return imageList;
    }
       

    
    @Override
    public LocationImageDetails createLocationImage(LocationImageDetails details) {
        try {

            LocationImage locationImage = new LocationImage(details.getLocationImageName(), details.getLocationImageUrl());
            int locationId = details.getLocationId();
            LocationImagePK pk = new LocationImagePK(locationId);
            locationImage.setLocationImagePK(pk);
            em.persist(locationImage);
            return details;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    
      @Override
    public List<LocationImageDetails> getAllImagesForLocation(int locationId) {
       List<LocationImage>  locationImages= null;
        try {      
            Query query = em.createNamedQuery("LocationImage.findByLocationId");
            query.setParameter("locationId", locationId );
            locationImages = (List<LocationImage>)query.getResultList();
                   
            return copyLocationImagesToDetails(locationImages);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }
}