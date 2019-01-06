/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.controller;

import com.optimalpath.duminda.dao.LocationDAOImpl;
import com.optimalpath.duminda.dao.LocationDAO;
import com.optimalpath.duminda.dao.MatrixDAOImpl;
import com.optimalpath.duminda.dao.MatrixFacadeDAO;
import com.optimalpath.duminda.dao.TourPlannerListDAOImpl;
import com.optimalpath.duminda.dao.TourPlannerListDAO;
import com.optimalpath.duminda.model.User;
import com.optimalpath.duminda.optimalroutefinder.Edge;
import com.optimalpath.duminda.optimalroutefinder.Graph;
import com.optimalpath.duminda.optimalroutefinder.Path;
import com.optimalpath.duminda.optimalroutefinder.Scheduler;
import com.optimalpath.duminda.optimalroutefinder.TouristGuide;
import com.optimalpath.duminda.optimalroutefinder.Vertex;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.FrontEndConstants;
import com.optimalpath.duminda.util.GoogleMapLocationDetails;
import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.MatrixDetails;
import com.optimalpath.duminda.util.TourPlannerListDetails;
import com.optimalpath.duminda.util.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "TourPlanListControlServlet", urlPatterns = {"/TourPlanListControlServlet"})
public class TourPlanListControlServlet extends HttpServlet {

