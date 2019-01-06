/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.controller;

import com.optimalpath.duminda.dao.HotLocationDAOImpl;
import com.optimalpath.duminda.dao.HotLocationDAO;
import com.optimalpath.duminda.dao.LocationCategoryDAOImpl;
import com.optimalpath.duminda.dao.LocationCategoryDAO;
import com.optimalpath.duminda.dao.LocationDAOImpl;
import com.optimalpath.duminda.dao.LocationDAO;
import com.optimalpath.duminda.model.User;
import com.optimalpath.duminda.util.LocationCategoryDetails;
import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.UserDetails;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Duminda
 */
@WebServlet(name = "WelcomeControlServlet", urlPatterns = {"/WelcomeControlServlet"})
public class WelcomeControlServlet extends HttpServlet {

   
    private HotLocationDAO hotLocationFacade;
   
    private LocationCategoryDAO locationCategoryFacade;
   
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
           locationFacade = new LocationDAOImpl();
           locationCategoryFacade = new LocationCategoryDAOImpl();
           hotLocationFacade = new HotLocationDAOImpl();
           
            HttpSession session = request.getSession();
            List<LocationDetails> locationList = locationFacade.getAllLocations();
            session.setAttribute("locationList", locationList);
            List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
            session.setAttribute("categoryList", categoryList);
            List<LocationDetails> hotLocationDetails = hotLocationFacade.getAllHotLocations();
            session.setAttribute("hotLocationDetails", hotLocationDetails);
            List<String> locationCityList = locationFacade.getAllLocationsCities();
            session.setAttribute("locationCityList", locationCityList);
 //           List<GoogleMapLocationDetails> googleMapLocationList = locationFacade.getAllGoogleMapLocations();
 //           Iterator<GoogleMapLocationDetails> iter = googleMapLocationList.iterator();
//            String locationArray[] = new String[googleMapLocationList.size()];
//            int i = 0;
//            while (iter.hasNext()) {
//                GoogleMapLocationDetails googleMapLocation = iter.next();
//                String locationMapDetails = googleMapLocation.getGoogleMapLocationName() + "-" + googleMapLocation.getLatitude() + "-" + googleMapLocation.getLongitude() + "-" + googleMapLocation.getLocationId();
//                locationArray[i] = locationMapDetails;
//                ++i;
//            }
//
//            session.setAttribute("locationArray", locationArray);
           
            User user = (User) session.getAttribute("user");
            
            if (user == null) {
                
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            
            } else if (user.getUserType().equals("Admin")) {
            
                requestDispatcher = request.getRequestDispatcher("user/admindashboard.jsp");
                requestDispatcher.forward(request, response);
            
            } else if (user.getUserType().equals("Member")) {
                requestDispatcher = request.getRequestDispatcher("user/memberdashboard.jsp");
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
