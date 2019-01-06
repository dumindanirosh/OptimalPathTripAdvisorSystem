/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.LocationCategory;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.LocationCategoryDetails;
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
public class LocationCategoryDAOImpl implements LocationCategoryDAO {

    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    public LocationCategoryDAOImpl() {

         entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        em = entityManagerFactory.createEntityManager();

    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<LocationCategoryDetails> copyLocationCategoriesToDetails(List<LocationCategory> LocationCategoryList) {
        List<LocationCategoryDetails> locationCategoryDetailList = new ArrayList<LocationCategoryDetails>();
        Iterator<LocationCategory> i = LocationCategoryList.iterator();
        while (i.hasNext()) {
            LocationCategory locationCategory = (LocationCategory) i.next();
            LocationCategoryDetails details = new LocationCategoryDetails(locationCategory.getLocationCategoryId(),
                    locationCategory.getLocationCategoryType());
            locationCategoryDetailList.add(details);
        }
        return locationCategoryDetailList;
    }

    private LocationCategoryDetails copyLocationCategoryToDetails(LocationCategory locationCategory) {
        LocationCategoryDetails details = new LocationCategoryDetails(locationCategory.getLocationCategoryId(),
                locationCategory.getLocationCategoryType());
        return details;
    }

    @Override
    public void createLocationCategory(LocationCategoryDetails locationCategoryDetails) {
        try {
            LocationCategory locationCategory = new LocationCategory(locationCategoryDetails.getLocationCategoryType());
            em.persist(locationCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void editLocationCategory(LocationCategoryDetails locationCategoryDetails) {
        try {
            LocationCategory locationCategory = new LocationCategory(locationCategoryDetails.getLocationCategoryId(),
                    locationCategoryDetails.getLocationCategoryType());
            em.merge(locationCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean removeLocationCategory(int locationCategoryID) {
        try {
            LocationCategory locationCategory = em.find(LocationCategory.class, locationCategoryID);
            if (locationCategory != null) {
                em.remove(locationCategory);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LocationCategoryDetails> getAllLocationCategories() {
        List<LocationCategory> locationCategoriesList = null;
        try {
            locationCategoriesList = (List<LocationCategory>) em.createNamedQuery("LocationCategory.findAll").getResultList();
            return copyLocationCategoriesToDetails(locationCategoriesList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public LocationCategoryDetails getLocationCategoryNameIdByLocationID(int locationCategoryId) {
        LocationCategory locationCategoryList = null;
        try {
            locationCategoryList = (LocationCategory) em.createNamedQuery(
                    "LocationCategory.findByLocationCategoryId")
                    .setParameter("locationCategoryId", locationCategoryId)
                    .getSingleResult();
            return copyLocationCategoryToDetails(locationCategoryList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
