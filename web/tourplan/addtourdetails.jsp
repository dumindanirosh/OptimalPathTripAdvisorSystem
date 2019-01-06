

<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <title>Optimal Path Trip Advisor</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css" />
        <link rel="stylesheet" href="css/skeleton.css" />
        <link rel="stylesheet" href="css/flexslider.css" />
        <link rel="stylesheet" href="css/lamoon.css" />
        <link rel="stylesheet" href="css/base.css" />

        <script>
            $(function() {
                $("#datepicker").datepicker();
                $("#datepicker1").datepicker();
            });
        </script>


    </head>
    <%!        LocationDetails location;
        List<LocationDetails> locationList;
        List<LocationCategoryDetails> listOfCategories;
        List<String> locationCityList;
    %>
    <body>

        <%
            locationList = (List<LocationDetails>) session.getAttribute("locationList");
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
                                <%
                                    listOfCategories = (List<LocationCategoryDetails>) session.getAttribute("categoryList");
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
                            <form action="/OptimalPathTripAdvisorSystem/TourPlanListControlServlet" method="POST" >
                                <h3 class="extra-margin top"><span class="left">Add New Tour Info</span></h3>
                                <table border="1">
                                    <tbody>
                                        <tr>
                                            <td><label for="checkin" style="font-size: 20px">Arrival Date : </label></td>
                                            <td> <input type="text" name="arrivalDate" id="datepicker"  class=" datepicker" /></td>
                                        </tr>
                                        <tr>
                                            <td> <label for="checkout" style="font-size: 20px">Departure  Date :</label></td>
                                            <td> <input type="text"  name="departureDate" id="datepicker1" /> </td>
                                        </tr>
                                        <tr>
                                            <td><label for="roomamt" style="font-size: 20px">Start Tour :</label></td>
                                            <td> <select id="roomamt" name="startLocation" >
                                                    <% for (LocationDetails location : locationList) {%>

                                                    <option value="<%=location.getLocationId()%>"><%=location.getLocationName()%></option>
                                                    <%}%>
                                                </select> </td>
                                        </tr>
                                        <tr>
                                            <td>  <label for="extrabed" style="font-size: 20px">End Tour : </label></td>
                                            <td> <select id="extrabed" name="endLocation" >
                                                    <% for (LocationDetails location : locationList) {%>

                                                    <option value="<%=location.getLocationId()%>"><%=location.getLocationName()%></option>
                                                    <%}%>
                                                </select> </td>
                                        </tr>

                                    </tbody>
                                </table>
                                <div style="color: #cd0a0a">
                                    <%
                                        String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                        if (errorMessage != null) {
                                            out.println(errorMessage);
                                        }%>
                                </div>

                                <div>

                                    <input type="hidden" name="username" value="<%=user.getUsername()%>" /><br/>
                                    <input type = "hidden" name = "action" value ="addtourdetails">
                                    <input type="submit" id="submit" class="medium gray button" value="Submit" />
                                    <input type="reset" id="submit" class="medium gray button" value="Reset" /> 
                                </div>
                            </form>
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
