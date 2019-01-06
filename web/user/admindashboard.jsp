

<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.util.UserDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
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
    <%! List<LocationDetails> locationList;
        List<LocationCategoryDetails> listOfCategories;
        List<LocationDetails> hotLocatonList;
        List<String> locationCityList;
    %>
    <body>
        <img src="images/bg111.jpg" class="bg" alt="" />
        <div id="root-container" class="container">
            <div id="wrapper" class="sixteen columns">
                <div id="banner" style="height: 450px">
                    <div id="logo">
                        
                    </div>
                    <div id="banner-slider" class="flexslider" style="height: 450px">
                        <ul class="slides" style="height: 450px">
                            <li style="height: 450px">
                                <img src="images/banner/banner1.jpg" alt="" />
                                <div class="left black banner-caption">
                                    <h2>Enjoy Your Tour</h2>
                                    <p>
                                        
                                    </p>
                                </div>
                            </li>
                            <li>
                                <img src="images/banner/banner2.jpg" alt="" />
                                <div class="left white banner-caption">
                                    
                                    <p>
                                       
                                    </p>
                                </div>
                            </li>
                            <li>
                                <img src="images/banner/banner3.jpg" alt="" />
                                <div class="right white banner-caption">
                                    <h2>Be your own travel guide and create your trip now</h2>
                                    <p>
                                       
                                    </p>
                                </div>
                            </li>
                        </ul>
                    </div>

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
               
                <div id="content">
                    <div id="intro">
                        <h1><span>Welcome to the Tour Planner</span></h1>
                    </div>

                    <div class="container admindashboard" >
                        <table  border="1">
                            <tbody>
                                <tr>
                                    <td><h2>Location Related</h2></td>
                                    <td><h2>User Related</h2></td>
                                    <td><h2>Feedback Related</h2></td>
                                </tr>
                                <tr> 
                                    <td><a href="LocationControlServlet?action=viewlocationcategories" style="font-size: 16pt" >Add New Location</a></td>
                                    <td><a href="forwardAction?forwardPage=user/adduser.jsp" style="font-size: 16pt">Add New User</a></td>
                                    <td><a href="FeedbackControlServlet?action=viewallinquiries" style="font-size: 16pt" >Display All Inquiries</a></td>
                                </tr>
                                <tr>
                                    <td><a href="LocationControlServlet?action=viewlocationids" style="font-size: 16pt">Display Particular Location</a></td>
                                    <td><a href="UserControlServlet?action=viewallusers" style="font-size: 16pt" >Display All Users</a></td>
                                </tr>
                                <tr>
                                    <td><a href="LocationControlServlet?action=viewalllocations" style="font-size: 16pt" >Display all Locations</a></td>
                                    <td><a href="UserControlServlet?action=viewallmembers" style="font-size: 16pt" >Display All Members</a></td>
                                </tr>
                                <tr>
                                    <td><a href="forwardAction?forwardPage=location/addlocationcategory.jsp" style="font-size: 16pt" >New Location Category</a></td>
                                    <td><a href="UserControlServlet?action=viewallofficerss" style="font-size: 16pt" >Display All Officers</a></td>
                                </tr>
                                <tr>
                                    <td><a href="LocationControlServlet?action=viewlocationcategoriestodelete" style="font-size: 16pt" >Delete Location Category</a></td>
                                </tr>
                               
                                <tr>
                                    <td><a href="LocationControlServlet?action=viewlocationcnames" style="font-size: 16pt" >New Hot Location</a></td>

                                </tr>
                               
                               <tr> 
                                    <td><a href="forwardAction?forwardPage=route/getdistancetime1.jsp" style="font-size: 16pt" >Add Distance</a></td>
                                    
                                   
                                </tr>
                            </tbody>
                        </table>
                    </div>



                   
                    <div id="feature" class="container section">
                        <h1 align ="middle">Hot Locations</h1>
                        <%
                            int x = 0;
                            hotLocatonList = (List<LocationDetails>) session.getAttribute("hotLocationDetails");
                            Iterator<LocationDetails> iter = hotLocatonList.iterator();
                            while (iter.hasNext()) {
                                LocationDetails hotLocation = iter.next();
                                if (x < 8) {
                                    String imageURL = BackendConstants.IMAGE_VIEW_PATH + hotLocation.getMainImageUrl();
                        %>
                        <div class="one-fourth column">
                            <div class="one-fourth "> 
                                <div class="one-fourth " style="height: 40px"> 
                                    <h3> <%=hotLocation.getLocationName()%></h3></div>
                                <a href=LocationControlServlet?action=viewmorelocationdetails&locationId=<%=hotLocation.getLocationId()%>>
                               <br>     <p>        
                                        <img src="<%=imageURL%>" width="200px" height="200px" alt=""/>
                                    </p></a>
                            </div>
                            <h3><span>

                                    <a href="LocationControlServlet?action=viewmorelocationdetails&locationId=<%=hotLocation.getLocationId()%>">View More</a>
                                </span></h3>
                        </div>
                        <% ++x;
                                }
                            }%>
                    </div>


                    <div id="feature" class="container section">
                        <h1 align ="middle">All Locations</h1>
                        <%
                            locationList = (List<LocationDetails>) session.getAttribute("locationList");
                            Iterator<LocationDetails> ite = locationList.iterator();
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


        <% } else {
                response.sendRedirect("/WelcomeControlServlet");
            }
        %>
        
        <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="scripts/jquery.flexslider-min.js"></script>
        <script type="text/javascript" src="scripts/jquery.hoverIntent.minified.js"></script>
        <script type="text/javascript" src="scripts/superfish.js"></script>
        <script type="text/javascript" src="scripts/jquery.cycle.lite.js"></script>
        <script type="text/javascript" src="scripts/lamoon.js"></script>

    </body>
</html>
