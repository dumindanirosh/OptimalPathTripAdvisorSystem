

<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
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
        String displayMessage;
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
                        
                    }
                    
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
                            
                            


               
                <div id="content">
                    <div id="intro">
                        <h3>
                            <%  displayMessage = (String) request.getAttribute(BackendConstants.MESSAGE);%>

                            <%=displayMessage%> 
                            <br/>
                            <a href ="/OptimalPathTripAdvisorSystem/WelcomeControlServlet">Back to main menu</a>



                        </h3>
                    </div>
                       
                        <div id="footer">

                        </div>
                        <div id="copyright" >
                            <div class="container section end" align ="middle">
                                <span id="text" >All Rights Reserved.</span>
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
