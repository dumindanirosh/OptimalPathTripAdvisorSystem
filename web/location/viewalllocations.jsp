
<%@page import="com.optimalpath.duminda.util.GoogleMapLocationDetails"%>
<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.UserDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Optimal Path Trip Advisor </title>
        <link rel="stylesheet" href="css/skeleton.css" />
        <link rel="stylesheet" href="css/flexslider.css" />
        <link rel="stylesheet" href="css/lamoon.css" />
    </head>
    <%!        List<LocationCategoryDetails> listOfCategories;
        List<LocationDetails> locationDetails;
        List<GoogleMapLocationDetails> googleMapLocationDetails;
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

                <div id="content">
                    <div id="intro">
                        <h1><span>Welcome to the Tour Planner</span></h1>
                    </div>
                    <div id="feature" class="container section">
                        <h1 align ="middle">All Locations</h1>
                        <%
                            locationDetails = (List<LocationDetails>) request.getAttribute("locationsList");;
                            Iterator<LocationDetails> ite = locationDetails.iterator();
                            while (ite.hasNext()) {
                                LocationDetails location = ite.next();
                                String imageURL = BackendConstants.IMAGE_VIEW_PATH + location.getMainImageUrl();
                        %>
                        <div class="one-third column">
                            <div class="one-third "> 
                                <a href=LocationControlServlet?action=viewmorelocationdetails&locationId=<%=location.getLocationId()%>>
                                    <p>        
                                        <img src="<%=imageURL%>" width="300px" height="300px" alt=""/>
                                    </p></a>
                            </div>
                            <h3><span>
                                    <%=location.getLocationName()%><br/>
                                    <%=location.getLocationCategoryType()%>
                                    <br/>
                                    <a href="LocationControlServlet?action=viewmorelocationdetails&locationId=<%=location.getLocationId()%>">View More</a>
                                </span></h3>
                        </div>
                        <%
                            }%>
                    </div>



                </div>
                <% } else {
                        response.sendRedirect("/WelcomeControlServlet");
                    }
                %>
               
                <div id="footer">
                    <div class="container section end">


                    </div>
                </div>
                <div id="copyright" >
                    <div class="container section end" align ="middle">
                        <span id="text" >All Rights Reserved.</span>
                    </div>
                </div>
            </div>
        </div>
    </div>



   
    <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="scripts/jquery.flexslider-min.js"></script>
    <script type="text/javascript" src="scripts/jquery.hoverIntent.minified.js"></script>
    <script type="text/javascript" src="scripts/superfish.js"></script>
    <script type="text/javascript" src="scripts/jquery.cycle.lite.js"></script>
    <script type="text/javascript" src="scripts/lamoon.js"></script>

</body>
</html>
