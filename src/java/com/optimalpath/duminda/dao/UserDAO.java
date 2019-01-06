/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.User;
import com.optimalpath.duminda.util.UserDetails;
import java.util.List;
import javax.persistence.NoResultException;


/**
 *
 * @author Duminda
 */

public interface UserDAO {

    public String addUser(User user) throws Exception ;
    
    public User systemLogin(String username, String password)throws Exception,NoResultException;

    public UserDetails checkUserNameAvailability(String username);

    

    public void editUser(UserDetails details);

    public void editUserPassword(UserDetails details);

    public boolean removeUser(String username);

    public List<UserDetails> getAllUsers();

    public List<UserDetails> getUsersByUsertype(String userType);



    
}
