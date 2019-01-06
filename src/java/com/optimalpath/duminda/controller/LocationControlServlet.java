/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.controller;

import com.optimalpath.duminda.dao.FeedBackDAOImpl;
import com.optimalpath.duminda.dao.FeedBackDAO;
import com.optimalpath.duminda.dao.HotLocationDAOImpl;
import com.optimalpath.duminda.dao.HotLocationDAO;
import com.optimalpath.duminda.dao.LocationCategoryDAOImpl;
import com.optimalpath.duminda.dao.LocationCategoryDAO;
import com.optimalpath.duminda.dao.LocationDAOImpl;
import com.optimalpath.duminda.dao.LocationDAO;
import com.optimalpath.duminda.dao.LocationImageDAOImpl;
import com.optimalpath.duminda.dao.LocationImageDAO;
import com.optimalpath.duminda.model.User;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.FrontEndConstants;
import com.optimalpath.duminda.util.GoogleMapLocationDetails;
import com.optimalpath.duminda.util.HotLocationDetails;
import com.optimalpath.duminda.util.LocationCategoryDetails;
import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.LocationImageDetails;
import com.optimalpath.duminda.util.ReviewDetails;
import com.optimalpath.duminda.util.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Duminda
 */
@WebServlet(name = "LocationControlServlet", urlPatterns = {"/LocationControlServlet"})
public class LocationControlServlet extends HttpServlet {

   
    private FeedBackDAO feedBackFacade;
   
