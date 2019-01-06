

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
        <title>Optimal Path Trip Advisor </title>
        <script src="http://maps.google.com/maps/api/js?sensor=false" 
        type="text/javascript"></script> 

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
        List<LocationDetails> locationD;
        List<LocationCategoryDetails> locationCategoryDetail;
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




                <div id="content" >
                    <div id="intro">
                        <h1><span> Add Location</span></h1>

                    </div>
                    <div class="container section" >


                        <div id="intro" align="center" >                        
                            <form action="LocationControlServlet" method="POST">
                                <table border="1">

                                    <tbody>
                                        <tr>
                                            <td><label for="locationName" style="font-size: 20px" > Location Name :</label></td>
                                            <td> <input type="text" name="locationName" required="true"    /></td>
                                        </tr>
                                        <tr>
                                            <td><label for="locationDescription" style="font-size: 20px"> Location Description :</label></td>
                                            <td> <input type="text" name="locationDescription"  required="true"    /></td>
                                        </tr>
                                        <tr>
                                            <td><label for="firstname" style="font-size: 20px">Location Category Type :  </label></td>
                                            <td> 
                                                <select id="locationcatagoryid" name="locationCategoryType" >
                                                    <%
                                                        locationCategoryDetail = (List<LocationCategoryDetails>) request.getAttribute("categoryList");

                                                        Iterator<LocationCategoryDetails> it1 = locationCategoryDetail.iterator();
                                                        while (it1.hasNext()) {
                                                            LocationCategoryDetails locationCategory = it1.next();
                                                    %>
                                                    <option value="<%= locationCategory.getLocationCategoryId().toString()%>">
                                                        <%= locationCategory.getLocationCategoryType()%>
                                                    </option>
                                                    <% }%>
                                                </select>
                                        </tr>
                                        <tr>
                                            <td><label for="locationCity" style="font-size: 20px">Location City : </label></td>
                                            <td> <input type="text" name="locationCity"  required="true"   /></td>
                                        </tr>
                                        <tr>
                                            <td><label for="locationDistrict" style="font-size: 20px">Location District : </label></td>
                                            <td> <input type="text" name="locationDistrict"  /></td>
                                        </tr>
                                        <tr>
                                            <td><label for="visitingTime" style="font-size: 20px">Visiting Duration : </label></td>
                                            <td> <input type="text" name="visitingTime"  /></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div id="map" style="width: 400px; height: 300px;"></div> 
                                                <script type="text/javascript">
                                                    var map = new google.maps.Map(document.getElementById('map'), {
                                                        mapTypeId: google.maps.MapTypeId.TERRAIN,
                                                        center: new google.maps.LatLng(7.0000, 81.0000),
                                                        zoom: 6
                                                    });
                                                    var location1;
                                                    var geocoder = new google.maps.Geocoder();

                                                    function geocode() {
                                                        geocoder.geocode({
                                                            'address': document.getElementById('search').value
                                                        },
                                                        function(results, status) {
                                                            if (status == google.maps.GeocoderStatus.OK) {
                                                                location1 = results[0].geometry.location;
                                                                address = document.getElementById("search").value;
                                                                new google.maps.Marker({
                                                                    position: results[0].geometry.location,
                                                                    map: map
                                                                });
                                                                map.setCenter(results[0].geometry.location);
                                                            }

                                                            var lattitude = +location1.lat();
                                                            var longitude = +location1.lng();
                                                            document.getElementById("address").value = address;
                                                            document.getElementById("lattitude").value = lattitude;
                                                            document.getElementById("longitude").value = longitude;
                                                        });
                                                    }
                                                </script> 
                                            </td>
                                            <td>
                                                <input type="text" id="search">
                                                <input type="button" onclick="geocode();" value="Search">
                                            </td>   
                                        </tr>
                                        <tr>
                                            <td><label for="locationGoogleMapName" style="font-size: 20px"> Location Google Map Name :</label></td>
                                            <td> <input type="text" id="address" name="locationGoogleMapName"  /></td>
                                        </tr>
                                        <tr>
                                            <td><label for="latitude" style="font-size: 20px">Latitude : </label></td>
                                            <td> <input type="text" id="lattitude" name="latitude"   /></td>
                                        </tr>
                                        <tr>
                                            <td><label for="longitude" style="font-size: 20px">Longitude :  </label></td>
                                            <td> <input type="text" id="longitude" name="longitude"  /></td>
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


                                    <input type="hidden" name="enteredBy" value="<%=user.getUsername()%>" />
                                    <input type = "hidden" name = "action" value ="addlocation">
                                    <input type="submit" id="submit" class="medium gray button" value="Submit" />
                                    <input type="reset" id="submit" class="medium gray button" value="Reset" /> 




                                </div>
                            </form>
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
