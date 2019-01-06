

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
        <script src="http://maps.google.com/maps/api/js?sensor=false" 
        type="text/javascript"></script>
    </head>
    <%! List<LocationDetails> locationList;
        List<LocationCategoryDetails> listOfCategories;
        List<LocationDetails> hotLocatonList;
        String locationCity;
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

                <%}%> 

                <%
                    String locatios[] = (String[]) request.getAttribute("locationArray");
                    locationCity = (String) request.getAttribute("locationCity");

                %>
             
                <div id="content">
                    <h1 align ="middle">Select Locations From City <%=locationCity%></h1>
                    <div id="map" style="width: 1240px; height: 1000px;" align ="center"></div>
                   
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
        </script>
        <script type="text/javascript">
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 8,
                center: new google.maps.LatLng(7.9569, 80.7597),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });
            var contentString;
            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });
            var marker, i;
            for (i = 0; i < locations.length; i++) {
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                    map: map
                });
                google.maps.event.addListener(marker, 'click', (function(marker, i) {
                    return function() {
                        contentString = '<div id="content">' +
                                '<div id="siteNotice">' +
                                '</div>' +
                                '<h2 id="firstHeading" class="firstHeading">' + (locations[i][0]) + '</h2>' +
                                '<div id="bodyContent">' +
                                '<a href=TourPlanListControlServlet?action=checkexisitngfrommap&locationId=' + locations[i][3] + '>' +
                                'Add To Tour</a> ' +
                                '<a href=LocationControlServlet?action=viewmorelocationdetails&locationId=' + locations[i][3] + '>' +
                                'View More</a> '
                                '</div>' +
                                '</div>';
                        infowindow.setContent(contentString);
                        infowindow.open(map, marker);
                    }
                })(marker, i));
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
