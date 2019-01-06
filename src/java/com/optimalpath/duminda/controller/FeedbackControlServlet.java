/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.controller;

import com.optimalpath.duminda.dao.FeedBackDAOImpl;
import com.optimalpath.duminda.util.ReviewDetails;
import com.optimalpath.duminda.util.FrontEndConstants;
import com.optimalpath.duminda.util.ContactUsDetails;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.dao.FeedBackDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duminda
 */
@WebServlet(name = "FeedbackControlServlet", urlPatterns = {"/FeedbackControlServlet"})
public class FeedbackControlServlet extends HttpServlet {
    private FeedBackDAO feedBackFacade = new FeedBackDAOImpl();
    RequestDispatcher requestDispatcher = null;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("addreview")) {
                String comment = request.getParameter("comment");
                String locationId = request.getParameter("locationId");
                String username = request.getParameter("username");
                Integer locationID = Integer.parseInt(locationId);
                boolean isValidated = true;
                if (comment == null || comment.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    ReviewDetails newUser = new ReviewDetails(comment, locationID, username);
                    feedBackFacade.createReview(newUser);
                    request.setAttribute(BackendConstants.MESSAGE, "The review was added sucessfully!");
                    requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/feedback/addreview.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("addcontactus")) {
                String name = request.getParameter("name");
                String emailAddress = request.getParameter("emailAddress");
                String contactNo = request.getParameter("contactNo");
                String message = request.getParameter("message");
                boolean isValidated = true;
                if (name == null || name.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (contactNo == null || contactNo.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (emailAddress == null || emailAddress.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (message == null || message.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    ContactUsDetails contactUsDetails = new ContactUsDetails(name, emailAddress, contactNo, message);
                    feedBackFacade.createContactUs(contactUsDetails);

                    request.setAttribute(BackendConstants.MESSAGE, "You are requesed sucessfully!");
                    requestDispatcher = request.getRequestDispatcher("Confirmation.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/feedback/addcontactus.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("viewallinquiries")) {
                List<ContactUsDetails> list = feedBackFacade.getAllInquiries();
                request.setAttribute("list", list);
                requestDispatcher = request.getRequestDispatcher("/feedback/viewallinquiries.jsp");
                requestDispatcher.forward(request, response);
            }


        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
