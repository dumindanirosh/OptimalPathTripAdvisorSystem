

<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.util.UserDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
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
    <%!        List<LocationDetails> locationDetails;
        List<LocationCategoryDetails> listOfCategories;
        LocationDetails locatinDetails;
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


                <div id="content" class="room-content">
                    <div id="intro">
                        <ul>  



                        </ul>
                    </div>
                    <div class="container section" >


                        <div id="intro" align="center" >     
                            <%
                                locatinDetails = (LocationDetails) request.getAttribute("locationDetail");


                            %>
                            </form>  

                            <h3>Update Location Details</h3>
                            <form action="/OptimalPathTripAdvisorSystem/LocationControlServlet" method="POST">
                                <table border="1" style="border: #212121 dashed; font: 20px">
                                    <tbody>

                                        <tr>
                                            <td><label style="font-size: 20px"> Location name :</label></td>
                                            <td> <input type="text" name="locationName" value="<%=locatinDetails.getLocationName()%>" /></td>
                                        </tr>
                                        <tr>
                                            <td><label style="font-size: 20px">Location Description :</label></td>
                                            <td><input type="text" name="locationDescription" value="<%=locatinDetails.getLocationDescription()%>" />  </td>
                                        </tr>
                                        <tr>
                                            <td><label style="font-size: 20px">Location City :</label></td>
                                            <td><input type="text" name="locationCity" value="<%=locatinDetails.getLocationCity()%> " /></td>
                                        </tr>
                                        <tr>
                                            <td><label style="font-size: 20px">Location District :</label></td>
                                            <td><input type="text" name="locationDistrict" value="<%=locatinDetails.getLocationDistrict()%>" /></td>
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
                                    <input type="hidden" name="enteredBy" value="Arlene" /><br>
                                    <input type="hidden" name="locationId" value="<%=request.getParameter("locationId")%>"/><br>
                                    <input type = "hidden" name = "action" value ="editlocation">

                                    <input type="submit" value="Submit"  class="large blue button " />
                                    <input type="reset" value="Reset"  class="large blue button " />  
                                </div>
                            </form>       

                        </div>
                    </div>
                </div>
                <% } else {
                        response.sendRedirect("WelcomeControlServlet");
                    }
                %>
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