    List<Vertex> nodes;
    List<Edge> edges;
    LinkedHashMap<String, Integer> hashMap;
    private TourPlannerListDAO tourPlannerListFacade;
    private LocationDAO locationFacade;
    private MatrixFacadeDAO matrixFacade;
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
        try {
            tourPlannerListFacade = new TourPlannerListDAOImpl();
            locationFacade = new LocationDAOImpl();
            matrixFacade = new MatrixDAOImpl();

            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("checkexisitng")) {
                String locationId = request.getParameter("locationId");
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    request.setAttribute("locationId", locationId);
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("locationId", locationId);
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    if (list != null && list.size() >= 1) {
                        request.setAttribute("list", list);
                        requestDispatcher = request.getRequestDispatcher("tourplan/viewtourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("tourplan/addtourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            }

            if (action.equalsIgnoreCase("checkexisitngfrommap")) {
                String locationId = request.getParameter("locationId");
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    request.setAttribute("locationId", locationId);
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("locationId", locationId);
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    if (list != null && list.size() >= 1) {
                        request.setAttribute("list", list);
                        requestDispatcher = request.getRequestDispatcher("tourplan/viewtourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("tourplan/addtourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            }

            if (action.equalsIgnoreCase("viewalltours")) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    if (list != null && list.size() >= 1) {
                        request.setAttribute("list", list);
                        requestDispatcher = request.getRequestDispatcher("tourplan/viewalltourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    } else {

                        requestDispatcher = request.getRequestDispatcher("tourplan/addtourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            }

            if (action.equalsIgnoreCase("addnewtourdetails")) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/tourplan/addtourdetails.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("addtourdetails")) {
                String username = request.getParameter("username");
                String arrivalDate = request.getParameter("arrivalDate");
                String departureDate = request.getParameter("departureDate");
                String startLocation = request.getParameter("startLocation");
                String endLocation = request.getParameter("endLocation");
                Integer sId = Integer.parseInt(startLocation);
                Integer eId = Integer.parseInt(endLocation);
                boolean isValidated = true;
                if (arrivalDate == null || arrivalDate.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (departureDate == null || departureDate.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (startLocation == null || startLocation.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                } else if (endLocation == null || endLocation.length() == 0) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontEndConstants.REQUIRED);
                    isValidated = false;
                }
                if (isValidated) {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                    Date result = df.parse(arrivalDate);
                    Date result1 = df1.parse(departureDate);
                    if (result.before(result1)) {
                        TourPlannerListDetails tourPlannerListDetails = new TourPlannerListDetails(result, result1,
                                sId, eId, username);
                        TourPlannerListDetails tourplannerlist = tourPlannerListFacade.createTourPlannerList(tourPlannerListDetails);
                        if (tourplannerlist != null) {
                            int tourPlannerListId = tourplannerlist.getTourPlannerListId();
                            request.setAttribute("tourPlannerListId", new Integer(tourPlannerListId));
                            request.setAttribute(BackendConstants.MESSAGE, "You have sucessfully created a new tour! ");
                            requestDispatcher = request.getRequestDispatcher("Confirmation_1.jsp");
                            requestDispatcher.forward(request, response);
                        } else {
                            request.setAttribute(BackendConstants.ERROR_MESSAGE, "Error");
                            requestDispatcher = request.getRequestDispatcher("/tourplan/addtourdetails.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, "Invalid Dates");
                        requestDispatcher = request.getRequestDispatcher("/tourplan/addtourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher = request.getRequestDispatcher("/tourplan/addtourdetails.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("selecttourplannerlist")) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                String tourplannerlistid = request.getParameter("tourplannerlistid");
                Integer tourPlannerListID = Integer.parseInt(tourplannerlistid);
                String locationId = request.getParameter("locationId");
                Integer locationID = Integer.parseInt(locationId);
                List<LocationDetails> locationsForTour = tourPlannerListFacade.getAllLocationsForTourPlannerList(tourPlannerListID);
                boolean isExisted = false;
                for (LocationDetails l : locationsForTour) {
                    String existLocId = Integer.toString(l.getLocationId());
                    if (existLocId.equals(locationId)) {
                        isExisted = true;
                        break;
                    }
                }
                if (!isExisted) {
                    List<LocationDetails> al = tourPlannerListFacade.addLocationToTourPlanner(locationID, tourPlannerListID);
                    session.setAttribute("tourplannerlistid", tourplannerlistid);
                    session.setAttribute(BackendConstants.TOUR_LOCATIONS, al);
                    requestDispatcher = request.getRequestDispatcher("tourplan/tourmoredetails.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("locationId", locationId);
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, "Location Already Existing. Please select another list. Or start new tour");
                    request.setAttribute("list", list);
                    requestDispatcher = request.getRequestDispatcher("/tourplan/viewtourdetails.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("selecttourplannerlist")) {

                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                String tourplannerlistid = request.getParameter("tourplannerlistid");
                Integer tourPlannerListID = Integer.parseInt(tourplannerlistid);
                String locationId = request.getParameter("locationId");
                Integer locationID = Integer.parseInt(locationId);

                List<LocationDetails> locationsForTour = tourPlannerListFacade.getAllLocationsForTourPlannerList(tourPlannerListID);
                boolean isExisted = false;
                for (LocationDetails l : locationsForTour) {
                    String existLocId = Integer.toString(l.getLocationId());
                    if (existLocId.equals(locationId)) {
                        isExisted = true;
                        break;
                    }
                }
                if (!isExisted) {
                    List<LocationDetails> al = tourPlannerListFacade.addLocationToTourPlanner(locationID, tourPlannerListID);
                    session.setAttribute("tourplannerlistid", tourplannerlistid);
                    session.setAttribute(BackendConstants.TOUR_LOCATIONS, al);
                    requestDispatcher = request.getRequestDispatcher("tourplan/tourmoredetails.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("locationId", locationId);
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, "Location Already Existing. Please select another list. Or start new tour");
                    request.setAttribute("list", list);
                    requestDispatcher = request.getRequestDispatcher("/tourplan/viewtourdetails.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("viewtourplannerlist")) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    String tourplannerlistid = request.getParameter("tourplannerlistid");
                    Integer tourPlannerListID = Integer.parseInt(tourplannerlistid);
                    List<LocationDetails> al = tourPlannerListFacade.getAllLocationsForTourPlannerList(tourPlannerListID);
                    session.setAttribute(BackendConstants.TOUR_LOCATIONS, al);
                    session.setAttribute("tourplannerlistid", tourplannerlistid);
                    requestDispatcher = request.getRequestDispatcher("tourplan/tourmoredetails.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("viewoptimalpath")) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    String tourplannerlistid = request.getParameter("tourplannerlistid");
                    Integer tourPlannerListID = Integer.parseInt(tourplannerlistid);
                    List<LocationDetails> locationsForTour = tourPlannerListFacade.getAllLocationsForTourPlannerList(tourPlannerListID);
                    List<LocationDetails> allLocationDetails = locationFacade.getAllLocations();
                    TourPlannerListDetails tour = tourPlannerListFacade.getDetailsForTourPlannerList(tourPlannerListID);
                    boolean t = true;
                    if (allLocationDetails != null && allLocationDetails.size() > 0) {
                        nodes = new ArrayList<Vertex>();
                        for (LocationDetails locationDetails : allLocationDetails) {
                            nodes.add(new Vertex(Integer.toString(locationDetails.getLocationId()), locationDetails.getLocationName()));
                        }
                        edges = new ArrayList<Edge>();
                        int x = 0;
                        List<Integer> locationIndexList = new ArrayList<Integer>();
                        LocationDetails locDetails = allLocationDetails.get(0);
                        if (t) {
                            locationIndexList.add(locDetails.getLocationId());
                            t = false;
                        }
                        boolean locationIndexCompleted = false;
                        for (LocationDetails locationDetails : allLocationDetails) {
                            int locId = locationDetails.getLocationId();
                            List<MatrixDetails> detail = matrixFacade.getAllLocationsPathsForLocationID1(locId);
                            int y = 0;
                            for (MatrixDetails matrixDetails : detail) {
                                if (x == y) {
                                    y = y + 1;
                                }
                                String edge = Integer.toString(matrixDetails.getLocationId1()) + "-" + Integer.toString(matrixDetails.getLocationId2());

                                double distance = matrixDetails.getDistance();
                                try {
                                    if (!locationIndexCompleted) {
                                        if (x == y) {
                                            y = y + 1;
                                        }
                                        locationIndexList.add(matrixDetails.getLocationId2());
                                        addLane(edge, x, y, distance);
                                    } else {
                                        int loc1Index = locationIndexList.indexOf(matrixDetails.getLocationId1());
                                        int loc2Index = locationIndexList.indexOf(matrixDetails.getLocationId2());

                                        addLane(edge, loc1Index, loc2Index, distance);
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                ++y;
                            }
                            locationIndexCompleted = true;
                            ++x;
                        }
                    }

                    if (locationsForTour != null && locationsForTour.size() > 0) {

                        Graph graph = new Graph(nodes, edges);
                        TouristGuide touristGuide = new TouristGuide(graph);
                        for (Vertex v : nodes) {
                            if (v.getId().equals(Integer.toString(tour.getStartLocationId()))) {
                                touristGuide.setSource(v);
                                break;
                            }
                        }
                        int visitLocations = nodes.size();
                        int i = 1;
                        for (LocationDetails ld : locationsForTour) {
                            for (Vertex v : nodes) {
                                if (v.getId().equals(ld.getLocationId().toString())) {
                                    touristGuide.markPalceToVisit(v);
                                    break;
                                }
                            }
                        }
                        Path path;
                        try {
                            path = touristGuide.getOptimalPath(nodes.get(tour.getEndLocationId()));
                        } catch (Exception ex) {
                            path = touristGuide.getOptimalPath(nodes.get(tour.getStartLocationId()));
                        }
                        List<Vertex> optimalPathList = path.getPoints();
                        String locationArray[] = new String[optimalPathList.size()];
                        int e = 0;
                        String locationOrder[] = new String[optimalPathList.size()];
                        for (Vertex v : optimalPathList) {
                            Integer id = Integer.parseInt(v.getId());
                            GoogleMapLocationDetails googleMapLocation = locationFacade.viewGoogleMapLocationForLocation(id);
                            String locationMapDetails = googleMapLocation.getGoogleMapLocationName() + "-" + googleMapLocation.getLatitude() + "-" + googleMapLocation.getLongitude() + "-" + googleMapLocation.getLocationId();

                            locationArray[e] = locationMapDetails;
                            String locIdName = v.getId() + "-" + v.getName();
                            locationOrder[e] = locIdName;
                            ++e;
                        }
                        request.setAttribute("locationArray", locationArray);
                        request.setAttribute("tourPlannerListID", tourPlannerListID);
                        if (path != null) {
                            path.print();
                        } else {
                            System.out.println("Error");
                        }
                        requestDispatcher = request.getRequestDispatcher("route/displayoptimalroute.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        List<LocationDetails> al = tourPlannerListFacade.getAllLocationsForTourPlannerList(tourPlannerListID);
                        session.setAttribute(BackendConstants.TOUR_LOCATIONS, al);
                        session.setAttribute("tourplannerlistid", tourplannerlistid);
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, "No Locations to check the optimal route.");
                        requestDispatcher = request.getRequestDispatcher("tourplan/tourmoredetails.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                }

            }

            if (action.equalsIgnoreCase("viewschedule")) {

                HttpSession session = request.getSession();

                User user = (User) session.getAttribute("user");

                if (user != null) {

                    String tourplannerlistid = request.getParameter("tourplannerlistid");
                    Integer tourPlannerListID = Integer.parseInt(tourplannerlistid);
                    List<LocationDetails> locationsForTour = tourPlannerListFacade.getAllLocationsForTourPlannerList(tourPlannerListID);
                    TourPlannerListDetails tour = tourPlannerListFacade.getDetailsForTourPlannerList(tourPlannerListID);
                    TourPlannerListDetails tpld = null;
                    if (locationsForTour != null && locationsForTour.size() > 0) {
                        nodes = new ArrayList<Vertex>();
                        edges = new ArrayList<Edge>();
                        tpld = tourPlannerListFacade.getDetailsForTourPlannerList(tourPlannerListID);

                        List<LocationDetails> allLocationDetails = locationFacade.getAllLocations();
                        boolean t = true;
                        if (allLocationDetails != null && allLocationDetails.size() > 0) {
                            nodes = new ArrayList<Vertex>();
                            for (LocationDetails locationDetails : allLocationDetails) {
                                nodes.add(new Vertex(Integer.toString(locationDetails.getLocationId()), locationDetails.getLocationName()));
                            }
                            edges = new ArrayList<Edge>();
                            int x = 0;
                            List<Integer> locationIndexList = new ArrayList<Integer>();
                            LocationDetails locDetails = allLocationDetails.get(0);
                            if (t) {
                                locationIndexList.add(locDetails.getLocationId());
                                t = false;
                            }
                            boolean locationIndexCompleted = false;
                            for (LocationDetails locationDetails : allLocationDetails) {
                                int locId = locationDetails.getLocationId();
                                List<MatrixDetails> detail = matrixFacade.getAllLocationsPathsForLocationID1(locId);
                                int y = 0;
                                for (MatrixDetails matrixDetails : detail) {
                                    if (x == y) {
                                        y = y + 1;
                                    }
                                    String edge = Integer.toString(matrixDetails.getLocationId1()) + "-" + Integer.toString(matrixDetails.getLocationId2());
                                    double distance = matrixDetails.getDistance();
                                    try {
                                        if (!locationIndexCompleted) {
                                            if (x == y) {
                                                y = y + 1;
                                            }
                                            locationIndexList.add(matrixDetails.getLocationId2());
                                            addLane(edge, x, y, distance);
                                        } else {
                                            int loc1Index = locationIndexList.indexOf(matrixDetails.getLocationId1());
                                            int loc2Index = locationIndexList.indexOf(matrixDetails.getLocationId2());

                                            addLane(edge, loc1Index, loc2Index, distance);
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    ++y;
                                }

                                locationIndexCompleted = true;
                                ++x;
                            }

                        }
                        Graph graph = new Graph(nodes, edges);

                        TouristGuide touristGuide = new TouristGuide(graph);
                        List<String> markedLocationsList = new ArrayList<String>();
                        for (Vertex v : nodes) {
                            if (v.getId().equals(Integer.toString(tour.getStartLocationId()))) {
                                touristGuide.setSource(v);
                                markedLocationsList.add(v.getId());
                                break;
                            }
                        }

                        int visitLocations = nodes.size();
                        int i = 1;
                        for (LocationDetails ld : locationsForTour) {
                            for (Vertex v : nodes) {
                                if (v.getId().equals(ld.getLocationId().toString())) {
                                    touristGuide.markPalceToVisit(v);
                                    markedLocationsList.add(v.getId());
                                    break;
                                }
                            }
                        }
                        Path path = touristGuide.getOptimalPath(nodes.get(tour.getEndLocationId()));
                        List<Vertex> optimalPathList = path.getPoints();
                        String locationArray[] = new String[optimalPathList.size()];
                        int e = 0;
                        String locationOrder[] = new String[optimalPathList.size()];
                        List<Scheduler> dailySchedulers = new ArrayList<>();
                        for (Vertex v : optimalPathList) {
                            Integer id = Integer.parseInt(v.getId());
                            GoogleMapLocationDetails googleMapLocation = locationFacade.viewGoogleMapLocationForLocation(id);
                            String locationMapDetails = googleMapLocation.getGoogleMapLocationName() + "-"
                                    + googleMapLocation.getLatitude() + "-" + googleMapLocation.getLongitude()
                                    + "-" + googleMapLocation.getLocationId();
                            locationArray[e] = locationMapDetails;
                            String locIdName = v.getId() + "-" + v.getName();
                            locationOrder[e] = locIdName;
                            ++e;
                        }
                        Date checkInDate = tpld.getCheckinDate();
                        GregorianCalendar grCalendar = new GregorianCalendar();
                        grCalendar.setTime(checkInDate);
                        grCalendar.set(GregorianCalendar.HOUR, 9);
                        grCalendar.set(GregorianCalendar.MINUTE, 0);
                        for (int p = 0; p < locationOrder.length; p++) {
                            if ((p + 1) < locationOrder.length) {
                                String locIdName[] = locationOrder[p].split("-");
                                String locId1 = locIdName[0];
                                String locName = locIdName[1];
                                String nextLocIdName[] = locationOrder[p + 1].split("-");
                                String locId2 = nextLocIdName[0];
                                String nextLocName = nextLocIdName[1];
                                int locId = Integer.parseInt(locId1);
                                int nexttLocId = Integer.parseInt(locId2);
                                MatrixDetails detail = matrixFacade.getMatrixDetails(locId, nexttLocId);
                                LocationDetails locdetails;
                                if ((p + 1) != (locationOrder.length - 1)) {
                                    locdetails = locationFacade.getLocation(nexttLocId);
                                } else {
                                    locdetails = locationFacade.getLocation(locId);
                                }
                                double travelDuration = detail.getTime();
                                String travelTimeStr = Double.toString(travelDuration);
                                String hoursminute[] = travelTimeStr.split("\\.");
                                int h = Integer.parseInt(hoursminute[0]);
                                String m = "." + hoursminute[1];
                                int minute = (int) ((Double.parseDouble(m)) * 60);
                                grCalendar.add(GregorianCalendar.HOUR, h);
                                grCalendar.add(GregorianCalendar.MINUTE, minute);
                                Date arrivingTime = grCalendar.getTime();
                                int hours = grCalendar.get(GregorianCalendar.HOUR_OF_DAY);

                                if (hours >= 18) {
                                    grCalendar.add(GregorianCalendar.DATE, 1);
                                    grCalendar.set(GregorianCalendar.HOUR_OF_DAY, 9);
                                    grCalendar.set(GregorianCalendar.MINUTE, 0);
                                }

                                int y = 0;
                                double visitingDuration;
                                if (markedLocationsList.contains(locdetails.getLocationId().toString())) {
                                    visitingDuration = locdetails.getVisitingDuration();
                                } else {
                                    visitingDuration = 0.0;
                                }

                                String visitingDurationStr = Double.toString(visitingDuration);
                                String hoursminute2[] = visitingDurationStr.split("\\.");
                                int h2 = Integer.parseInt(hoursminute2[0]);
                                String m2 = "." + hoursminute2[1];
                                int minute2 = (int) ((Double.parseDouble(m2)) * 60);
                                grCalendar.add(GregorianCalendar.HOUR, h2);
                                grCalendar.add(GregorianCalendar.MINUTE, minute2);
                                Date visingTime = grCalendar.getTime();
                                double distance = detail.getDistance();
                                DecimalFormat df = new DecimalFormat("#.##");
                                String travalDurationStr = df.format(travelDuration);
                                double travelDurationFormated = Double.parseDouble(travalDurationStr);
                                Scheduler scheduler = new Scheduler(locName, nextLocName, distance, travelDurationFormated,
                                        visitingDuration, arrivingTime, visingTime);
                                dailySchedulers.add(scheduler);
                            }
                        }
                        request.setAttribute("tourPlannerListID", tourPlannerListID);
                        request.setAttribute("tourPlannerList", tpld);
                        request.setAttribute("dailySchedulers", dailySchedulers);

                        requestDispatcher = request.getRequestDispatcher("route/viewschedule.jsp");
                        requestDispatcher.forward(request, response);
                    } else {

                        List<LocationDetails> al = tourPlannerListFacade.getAllLocationsForTourPlannerList(tourPlannerListID);
                        session.setAttribute(BackendConstants.TOUR_LOCATIONS, al);
                        session.setAttribute("tourplannerlistid", tourplannerlistid);
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, "No Locations to check the optimal route.");
                        requestDispatcher = request.getRequestDispatcher("tourplan/tourmoredetails.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher = request.getRequestDispatcher("user/login.jsp");
                    requestDispatcher.forward(request, response);
                }

            }

            if (action.equalsIgnoreCase("deletetourplannerlist")) {
                HttpSession session = request.getSession();
                String tourplannerlistid = request.getParameter("tourplannerlistid");
                Integer tourPlannerListID = Integer.parseInt(tourplannerlistid);
                User user = (User) session.getAttribute("user");
                boolean al = tourPlannerListFacade.removeTourPlannerList(tourPlannerListID);
                if (al == true) {
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    request.setAttribute("list", list);
                    session.setAttribute("tourplannerlistid", tourplannerlistid);
                    requestDispatcher = request.getRequestDispatcher("tourplan/viewalltourdetails.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    request.setAttribute("list", list);
                    session.setAttribute("tourplannerlistid", tourplannerlistid);
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, "Error");
                    requestDispatcher = request.getRequestDispatcher("/tourplan/viewalltourdetails.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (action.equalsIgnoreCase("deletelocationtourplannerlist")) {
                HttpSession session = request.getSession();
                String tourplannerlistid = request.getParameter("tourplannerlistid");
                Integer tourPlannerListID = Integer.parseInt(tourplannerlistid);
                String locationId = request.getParameter("locationId");
                Integer locationID = Integer.parseInt(locationId);
                boolean al = tourPlannerListFacade.removeLocationFromTourPlanner(tourPlannerListID, locationID);
                if (al == true) {
                    ArrayList<LocationDetails> locationDetailses = (ArrayList<LocationDetails>) session.getAttribute(BackendConstants.TOUR_LOCATIONS);
                    Iterator it = locationDetailses.iterator();
                    while (it.hasNext()) {
                        LocationDetails locationDetails = (LocationDetails) it.next();
                        String existLocId = Integer.toString(locationDetails.getLocationId());
                        if (locationId.equals(existLocId)) {
                            locationDetailses.remove(locationDetails);
                            break;
                        }
                    }

                    UserDetails user = (UserDetails) session.getAttribute("user");
                    List<TourPlannerListDetails> list = tourPlannerListFacade.getTourPlannerListIDForUsername(user.getUsername());
                    if (list != null && list.size() >= 1) {
                        request.setAttribute("list", list);
                        session.setAttribute("tourplannerlistid", tourplannerlistid);
                        requestDispatcher = request.getRequestDispatcher("tourplan/viewalltourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("tourplan/addtourdetails.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, "Error");
                    requestDispatcher = request.getRequestDispatcher("/tourplan/viewtourdetails.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        } finally {
            out.close();
        }
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
            double distance) {

        Edge lane = new Edge(laneId, nodes.get(sourceLocNo),
                nodes.get(destLocNo), distance);

        edges.add(lane);
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
            Logger.getLogger(TourPlanListControlServlet.class
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
            Logger.getLogger(TourPlanListControlServlet.class
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
