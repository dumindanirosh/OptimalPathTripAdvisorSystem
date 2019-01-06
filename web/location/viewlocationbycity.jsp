
<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.UserDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="com.optimalpath.duminda.util.GoogleMapLocationDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Optimal Path Trip Advisor</title>
        <meta charset="utf-8">
   
        <link rel="stylesheet" href="css/base.css" />
        <link rel="stylesheet" href="css/skeleton.css" />
        <link rel="stylesheet" href="css/flexslider.css" />
        <link rel="stylesheet" href="css/jquery.fancybox-1.3.4.css" />
        <link rel="stylesheet" href="css/lamoon.css" />
    </head>
    <%! List<LocationDetails> locationDetails;
        List<GoogleMapLocationDetails> googleMapLocationDetails;
        String locationCity;
        List<LocationCategoryDetails> listOfCategories;
        List<String> locationCityList;
    %>
    <body>
        <%  locationCity = (String) request.getAttribute("locationCity");
        %>
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
                              locationCityList = (List<String>)session.getAttribute("locationCityList");
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
                              locationCityList = (List<String>)session.getAttribute("locationCityList");
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

                <%}%> 
              
                <div id="content" class="room-content">
                    <div id="intro">
                        <h1><span>Location City : <%=locationCity%></span></h1>


                        <%
                            locationDetails = (List<LocationDetails>) request.getAttribute("locationByCityList");

                            for (LocationDetails locationD : locationDetails) {
                                String imageURL = "locationimages/" + locationD.getMainImageUrl();
                        %>
                    </div>
                    <div class="container section" style="border: #212121 dashed">
                        <div class="one-third column">
                            <img src=<%=imageURL%> width="300" height="300" alt=""/><br/>

                        </div>
                        <div class="two-third column last">
                            <h3 class="nobg">
                                <br>
                                 <%=locationD.getLocationName()%> <br/>
                                
                                Category Type : <%=locationD.getLocationCategoryType()%><br/>
                                City :<%=locationD.getLocationCity()%> <br/>
                                District :<%=locationD.getLocationDistrict()%> <br/>
                            </h3>                      
                            <p>
                                <a href="LocationControlServlet?action=viewmorelocationdetails&locationId=<%=locationD.getLocationId()%>" class="small gray button">View More</a>
                                &nbsp;
                                <a href="LocationControlServlet?action=viewonmap&locationId=<%=locationD.getLocationId()%>" class="small steel-blue button">View On Map</a>
                            </p>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>

                <div id="copyright">
                    <div class="container section end">

                        <span id="text">All Rights Reserved.</span>

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
        <script type="text/javascript" src="scripts/jquery.fancybox-1.3.4.pack.js"></script>
        <script type="text/javascript" src="scripts/lamoon.js"></script>

    </body>
</html>

