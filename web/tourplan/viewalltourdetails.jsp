


<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="com.optimalpath.duminda.util.TourPlannerListDetails"%>
<%@page import="com.optimalpath.duminda.util.UserDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <title>Optimal Path Trip Advisor</title>


        <link rel="stylesheet" href="css/skeleton.css" />
        <link rel="stylesheet" href="css/flexslider.css" />
        <link rel="stylesheet" href="css/lamoon.css" />
        <link rel="stylesheet" href="css/base.css" />


    </head>
    <%!        LocationDetails location;
        List<LocationDetails> locationList;
        List<LocationCategoryDetails> listOfCategories;
        List<TourPlannerListDetails> list;
        List<LocationDetails> al;
        List<String> locationCityList;
    %>
    <body>

       
        <img src="images/bg111.jpg" class="bg" alt="" />

        
        <div id="root-container" class="container">
            <div id="wrapper" class="sixteen columns">


                <div id="sub-banner">
                    <div id="logo">
                        
                    </div>
                    <img src="images/placeholders/banner1.jpg" alt="" width="1240" />
                </div>

                 <div id="menu" class="home">
                    <ul id="root-menu" class="sf-menu">
                        <li>
                            <a href="WelcomeControlServlet" class="active" >Home</a>
                        </li>
                        <li>
                            <a href="LocationControlServlet?action=viewalllocationsonmap">Map of Sri Lanka</a>
                        </li>
                        <li>
                            <a>Attraction Category</a>
                            <ul>
                                <%listOfCategories = (List<LocationCategoryDetails>) session.getAttribute("categoryList");
                                    Iterator<LocationCategoryDetails> it = listOfCategories.iterator();
                                    while (it.hasNext()) {
                                        LocationCategoryDetails locationCategoryList = it.next();
                                %>  <li>

                                    <a href="LocationControlServlet?action=viewlocationbycategories&locationCategoryID=<%=locationCategoryList.getLocationCategoryId()%>">
                                        <%=locationCategoryList.getLocationCategoryType()%>
                                    </a>
                                </li>
                                <%}%>
                            </ul>
                        </li>
                        <li>
                            <a href="">Attraction City</a>
                            <ul>
                                <%
                                    locationCityList = (List<String>) session.getAttribute("locationCityList");
                                    for (String temp : locationCityList) {
                                %>
                                <li>
                                    <a href="LocationControlServlet?action=viewlocationbycity&locationCity=<%=temp%>"><%=temp%></a>
                                </li>
                                <%}%>
                            </ul>
                        </li>
                        <li>
                            <a href="">Tour Map Category</a>

                            <ul>
                                <%
                                    Iterator<LocationCategoryDetails> it2 = listOfCategories.iterator();
                                    while (it2.hasNext()) {
                                        LocationCategoryDetails locationCategoryList = it2.next();
                                %>  <li>

                                    <a href="LocationControlServlet?action=viewalllocationsonmapbycategory&locationCategoryID=<%=locationCategoryList.getLocationCategoryId()%>">
                                        <%=locationCategoryList.getLocationCategoryType()%>
                                    </a>
                                </li>
                                <%}%>
                            </ul>

                        </li>
                        <li>
                            <a href="">Tour Map City</a>
                            <ul>
                                <%
                                    locationCityList = (List<String>) session.getAttribute("locationCityList");
                                    for (String temp : locationCityList) {
                                %>
                                <li>
                                     <a href="LocationControlServlet?action=viewalllocationsbycityonmap&locationCity=<%=temp%>"><%=temp%></a>
                                </li>
                                <%}%>
                            </ul>
                        </li>


                    </ul>
                </div>


                <%
                  User user = (User) session.getAttribute("user");
                    if (user != null) {
                %>

                <div id="content">
                    <div id="menu" class="home">

                        <ul id="root-menu" class="sf-menu">
                            <li><a href="forwardAction?forwardPage=user/viewuser.jsp"  >Display Profile</a></li>        
                            <li> <a href="TourPlanListControlServlet?action=viewalltours" >View All Tours</a></li>
                            <li><a href="UserControlServlet?action=logout" >Logout &nbsp;</a></li>
                            <li><a style="font-size: 22px" >Hello <%=user.getUsername()%></a></li>

                        </ul>
                    </div>
                </div>


             
                <div id="content" class="reservation">
                    <div class="container section">


                        <div id="intro">

                            <h3 class="extra-margin top"><span class="left">All Tours Info</span></h3>
                            <%
                                list = (List<TourPlannerListDetails>) request.getAttribute("list");
                                Iterator<TourPlannerListDetails> it1 = list.iterator();
                                while (it1.hasNext()) {
                                    TourPlannerListDetails add = it1.next();
                                    String tourplannerlistid = Integer.toString(add.getTourPlannerListId());
                            %>
                            <table border="1">

                                <tbody>
                                    <tr>
                                        <td><label style="font-size: 20px">Tour Planner List ID: </label></td>
                                        <td> <%=add.getTourPlannerListId()%></td>


                                        <td>  <a href="TourPlanListControlServlet?action=deletetourplannerlist&tourplannerlistid=<%=tourplannerlistid%>">Delete Tour</a></td>

                                    </tr>
                                    <tr>
                                        <%Iterator<LocationDetails> it4 = add.getLocationList().iterator();
                                            while (it4.hasNext()) {
                                                LocationDetails location = it4.next();
                                                String locationId = Integer.toString(location.getLocationId());
                                        %>  
                                        <td> <label style="font-size: 20px">Location Name :</label></td>
                                        <td> <%=location.getLocationName()%></td>
                                        <td>  <a href="TourPlanListControlServlet?action=deletelocationtourplannerlist&tourplannerlistid=<%=tourplannerlistid%>&locationId=<%=locationId%>" >Delete Location</a></td>
                                    </tr>
                                </tbody>

                                <%
                                    }
                                %>
                                <br/>
                                <div>
                                    <a href="LocationControlServlet?action=viewalllocationsonmap" class="large gray button">Add More Locations</a>
                                   
                                    <a href="TourPlanListControlServlet?action=viewoptimalpath&tourplannerlistid=<%=tourplannerlistid%>" class="large gray button">Check Optimal Route</a>
                                </div>
                                <%
                                    }
                                %>

                            </table>
                                 <div >
                                <%
                                    String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                    if (errorMessage != null) {
                                        out.println(errorMessage);
                                    }%>
                            </div>



                            <% } else {
                                    response.sendRedirect("/OptimalPathTripAdvisorSystem/WelcomeControlServlet");
                                }
                            %>

                        </div>
                    </div>
                </div>


               
                <div id="copyright"></div>
            </div>
        </div>

       
        <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="scripts/jquery.flexslider-min.js"></script>
        <script type="text/javascript" src="scripts/jquery.hoverIntent.minified.js"></script>
        <script type="text/javascript" src="scripts/superfish.js"></script>
        <script type="text/javascript" src="scripts/jquery.cycle.lite.js"></script>
        <script type="text/javascript" src="scripts/jquery.fancybox-1.3.4.pack.js"></script>
        <script type="text/javascript" src="scripts/jquery.validationEngine.js"></script>
        <script type="text/javascript" src="scripts/jquery.validationEngine-en.js"></script>
        <script type="text/javascript" src="scripts/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="scripts/ui.spinner.min.js"></script>
        <script type="text/javascript" src="scripts/lamoon.js"></script>

    </body>
</html>
