

<%@page import="com.optimalpath.duminda.model.User"%>
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
        <script src="http://maps.google.com/maps/api/js?sensor=false" 
        type="text/javascript"></script>
    </head>
    <%! List<LocationDetails> locationList;
        List<LocationCategoryDetails> listOfCategories;
        List<LocationDetails> hotLocatonList;
        List<String> locationCityList;
    %>
    <style>
        #panel {
            position: absolute;
            top: 5px;
            left: 50%;
            margin-left: -180px;
            z-index: 5;
            background-color: #fff;
            padding: 5px;
            border: 1px solid #999;
        }
        #directions-panel {
            height: 100%;
            float: right;
            width: 390px;
            overflow: auto;
        }
        #directions-panel {
            float: none;
            width: auto;
        }
    </style>

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
                            <a>Attraction City</a>
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
                                <li>
                                    <a href=""><span>&nbsp;&nbsp;&nbsp;- </span>Wildlife</a>
                                </li>
                                <li>
                                    <a href=""><span>&nbsp;&nbsp;&nbsp;- </span>Beach</a>
                                </li>
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
                                    <a href="LocationControlServlet?action=viewlocationbycity&locationCity=<%=temp%>"><%=temp%></a>
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

                <%}
                    Integer tourPlannerListID = (Integer) request.getAttribute("tourPlannerListID");
                    
                %> 

                <div class="content">
                    <h1 align ="middle">Optimal Route</h1>
                    <h2 align="middle" style="font: 22px">
                    <a href="TourPlanListControlServlet?action=viewschedule&tourplannerlistid=<%=tourPlannerListID.toString()%>" >View Schedule</a>
                    </h2>       
                    <br/>
                    <div class="two-third column" style="margin: 0px">
                        <div id="map1" style="width: 720px; height: 1000px;" ></div>
                    </div>
                    <div class="one-third column last">
                        <div id="directions-panel" style="width: 500px; height: 1000px; margin: 0px" ></div>
                    </div>
                </div>
                <div id="content">

                    <div id="footer">
                        <div class="container section end">


                            <a href="feedback/addcontactus.jsp">Contact Us</a>


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



        <%
            String locatios[] = (String[]) request.getAttribute("locationArray");



        %>
        <script>

            var locations = [];
            var jsArray = new Array();
            <%for (int i = 0; i < locatios.length; i++) {%>
            jsArray.push("<%= locatios[i]%>");

            <%}%>
        </script>

        <script>
            var locations2;
            var i;
            locations2 = [];
            for (i = 0; i < jsArray.length; i++) {
                var places = jsArray[i];
                var p = places.split("-");
                locations2[i] = p;
                locations[i] = [locations2[i][0], parseFloat(locations2[i][1]), parseFloat(locations2[i][2]), parseFloat(locations2[i][3])];
            }


            var directionsDisplay;

            directionsDisplay = new google.maps.DirectionsRenderer();
            var mapOptions = {
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                center: new google.maps.LatLng(7.9569, 80.7597),
            };

            var map = new google.maps.Map(document.getElementById('map1'),
                    mapOptions);
            directionsDisplay.setMap(map);
            directionsDisplay.setPanel(document.getElementById('directions-panel'));
            calcRoute();

            function calcRoute() {
                var start = locations2[0][0];
                var end = locations2[0][0];
                var selectedLocation = [];
                for (var h = 1; h < locations2.length - 1; h++) {
                    selectedLocation[h - 1] = locations2[h][0];
                }

                var waypts = [];
                for (var gh = 0; gh < selectedLocation.length; gh++) {
                    var locs = selectedLocation[gh];
                    waypts.push({
                        location: locs,
                        stopover: true});
                }


                var request = {
                    origin: start,
                    destination: end,
                    waypoints: waypts,
                    travelMode: google.maps.TravelMode.DRIVING
                };

                var rendererOptions = {
                    preserveViewport: true,
                    suppressMarkers: false,
                };

                directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
                directionsDisplay.setMap(map);
                directionsDisplay.setPanel(document.getElementById('directions-panel'));
                var directionsService = new google.maps.DirectionsService();
                directionsService.route(request, function(result, status) {
                    if (status == google.maps.DirectionsStatus.OK) {
                        directionsDisplay.setDirections(result);
                    }
                });

            }



        </script>




        <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="scripts/jquery.flexslider-min.js"></script>
        <script type="text/javascript" src="scripts/jquery.hoverIntent.minified.js"></script>
        <script type="text/javascript" src="scripts/superfish.js"></script>
        <script type="text/javascript" src="scripts/jquery.cycle.lite.js"></script>
        <script type="text/javascript" src="scripts/lamoon.js"></script>

    </body>
</html>
