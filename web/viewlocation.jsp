

<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.GoogleMapLocationDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="com.optimalpath.duminda.util.UserDetails"%>
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
    <%!        LocationDetails locationD;
        GoogleMapLocationDetails googleMapLocationD;
        List<String> locationCityList;
        List<LocationCategoryDetails> listOfCategories;
    %>
    <body>
        <%
            locationD = (LocationDetails) request.getAttribute("locationDetail");
            googleMapLocationD = (GoogleMapLocationDetails) request.getAttribute("googleMapLocationDetail");
        
            String imageURL = "locationimages/" + locationD.getMainImageUrl();


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
<h2 class="nobg" align="middle" style="font-size: 30px">Location Information</h2>
                    <div class="container section" >
                        <div class="half column">                        
                            <img src="<%=imageURL%>" width="400px" height="400px" alt=""/><br/>
                           

                        </div>
                        <div class="half column last">
                            <label style="font-size: 24px"><%=locationD.getLocationName()%></label>
                      
                            <table>
                                <tbody>
                                    <tr>
                                        <td><label style="font-size: 20px">Description :</label></td>                                        </td>
                                        <td> <label style="font-size: 20px"><%=locationD.getLocationDescription()%></label></td>

                                    </tr>
                                    <tr>
                                        <td><label style="font-size: 20px">Category Type :</label></td>                                        </td>
                                        <td> <label style="font-size: 20px"><%=locationD.getLocationCategoryType()%></label></td>

                                    </tr>
                                    <tr>
                                        <td><label style="font-size: 20px">City :</label></td>                                        </td>
                                        <td> <label style="font-size: 20px"><%=locationD.getLocationCity()%></label></td>

                                    </tr>
                                    <tr>
                                        <td><label style="font-size: 20px">District :</label></td>                                        </td>
                                        <td> <label style="font-size: 20px"><%=locationD.getLocationDistrict()%></label></td>

                                    </tr>

                                </tbody>
                            </table>
                            
                            
                            <input type="hidden" name="locationId"  value="<%=locationD.getLocationId()%>"  /><br>
                            <% String id = Integer.toString(locationD.getLocationId());
                            %>
                            <h3 class="nobg extra-margin top">
                                <p class="center extra-margin top">



                                    <a href="LocationControlServlet?action=viewlocationtoedit&locationId=<%=id%>" class="medium steel-blue button">Edit Location</a>
                                    <a href="LocationControlServlet?action=deletelocation&locationId=<%=id%>" class="medium steel-blue button" onclick="return confirm('Are you sure want to delete');">Delete Location</a>

                                </p>
                            </h3>


                        </div>

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