    private LocationImageDAO locationImageFacade;
   
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
            feedBackFacade = new FeedBackDAOImpl();
            locationCategoryFacade = new LocationCategoryDAOImpl();
            locationFacade = new LocationDAOImpl();
            locationImageFacade = new LocationImageDAOImpl();
            hotLocationFacade = new HotLocationDAOImpl();
            
            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("addlocationcategory")) {
                String locationCategoryType = request.getParameter("locationCategoryType");
                boolean isValidated = true;
                if (locationCategoryType == null || locationCategoryType.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    LocationCategoryDetails locationCatDetails = new LocationCategoryDetails(locationCategoryType);
                    locationCategoryFacade.createLocationCategory(locationCatDetails);
                    request.setAttribute(BackendConstants.MESSAGE, "The location category was added sucessfully!");
                    requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/location/addlocationcategory.jsp");
                    requestDispatcher.forward(request, response);
                }
            }


            if (action.equalsIgnoreCase("viewlocationcategoriestodelete")) {
                List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                request.setAttribute("categoryList", categoryList);
                requestDispatcher = request.getRequestDispatcher("location/deletelocationcategory.jsp");
                requestDispatcher.forward(request, response);
            }


            if (action.equalsIgnoreCase("deletelocationcategory")) {
                String locationCategoryType = request.getParameter("locationCategoryType");
                Integer locationCategoryId = Integer.parseInt(locationCategoryType);
                boolean isValidated = true;
                if (locationCategoryType == null || locationCategoryType.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }

                if (isValidated) {
                    List<LocationDetails> locationsByCategoryList = locationFacade.getAllLocationsForCategory(locationCategoryId);
                    Iterator<LocationDetails> it = locationsByCategoryList.iterator();
                    while (it.hasNext()) {

                        LocationDetails locationDetails = it.next();
                        int id = locationDetails.getLocationId();
                        locationFacade.removeLocation(id);
                    }
                    boolean result = locationCategoryFacade.removeLocationCategory(locationCategoryId);
                    if (result == true) {
                        request.setAttribute(BackendConstants.MESSAGE, "The category was deleted sucessfully!");
                        requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, "Error category not deleted...");
                        requestDispatcher = request.getRequestDispatcher("location/deletelocationcategory.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher.forward(request, response);
                }
            }


            if (action.equalsIgnoreCase("viewlocationcategories")) {
                List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                request.setAttribute("categoryList", categoryList);
                requestDispatcher = request.getRequestDispatcher("location/addlocation.jsp");
                requestDispatcher.forward(request, response);
            }


            if (action.equalsIgnoreCase("addlocation")) {
                String locationName = request.getParameter("locationName");
                String locationDescription = request.getParameter("locationDescription");
                String locationcategorytype = request.getParameter("locationCategoryType");
                Integer locationCategoryId = Integer.parseInt(locationcategorytype);
                String locationCity = request.getParameter("locationCity");
                String locationDistrict = request.getParameter("locationDistrict");
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                String enteredBy = request.getParameter("enteredBy");
                Date enteredDate2 = new Date();
                String visitingTime1 = request.getParameter("visitingTime");
               
                String locationGoogleMapName = request.getParameter("locationGoogleMapName");
                String latitude = request.getParameter("latitude");
                String longitude = request.getParameter("longitude");
                Double lang = null;
                Double longi = null;
                Double visitingTime = null;
                boolean isValidated = true;
                boolean isValidated1 = true;
                if (locationName == null || locationName.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (locationDescription == null || locationDescription.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (locationcategorytype == null || locationcategorytype.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (locationCity == null || locationCity.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (locationDistrict == null || locationDistrict.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }else if (visitingTime1 == null || visitingTime1.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }else if (locationGoogleMapName == null || locationGoogleMapName.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (latitude == null || latitude.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (longitude == null || longitude.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    if (visitingTime1 != null || visitingTime1.length() > 0) {
                        try {
                            visitingTime = Double.parseDouble(visitingTime1);
                            
                        } catch (NumberFormatException ex) {
                            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.INVALID_NUMBER_FORMAT);
                            List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                            request.setAttribute("categoryList", categoryList);
                            isValidated1 = false;
                        }
                    }
                    if (latitude != null || latitude.length() > 0) {
                        try {
                            lang = Double.parseDouble(latitude);
                        } catch (NumberFormatException ex) {
                            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.INVALID_NUMBER_FORMAT);
                            List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                            request.setAttribute("categoryList", categoryList);
                            isValidated1 = false;
                        }
                    }
                    if (longitude != null || longitude.length() > 0) {
                        try {
                            longi = Double.parseDouble(longitude);
                        } catch (NumberFormatException ex) {
                            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.INVALID_NUMBER_FORMAT);
                            List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                            request.setAttribute("categoryList", categoryList);
                            isValidated1 = false;
                        }
                    }
                    if (isValidated1) {


                        LocationDetails locationDetail = new LocationDetails(locationName, locationDescription,
                                locationCategoryId, locationCity, locationDistrict,
                                null, null, true, user.getUsername(), enteredBy, enteredDate2,visitingTime);
                        
                        LocationDetails status = locationFacade.createLocation(locationDetail);
                        if (status != null) {
                            GoogleMapLocationDetails googleMapLocationDetails = new GoogleMapLocationDetails(locationGoogleMapName,
                                    lang, longi, status.getLocationId());
                            GoogleMapLocationDetails returnedStatus = locationFacade.createGoogleLocation(googleMapLocationDetails);
                            if ((returnedStatus != null)) {
                                int locationId = status.getLocationId();
                                request.setAttribute("locationId", new Integer(locationId));
                                request.setAttribute("locationCategoryId", locationCategoryId);
                                requestDispatcher = request.getRequestDispatcher("/location/addlocationimage.jsp");
                                requestDispatcher.forward(request, response);
                            } else {
                                request.setAttribute(BackendConstants.ERROR_MESSAGE, "Google Name exisitng");
                                List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                                request.setAttribute("categoryList", categoryList);
                                requestDispatcher = request.getRequestDispatcher("/location/addlocation.jsp");
                                requestDispatcher.forward(request, response);
                            }
                        } else {
                            request.setAttribute(BackendConstants.ERROR_MESSAGE, "Location Name existing");
                            List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                            request.setAttribute("categoryList", categoryList);
                            requestDispatcher = request.getRequestDispatcher("/location/addlocation.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                        request.setAttribute("categoryList", categoryList);
                        requestDispatcher = request.getRequestDispatcher("location/addlocation.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    List<LocationCategoryDetails> categoryList = locationCategoryFacade.getAllLocationCategories();
                    request.setAttribute("categoryList", categoryList);
                    requestDispatcher = request.getRequestDispatcher("location/addlocation.jsp");
                    requestDispatcher.forward(request, response);
                }
            }


            if (action.equalsIgnoreCase("editlocation")) {
                String locationId = request.getParameter("locationId");
                Integer locationID = Integer.parseInt(locationId);
                String locationName = request.getParameter("locationName");
                String locationDescription = request.getParameter("locationDescription");
                String locationCity = request.getParameter("locationCity");
                String locationDistrict = request.getParameter("locationDistrict");
                String enteredBy = request.getParameter("enteredBy");
                boolean isValidated = true;
                if (locationName == null || locationName.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (locationDescription == null || locationDescription.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (locationCity == null || locationCity.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (locationDistrict == null || locationDistrict.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    LocationDetails locationDetails = new LocationDetails(locationID, locationName, locationDescription,
                            locationCity, locationDistrict, enteredBy);
                    locationFacade.editLocation(locationDetails);
                    request.setAttribute(BackendConstants.MESSAGE, "The location was edited sucessfully!");
                    requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    LocationDetails locationDetail = locationFacade.getLocation(locationID);
                    request.setAttribute("locationDetail", locationDetail);
                    requestDispatcher = request.getRequestDispatcher("/location/updatelocation.jsp");
                    requestDispatcher.forward(request, response);
                }
            }


            if (action.equalsIgnoreCase("viewalllocations")) {
                List<LocationDetails> locationsList = locationFacade.getAllLocations();
                request.setAttribute("locationsList", locationsList);
                List<GoogleMapLocationDetails> googleMapLocationList = locationFacade.getAllGoogleMapLocations();
                request.setAttribute("googleMapLocationList", googleMapLocationList);
                requestDispatcher = request.getRequestDispatcher("location/viewalllocations.jsp");
                requestDispatcher.forward(request, response);
            }


            
            if (action.equalsIgnoreCase("viewalllocationsonmap")) {
                List<GoogleMapLocationDetails> googleMapLocationList = locationFacade.getAllGoogleMapLocations();
                Iterator<GoogleMapLocationDetails> iter = googleMapLocationList.iterator();
                String locationArray[] = new String[googleMapLocationList.size()];
                int i = 0;
                while (iter.hasNext()) {
                    GoogleMapLocationDetails googleMapLocation = iter.next();
                    String locationMapDetails = googleMapLocation.getGoogleMapLocationName() + "-" + googleMapLocation.getLatitude() + "-" + googleMapLocation.getLongitude() + "-" + googleMapLocation.getLocationId();
                    locationArray[i] = locationMapDetails;
                    ++i;
                }
                request.setAttribute("locationArray", locationArray);
                requestDispatcher = request.getRequestDispatcher("map/viewalllocationsonmap.jsp");
                requestDispatcher.forward(request, response);
            }


            if (action.equalsIgnoreCase("viewalllocationsbycityonmap")) {
                String locationCity = request.getParameter("locationCity");
                request.setAttribute("locationCity", locationCity);
                List<LocationDetails> locationByCityList = locationFacade.getLocationsByCity(locationCity);
                String locationArray[] = new String[locationByCityList.size()];
                int e = 0;
                for (LocationDetails loDetails : locationByCityList) {
                    GoogleMapLocationDetails googleMapLocationDetails = locationFacade.viewGoogleMapLocationForLocation(loDetails.getLocationId());
                    String locationMapDetails = googleMapLocationDetails.getGoogleMapLocationName() + "-" + googleMapLocationDetails.getLatitude() + "-" + googleMapLocationDetails.getLongitude() + "-" + googleMapLocationDetails.getLocationId();
                    locationArray[e] = locationMapDetails;
                    ++e;
                    request.setAttribute("locationArray", locationArray);
                }
                requestDispatcher = request.getRequestDispatcher("map/viewlocationsbycityonmap.jsp");
                requestDispatcher.forward(request, response);
            }

            
            if (action.equalsIgnoreCase("viewalllocationsonmapbycategory")) {
                String locationCategoryID = request.getParameter("locationCategoryID");
                Integer catelocationCategoryId = Integer.parseInt(locationCategoryID);
                LocationCategoryDetails categoryDetails = locationCategoryFacade.getLocationCategoryNameIdByLocationID(catelocationCategoryId);
                request.setAttribute("categoryDetails", categoryDetails);
                List<LocationDetails> locationByCategoryList = locationFacade.getAllLocationsForCategory(catelocationCategoryId);
                String locationArray[] = new String[locationByCategoryList.size()];
                int e = 0;
                for (LocationDetails loDetails : locationByCategoryList) {
                    GoogleMapLocationDetails googleMapLocationDetails = locationFacade.viewGoogleMapLocationForLocation(loDetails.getLocationId());
                   
                    String locationMapDetails = googleMapLocationDetails.getGoogleMapLocationName() + "-" + googleMapLocationDetails.getLatitude() + "-" + googleMapLocationDetails.getLongitude() + "-" + googleMapLocationDetails.getLocationId();
                    locationArray[e] = locationMapDetails;
                    ++e;
                    request.setAttribute("locationArray", locationArray);
                }
                request.setAttribute("locationArray", locationArray);
                requestDispatcher = request.getRequestDispatcher("map/viewlocationsbycategoryonmap.jsp");
                requestDispatcher.forward(request, response);
            }

            
            if (action.equalsIgnoreCase("viewlocationbycategories")) {
                String locationCategoryID = request.getParameter("locationCategoryID");
                Integer catelocationCategoryId = Integer.parseInt(locationCategoryID);
                LocationCategoryDetails categoryDetails = locationCategoryFacade.getLocationCategoryNameIdByLocationID(catelocationCategoryId);
                request.setAttribute("categoryDetails", categoryDetails);
                List<LocationDetails> locationByCategoryList = locationFacade.getAllLocationsForCategory(catelocationCategoryId);
                request.setAttribute("locationByCategoryList", locationByCategoryList);
                requestDispatcher = request.getRequestDispatcher("location/viewlocationbycategories.jsp");
                requestDispatcher.forward(request, response);
            }


            
            if (action.equalsIgnoreCase("viewlocationbycity")) {
                String locationCity = request.getParameter("locationCity");
                 request.setAttribute("locationCity", locationCity);
                List<LocationDetails> locationByCityList = locationFacade.getLocationsByCity(locationCity);
                request.setAttribute("locationByCityList", locationByCityList);
                requestDispatcher = request.getRequestDispatcher("location/viewlocationbycity.jsp");
                requestDispatcher.forward(request, response);
            }


            if (action.equalsIgnoreCase("viewlocationcnames")) {
                List<LocationDetails> locationList = locationFacade.getAllLocations();
                request.setAttribute("locationList", locationList);
                requestDispatcher = request.getRequestDispatcher("location/addhotlocation.jsp");
                requestDispatcher.forward(request, response);
            }

            if (action.equalsIgnoreCase("viewlocationtoedit")) {
                String locationid = request.getParameter("locationId");
                Integer locationId = Integer.parseInt(locationid);
                LocationDetails locationDetail = locationFacade.getLocation(locationId);
                request.setAttribute("locationDetail", locationDetail);
                requestDispatcher = request.getRequestDispatcher("location/updatelocation.jsp");
                requestDispatcher.forward(request, response);
            }

            
            if (action.equalsIgnoreCase("viewmorelocationdetails")) {
                String locationid = request.getParameter("locationId");
                Integer locationId = Integer.parseInt(locationid);
                LocationDetails locationDetail = locationFacade.getLocation(locationId);
                request.setAttribute("locationDetail", locationDetail);        
                List<LocationImageDetails> locationImageDetailses = locationImageFacade.getAllImagesForLocation(locationId);
                request.setAttribute("locationImageDetailses", locationImageDetailses);
                List<ReviewDetails> reviewDetails = feedBackFacade.getReviewsByLocationID(locationId);
                request.setAttribute("reviewDetails", reviewDetails);           
                String wiki = locationDetail.getLocationDescription();
                try{
                String des = fetchFirstParagraph(wiki);
                if (des != null) {
                    request.setAttribute("des", des);
                } else {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, "No Wikipdia Page Found.");
                }
                }catch(Exception e){
                    e.printStackTrace();
                }
                requestDispatcher = request.getRequestDispatcher("/location/viewmorelocationdetails.jsp");
                requestDispatcher.forward(request, response);
            }


            
            if (action.equalsIgnoreCase("viewonmap")) {
                String locationid = request.getParameter("locationId");
                Integer locationId = Integer.parseInt(locationid);
                LocationDetails locationDetail = locationFacade.getLocation(locationId);
                request.setAttribute("locationDetail", locationDetail);
                GoogleMapLocationDetails details = locationFacade.viewGoogleMapLocationForLocation(locationId);
                request.setAttribute("details", details);
                requestDispatcher = request.getRequestDispatcher("map/viewlocationonmap.jsp");
                requestDispatcher.forward(request, response);

            }


            if (action.equalsIgnoreCase("viewlocationids")) {
                List<LocationDetails> locationsList = locationFacade.getAllLocations();
                request.setAttribute("locationsList", locationsList);
                requestDispatcher = request.getRequestDispatcher("displaylocationids.jsp");
                requestDispatcher.forward(request, response);
            }

            if (action.equalsIgnoreCase("viewlocation")) {
                String locationid = request.getParameter("locationid");
                Integer locationId = Integer.parseInt(locationid);
                LocationDetails locationDetail = locationFacade.getLocation(locationId);
                locationDetail.setLocationCategoryType(locationDetail.getLocationCategoryType());
                request.setAttribute("locationDetail", locationDetail);
                GoogleMapLocationDetails googleMapLocationDetail = locationFacade.viewGoogleMapLocationForLocation(locationId);
                request.setAttribute("googleMapLocationDetail", googleMapLocationDetail);
                requestDispatcher = request.getRequestDispatcher("/viewlocation.jsp");
                requestDispatcher.forward(request, response);
            }


            if (action.equalsIgnoreCase("addhotlocation")) {
                String locationid = request.getParameter("locationid");
                Integer locationId = Integer.parseInt(locationid);
                boolean isValidated = true;
                if (locationid == null || locationid.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    HotLocationDetails hotLocation = new HotLocationDetails(locationId);
                    
                    hotLocationFacade.createHotLocation(hotLocation);
                    request.setAttribute(BackendConstants.MESSAGE, "The hot location was added sucessfully!");
                    requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    List<LocationDetails> locationList = locationFacade.getAllLocations();
                    request.setAttribute("locationList", locationList);
                    requestDispatcher = request.getRequestDispatcher("location/addhotlocation.jsp");
                    requestDispatcher.forward(request, response);
                }

            }

          

            if (action.equalsIgnoreCase("deletelocation")) {
                String locationId = request.getParameter("locationId");
                Integer locationID = Integer.parseInt(locationId);
                boolean isValidated = true;
                if (locationId == null || locationId.length() == 0) {
                   
                    isValidated = false;
                }
                if (isValidated) {
                    GoogleMapLocationDetails googleMapLocation = locationFacade.viewGoogleMapLocationForLocation(locationID);
                    int id = googleMapLocation.getGoogleMapLocationId();
                    locationFacade.removeGoogleMapLocation(id);
                    locationFacade.removeLocation(locationID);
                    request.setAttribute(BackendConstants.MESSAGE, "The location was deleted sucessfully!");
                    requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher.forward(request, response);
                }
            }



        } finally {
            out.close();
        }
    }

    public String fetchFirstParagraph(String article) throws IOException {
        
        String baseUrl = String.format("http://en.wikipedia.org/wiki/");
        String url = baseUrl + article;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select(".mw-content-ltr p");
            Element firstParagraph = paragraphs.first();
            return firstParagraph.text();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
