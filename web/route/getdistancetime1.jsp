

<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.UserDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
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
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>


        <script type="text/javascript">
            var location1;
            var location2;
            var address1;
            var address2;
            var latlng;
            var geocoder;
            var map;
            var distance;
            var d;
            function initialize()
            {
                geocoder = new google.maps.Geocoder();
                address1 = document.getElementById("address1").value;
                address2 = document.getElementById("address2").value;
                if (geocoder)
                {
                    geocoder.geocode({'address': address1}, function(results, status)
                    {
                        if (status == google.maps.GeocoderStatus.OK)
                        {
                            location1 = results[0].geometry.location;
                        } else
                        {
                            alert("Geocode was not successful for the following reason: " + status);
                        }
                    });
                    geocoder.geocode({'address': address2}, function(results, status)
                    {
                        if (status == google.maps.GeocoderStatus.OK)
                        {
                            location2 = results[0].geometry.location;
                            showMap();
                        } else
                        {
                            alert("Geocode was not successful for the following reason: " + status);
                        }
                    });
                }
            }

            function showMap()
            {
                latlng = new google.maps.LatLng((location1.lat() + location2.lat()) / 2, (location1.lng() + location2.lng()) / 2);

                var mapOptions =
                        {
                            zoom: 2,
                            center: latlng,
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        };

                map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

                directionsService = new google.maps.DirectionsService();
                directionsDisplay = new google.maps.DirectionsRenderer(
                        {
                            suppressMarkers: true,
                            suppressInfoWindows: true
                        });
                directionsDisplay.setMap(map);
                var request = {
                    origin: location1,
                    destination: location2,
                    travelMode: google.maps.DirectionsTravelMode.DRIVING
                };
                directionsService.route(request, function(response, status)
                {
                    if (status == google.maps.DirectionsStatus.OK)
                    {
                        directionsDisplay.setDirections(response);
                        distance = "The distance between the two location is: " + response.routes[0].legs[0].distance.text;
                        distance += "<br/>The driving time is: " + response.routes[0].legs[0].duration.text;
                        document.getElementById("distance_road").innerHTML = distance;
                        d = (response.routes[0].legs[0].distance.value) / 1000;
                        d1 = (response.routes[0].legs[0].distance.value + "meters");
                        document.getElementById("dis").value = d;
                        t = (response.routes[0].legs[0].duration.value) / 3780;
                        document.getElementById("time").value = t;
                    }
                });
                var marker1 = new google.maps.Marker({
                    map: map,
                    position: location1,
                    title: "First location"
                });
                var marker2 = new google.maps.Marker({
                    map: map,
                    position: location2,
                    title: "Second location"
                });
                
                
                var text1 = '<div id="content">' +
                        '<h1 id="firstHeading">First location</h1>' +
                        '<div id="bodyContent">' +
                        '<p>Coordinates: ' + location1 + '</p>' +
                        '<p>Address: ' + address1 + '</p>' +
                        '</div>' +
                        '</div>';
                var text2 = '<div id="content">' +
                        '<h1 id="firstHeading">Second location</h1>' +
                        '<div id="bodyContent">' +
                        '<p>Coordinates: ' + location2 + '</p>' +
                        '<p>Address: ' + address2 + '</p>' +
                        '</div>' +
                        '</div>';
                var infowindow1 = new google.maps.InfoWindow({
                    content: text1
                });
                var infowindow2 = new google.maps.InfoWindow({
                    content: text2
                });
                google.maps.event.addListener(marker1, 'click', function() {
                    infowindow1.open(map, marker1);
                });
                google.maps.event.addListener(marker2, 'click', function() {
                    infowindow2.open(map, marker2);
                });
            }
        </script>

    </head>

    <%!        List<LocationCategoryDetails> listOfCategories;
        String displayMessage;
        List<String> locationCityList;
        List<LocationDetails> locationD;
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
                    <div class="container section">
                        <div class="full-width column">
                            <%
                                locationD = (List<LocationDetails>) session.getAttribute("locationList");

                                Iterator<LocationDetails> it11 = locationD.iterator();
                                Iterator<LocationDetails> it22 = locationD.iterator();
                            %>
                            <div id="form" style="width:100%; height:20%">
                                <form action="/OptimalPathTripAdvisorSystem/DistanceTimeControlServlet" method="POST">
                                    <table align="center" valign="center">
                                        <tr>
                                            <td>First address:</td>
                                            <td><select name="address1" id="address1">
                                                    <%
                                                        while (it11.hasNext()) {
                                                            LocationDetails locationDetail = it11.next();
                                                    %>
                                                    <option  >
                                                        <% out.println(locationDetail.getLocationName());%>
                                                    </option>
                                                    <% }%>
                                                </select></td>
                                            <td>Second address:</td>
                                            <td><select name="address2" id="address2">
                                                    <%
                                                        while (it22.hasNext()) {
                                                            LocationDetails locationDetail = it22.next();
                                                    %>
                                                    <option  >
                                                        <%= locationDetail.getLocationName()%>
                                                    </option>
                                                    <% }%>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                    <table>
                                        <tr>
                                            <td colspan="7" align="center"><input type="button" value="Show" onclick="initialize();"/></td>
                                        </tr>
                                        <tr>
                                            <td>Distance :</td>
                                            <td><input type="text" id="dis" name="dis"></td>
                                            <td>Time :</td>
                                            <td> <input type="text" id="time" name="time"></td>

                                            <td>
                                                <input type = "hidden" name = "action" value ="adddistance">
                                                <input type="submit" id="submit" class="medium gray button" value="Submit" />  
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                        <div class="full-width column" style="width: 100%; height: auto">
                            <p>  <center>  <div style="width:100%; height:10%" id="distance_road"></div></center></p>

                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <div class="full-width column" style="width: 100%; height: 900px">
                            <div id="map_canvas" style="width:100%; height:94%"></div>
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


