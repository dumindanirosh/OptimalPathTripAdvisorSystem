/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.controller;


import com.optimalpath.duminda.dao.LocationDAO;
import com.optimalpath.duminda.dao.LocationDAOImpl;
import com.optimalpath.duminda.dao.MatrixFacadeDAO;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.FrontEndConstants;
import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.MatrixDetails;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DistanceTimeControlServlet", urlPatterns = {"/DistanceTimeControlServlet"})
public class DistanceTimeControlServlet extends HttpServlet {

  
    private MatrixFacadeDAO matrixFacade;
  
    private LocationDAO locationFacade;
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
            if (action.equalsIgnoreCase("adddistance")) {
                
                locationFacade = new LocationDAOImpl();
                
                String address1 = request.getParameter("address1");
                LocationDetails details = locationFacade.checkLocationNameExisting(address1);
                int ad1 = details.getLocationId();
                String address2 = request.getParameter("address2");
                
                LocationDetails details1 = locationFacade.checkLocationNameExisting(address2);
                
                int ad2 = details1.getLocationId();
                String distance = request.getParameter("dis");
                String time = request.getParameter("time");
                Double distance1 = Double.parseDouble(distance);
                Double time1 = Double.parseDouble(time);
                boolean isValidated = true;
                if (address1 == null || address1.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (address2 == null || address2.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (distance == null || distance.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (time == null || time.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    MatrixDetails matrixDetails = new MatrixDetails(distance1, time1,
                            ad1, ad2);
                    MatrixDetails returnedStatus = matrixFacade.createMatrix(matrixDetails);
                    if (returnedStatus != null) {
                        request.setAttribute(BackendConstants.MESSAGE, "The location distance was added sucessfully!");
                        requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                        requestDispatcher = request.getRequestDispatcher("Confirmation.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher = request.getRequestDispatcher("Confirmation.jsp");
                    requestDispatcher.forward(request, response);
                }
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
