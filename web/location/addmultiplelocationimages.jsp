
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
        <title>Optimal Path Trip Advisor </title>

        <meta charset="utf-8">

        <link rel="stylesheet" href="./css/base.css" />
        <link rel="stylesheet" href="./css/skeleton.css" />
        <link rel="stylesheet" href="./css/flexslider.css" />
        <link rel="stylesheet" href="./css/jquery.fancybox-1.3.4.css" />
        <link rel="stylesheet" href="./css/validationEngine.jquery.css" />
        <link rel="stylesheet" href="./css/smoothness/jquery-ui-1.8.22.custom.css" />
        <link rel="stylesheet" href="./css/ui.spinner.css" />
        <link rel="stylesheet" href="./css/lamoon.css" />

    </head>
    <%!            List<LocationCategoryDetails> listOfCategories;
        List<String> locationCityList;
    %>

    <body>

        <%

            Integer locationId = (Integer) request.getAttribute("locationId");
            String locationID = Integer.toString(locationId);
            String locationCategoryID = (String) request.getAttribute("locationCategoryId");



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




                <div id="content" >
                    <div id="intro">
                        <h1><span> Add Multiple Images</span></h1>

                    </div>
                    <div class="container section" >


                        <div id="intro" align="center" style="border: #212121 dashed">     

                            <form method="POST"  action="/OptimalPathTripAdvisorSystem/LocationImageControlServlet?action=addmultiplelocationimages&locationID=<%=locationID%>&locationCategoryID=<%=locationCategoryID%>"
                                  enctype="multipart/form-data">    
                                Image :  <input type="file" name="dataFile"
                                                id="fileChooser" /><br/>
                                <%

                                    String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                    if (errorMessage != null) {
                                        out.println(errorMessage);
                                    }%>
                                <input type="submit" id="submit" class="medium gray button" value="Upload" /><br/>
                            </form>

                            <form method="POST"  action="/OptimalPathTripAdvisorSystem/LocationImageControlServlet?action=addmultiplelocationimages&locationID=<%=locationID%>&locationCategoryID=<%=locationCategoryID%>"
                                  enctype="multipart/form-data">

                                Image :  <input type="file" name="dataFile"
                                                id="fileChooser" /><br/>
                                <%

                                    String errorMessage1 = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                    if (errorMessage1 != null) {
                                        out.println(errorMessage1);
                                    }%>
                                <input type="submit" id="submit" class="medium gray button" value="Upload" /> <br/>
                            </form>

                            <form method="POST"  action="/OptimalPathTripAdvisorSystem/LocationImageControlServlet?action=addmultiplelocationimages&locationID=<%=locationID%>&locationCategoryID=<%=locationCategoryID%>"
                                  enctype="multipart/form-data">
                                Image :  <input type="file" name="dataFile"
                                                id="fileChooser" /><br/>
                                <%
                                    String errorMessage2 = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                    if (errorMessage2 != null) {
                                        out.println(errorMessage2);
                                    }%>
                                <input type="submit" id="submit" class="medium gray button" value="Upload" /> <br/>
                            </form>

                           <a href="LocationControlServlet?action=viewlocationcategories" style="font-size: 16pt; " >Add New Location</a>
                        </div>
 
                    </div>
                </div>

               
                <div id="copyright"></div>
            </div>
        </div>




        <% } else {
                response.sendRedirect("forwardAction?forwardPage=user/login.jsp");
            }
        %>

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
