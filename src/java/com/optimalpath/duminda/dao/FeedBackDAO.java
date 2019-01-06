/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.util.ContactUsDetails;
import com.optimalpath.duminda.util.ReviewDetails;
import java.util.List;


/**
 *
 * @author Duminda
 */

public interface FeedBackDAO {

    public void createReview(ReviewDetails reviewDetails);

    public void createContactUs(ContactUsDetails contactUsDetails);

    public List<ContactUsDetails> getAllInquiries();

    public List<ReviewDetails> getReviewsByLocationID(int locationId);
    
}
