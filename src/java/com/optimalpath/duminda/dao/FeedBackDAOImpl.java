/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.ReviewDetails;
import com.optimalpath.duminda.util.ContactUsDetails;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.model.LocationReview;
import com.optimalpath.duminda.model.ContactUs;
import com.optimalpath.duminda.model.Location;
import com.optimalpath.duminda.model.User;

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

public class FeedBackDAOImpl implements FeedBackDAO {

       private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    public FeedBackDAOImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        em = entityManagerFactory.createEntityManager();

    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private List<ReviewDetails> copyReviewsToDetails(List<LocationReview> locationReviewList) {
        List<ReviewDetails> reviewDetailsList = new ArrayList<ReviewDetails>();
        Iterator<LocationReview> i = locationReviewList.iterator();
        while (i.hasNext()) {
            LocationReview locationReview = (LocationReview) i.next();
            ReviewDetails reviewDetails = new ReviewDetails(locationReview.getLocationReviewId(),
                    locationReview.getComment(), locationReview.getLocationId().getLocationId(),
                    locationReview.getUsername().getUsername());
            reviewDetailsList.add(reviewDetails);
        }
        return reviewDetailsList;
    }


    private List<ContactUsDetails> copyContactUsToDetails(List< ContactUs> contactUsList) {
        List<ContactUsDetails> contactUsDetailsList = new ArrayList<ContactUsDetails>();
        Iterator<ContactUs> i = contactUsList.iterator();
        while (i.hasNext()) {
            ContactUs contactUs = (ContactUs) i.next();
            ContactUsDetails contactUsDetails = new ContactUsDetails(contactUs.getContactUsId(), contactUs.getName(),
                    contactUs.getEmail(), contactUs.getContactNo(), contactUs.getMessage());
            contactUsDetailsList.add(contactUsDetails);
        }
        return contactUsDetailsList;
    }

    @Override
    public void createReview(ReviewDetails reviewDetails) {
        try {
            LocationReview locationReview = new LocationReview(reviewDetails.getComment());
            Location location = em.find(Location.class, reviewDetails.getLocationId());
            User user = em.find(User.class, reviewDetails.getUsername());
            locationReview.setLocationId(location);
            locationReview.setUsername(user);
            em.persist(locationReview);
        } catch (Exception ex) {
         ex.printStackTrace();
        }
    }

    @Override
    public void createContactUs(ContactUsDetails contactUsDetails) {
        try {
            ContactUs contactUs = new ContactUs(contactUsDetails.getName(), contactUsDetails.getEmail(),
                    contactUsDetails.getContactNo(),contactUsDetails.getMessage());
            em.persist(contactUs);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public List<ContactUsDetails> getAllInquiries() {
        List<ContactUs> contactUs = null;
        try {
            contactUs = (List<ContactUs>) em.createNamedQuery(
                    "ContactUs.findAll")
                    .getResultList();
            return copyContactUsToDetails(contactUs);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        return null;
    }
    
    
    
    @Override
    public List<ReviewDetails> getReviewsByLocationID(int locationId) {
        List<LocationReview> location = null;
        try {
            location = (List<LocationReview>) em.createNamedQuery(
                    "LocationReview.findByReviewByLocationId")
                    .setParameter("locationId", new Location(locationId))
                    .getResultList();
            return copyReviewsToDetails(location);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return null;
    }
}
