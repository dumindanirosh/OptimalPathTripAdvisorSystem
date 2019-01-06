/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.dao;

import com.optimalpath.duminda.model.User;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.UserDetails;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <h1> UserDAOImpl class</h1>
 * This program developed to handle CRUD operations of User Object. addUser(),
 * updateUser(), deleteUser() and user search operations are included in this
 * class.
 * <p>
 * <b>Note:</b> Database connections and transaction are done Java Persistence
 * API </p>
 *
 * @author Duminda Hettiarachchi
 * @since 2014-11-14
 */
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public UserDAOImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory(BackendConstants.PERSISTENT_UNIT_NAME);

        entityManager = entityManagerFactory.createEntityManager();

    }

    public void persist(Object object) {
        entityManager.persist(object);
    }

    private List<UserDetails> copyUsersToDetails(List<User> userList) {
        List<UserDetails> userDetailList = new ArrayList<UserDetails>();
        Iterator<User> i = userList.iterator();
        while (i.hasNext()) {
            User user = (User) i.next();
            UserDetails details = new UserDetails(user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getEmailAddress(), user.getUserType(), user.getEnteredDate(), user.getPassword());
            userDetailList.add(details);
        }
        return userDetailList;
    }

    private UserDetails copyUsersToDetails(User user) {
        UserDetails details = new UserDetails(user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getEmailAddress(), user.getUserType(), user.getEnteredDate(),
                user.getPassword());
        return details;
    }

    /**
     * addUser method used to add user data to user table.
     *
     * @param user
     * @return String - status of addUser.
     * @throws java.lang.Exception
     */
    @Override
    public String addUser(User user) throws Exception {

        EntityTransaction transaction = null;

        try {

            if (entityManager != null) {

                UserDetails availabilityStatus = checkUserNameAvailability(user.getUsername());
                if (availabilityStatus == null) {

                    if (transaction == null) {
                        transaction = entityManager.getTransaction();
                    }
                    if (!transaction.isActive()) {
                        transaction.begin();
                    }

                    entityManager.persist(user);
                    entityManager.flush();
                    transaction.commit();

                    return BackendConstants.SUCEESS;

                } else {
                    return BackendConstants.EXIST;
                }
            } else {
                return BackendConstants.EXIST;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw ex;
        }

    }

    @Override
    public UserDetails checkUserNameAvailability(String username) {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("User.findByUsername")
                    .setParameter("username", username).getSingleResult();
            return copyUsersToDetails(user);
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * systemLogin method used to authenticate user login data.
     *
     * @param username, password
     * @return User object.
     * @throws java.lang.Exception
     */
    @Override
    public User systemLogin(String username, String password) throws Exception, NoResultException {

        User user = null;

        if (entityManager != null) {

            try {
                
                Query query = entityManager.createNamedQuery("User.findByUsernameAndPassword");
                query.setParameter("username", username);
                query.setParameter("password", password);

                user = (User) query.getSingleResult();
                
                return user;

            } catch (NoResultException ex) {
                throw ex;
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            return null;
        }

    }

    @Override
    public void editUser(UserDetails details) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, details.getUsername());
            user.setFirstName(details.getFirstName());
            user.setLastName(details.getLastName());
            user.setEmailAddress(details.getEmailAddress());
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void editUserPassword(UserDetails details) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, details.getUsername());
            user.setPassword(details.getPassword());
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean removeUser(String username) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, username);
            if (user != null) {
                entityManager.remove(user);
                entityManager.getTransaction().commit();
                return true;
            } else {
                entityManager.getTransaction().commit();
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserDetails> getAllUsers() {
        List<User> userList = null;
        try {
            userList = (List<User>) entityManager.createNamedQuery("User.findAll").getResultList();
            return copyUsersToDetails(userList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDetails> getUsersByUsertype(String userType) {
        List<User> userList = null;
        try {
            userList = (List<User>) entityManager.createNamedQuery("User.findByUserType").setParameter("userType", userType)
                    .getResultList();
            return copyUsersToDetails(userList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
