/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.controller;

import com.optimalpath.duminda.dao.UserDAOImpl;
import com.optimalpath.duminda.dao.UserDAO;
import com.optimalpath.duminda.model.User;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.FrontEndConstants;
import com.optimalpath.duminda.util.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Duminda
 */
@WebServlet(name = "UserControlServlet", urlPatterns = {"/UserControlServlet"})
public class UserControlServlet extends HttpServlet {

    private UserDAO userDAO;
    RequestDispatcher requestDispatcher = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // Intialize UserDAO object
        userDAO = new UserDAOImpl();
        try {

            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("adduser")) {

                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String emailAddress = request.getParameter("emailAddress");
                String userType = request.getParameter("userType");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                String agreement = request.getParameter("agreement");

                boolean isValidated = true;
                if (firstName == null || firstName.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (lastName == null || lastName.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (emailAddress == null || emailAddress.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (userType == null || userType.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (username == null || username.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (password == null || password.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (confirmPassword == null || confirmPassword.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (!password.equals(confirmPassword)) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.PASSWORDS_ENTERED_MISMATCH);
                    isValidated = false;

                } else if (agreement == null || agreement.equals("null")) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.AGREEMENT);
                    isValidated = false;
                }
                if (isValidated) {
                    Date enteredDate = new Date();
                    // Intialize the Model Object
                    User newUser = new User(username, firstName, lastName, emailAddress, userType,
                            enteredDate, password);
                    String userCreatedStatus;
                    try {
                        // Execute the addUser method
                        userCreatedStatus = userDAO.addUser(newUser);

                        if (userCreatedStatus.equals(BackendConstants.SUCEESS)) {

                            request.setAttribute(BackendConstants.MESSAGE, firstName + " " + lastName + " registered sucessfully!");
                            requestDispatcher = request.getRequestDispatcher("Confirmation.jsp");
                            requestDispatcher.forward(request, response);

                        } else if (userCreatedStatus.equals(BackendConstants.EXIST)) {

                            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.USRNAME_EXISTING);
                            requestDispatcher = request.getRequestDispatcher("/user/adduser.jsp");
                            requestDispatcher.forward(request, response);

                        } else  {

                            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.ERROR_MESSAGE);
                            requestDispatcher = request.getRequestDispatcher("/user/adduser.jsp");
                            requestDispatcher.forward(request, response);

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {
                    requestDispatcher = request.getRequestDispatcher("/user/adduser.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("login")) { // Home Page login button

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                boolean isValidated = true;
                if (username == null || username.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.USRNAME_REQUIRED);
                    isValidated = false;
                } else if (password == null || password.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.PASSWROD_REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    User user;
                    try {
                        user = userDAO.systemLogin(username, password);

                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
//                        String locationId = request.getParameter("locationId");
//                        if (locationId == null) {

                        if (user.getUserType().equals("Admin")) {
                            requestDispatcher = request.getRequestDispatcher("/user/admindashboard.jsp");
                            requestDispatcher.forward(request, response);
                        } else if (user.getUserType().equals("Member")) {
                            requestDispatcher = request.getRequestDispatcher("/user/memberdashboard.jsp");
                            requestDispatcher.forward(request, response);
                        }
//                        } else {
//                            request.setAttribute("locationId", locationId);
//                            requestDispatcher = request.getRequestDispatcher("/TourPlanListControlServlet?action=checkexisitngfrommap");
//                            requestDispatcher.forward(request, response);
//                        }

                    } catch (NoResultException ex) {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.INVALID_LOGIN);
                        requestDispatcher = request.getRequestDispatcher("/user/login.jsp");
                        requestDispatcher.forward(request, response);
                    } catch (Exception ex) {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.ERROR_MESSAGE);
                        requestDispatcher = request.getRequestDispatcher("/user/login.jsp");
                        requestDispatcher.forward(request, response);
                    }

                } else {
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("logout")) { // Logout Submit from Member and Admin Dashboard

                HttpSession session = request.getSession();
                session.removeAttribute("user");
                request.setAttribute(BackendConstants.MESSAGE, FrontEndConstants.LOG_OUT_MSG);
                requestDispatcher = request.getRequestDispatcher("Confirmation.jsp");
                requestDispatcher.forward(request, response);

            }

            if (action.equalsIgnoreCase("viewallusers")) {
                List<UserDetails> userList = userDAO.getAllUsers();
                request.setAttribute("userList", userList);
                requestDispatcher = request.getRequestDispatcher("user/viewallusers.jsp");
                requestDispatcher.forward(request, response);
            }

            if (action.equalsIgnoreCase("viewallmembers")) {
                String member = "Member";
                List<UserDetails> userList = userDAO.getUsersByUsertype(member);
                request.setAttribute("userList", userList);
                requestDispatcher = request.getRequestDispatcher("user/viewallusers.jsp");
                requestDispatcher.forward(request, response);
            }

            if (action.equalsIgnoreCase("viewallofficerss")) {
                String officer = "Officer";
                List<UserDetails> userList = userDAO.getUsersByUsertype(officer);
                request.setAttribute("userList", userList);
                requestDispatcher = request.getRequestDispatcher("user/viewallusers.jsp");
                requestDispatcher.forward(request, response);
            }

            if (action.equalsIgnoreCase("edituser")) {
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
                String emailAddress = request.getParameter("emailaddress");
                String username = request.getParameter("username");
                boolean isValidated = true;
                if (firstName == null || firstName.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (lastName == null || lastName.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (emailAddress == null || emailAddress.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (username == null || username.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    UserDetails user = new UserDetails(username, firstName, lastName, emailAddress);
                    userDAO.editUser(user);
                    request.setAttribute(BackendConstants.MESSAGE, "The user was edited sucessfully!");
                    requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/user/edituser.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("deleteuser")) {
                String username = request.getParameter("username");
                boolean isValidated = true;
                if (username == null || username.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    boolean result = userDAO.removeUser(username);
                    if (result == true) {
                        request.setAttribute(BackendConstants.MESSAGE, "The user was deleted sucessfully!");
                        requestDispatcher = request.getRequestDispatcher("Confirmation_2.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, "Error profile not deleted...");
                        requestDispatcher = request.getRequestDispatcher("user/deleteuser.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("edituserpassword")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String newPassword = request.getParameter("newPassword");
                String confirmNewPassword = request.getParameter("confirmNewPassword");
                boolean isValidated = true;
                if (username == null || username.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (password == null || password.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (newPassword == null || newPassword.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (confirmNewPassword == null || confirmNewPassword.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    UserDetails userStatus = userDAO.checkUserNameAvailability(username);
                    if (userStatus != null) {
                        if (newPassword.equals(confirmNewPassword)) {
                            UserDetails newUserPassword = new UserDetails(username, newPassword);
                            userDAO.editUserPassword(newUserPassword);
                            request.setAttribute(BackendConstants.MESSAGE, "The password was edited sucessfully!");
                            requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                            requestDispatcher.forward(request, response);
                        } else {
                            request.setAttribute(BackendConstants.ERROR_MESSAGE, "New and confirm password didnt match!");
                            requestDispatcher = request.getRequestDispatcher("/user/edituserpassword.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, "The password entered and current password dont match!");
                        requestDispatcher = request.getRequestDispatcher("/user/edituserpassword.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher = request.getRequestDispatcher("/user/edituserpassword.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

        } finally {
            out.close();
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(UserControlServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(UserControlServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
