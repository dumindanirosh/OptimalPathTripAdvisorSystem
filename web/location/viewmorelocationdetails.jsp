

<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="com.optimalpath.duminda.util.ReviewDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationImageDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
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
       // GoogleMapLocationDetails googleMapLocationD;
        List<LocationImageDetails> locationImageDetailses;
        List<ReviewDetails> reviewDetails;
        List<LocationCategoryDetails> listOfCategories;
        List<String> locationCityList;
    %>
    <body>
        <%
            locationD = (LocationDetails) request.getAttribute("locationDetail");
            //googleMapLocationD = (GoogleMapLocationDetails) request.getAttribute("details");
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

                <%}%> 


                <div id="content" class="room-content">
                    <h2 class="nobg" align="middle" style="font-size: 30px">Location Information</h2>
                    <div class="container section" >
                        <div class="half column">                        
                            <img src="<%=imageURL%>" width="400px" height="400px" alt=""/><br/>
                            <%
                                locationImageDetailses = (List<LocationImageDetails>) request.getAttribute("locationImageDetailses");

                                for (LocationImageDetails locationIma : locationImageDetailses) {
                                    String imageURL1 = "locationimages/" + locationIma.getLocationImageUrl();

                            %>

                            <img src="<%=imageURL1%>" width="260px" height="170px" alt=""/><br/>
                            <%
                                }
                            %>

                        </div>
                        <div class="half column last">

                           

                                <label style="font-size: 24px"><%=locationD.getLocationName()%></label>

                                <br>

                            <table>
                                <tbody>
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
                                    <tr>
                                        <td><label style="font-size: 20px">Cover Up Time Maximum :</label></td>                                        </td>
                                        <td> <label style="font-size: 20px"><%=locationD.getVisitingDuration()%> &nbsp;hrs</label></td>

                                    </tr>

                                </tbody>
                            </table>

                            <%
                                String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);
                                if (errorMessage != null) {
                                    out.println(errorMessage);
                                } else {
                                    String d = (String) request.getAttribute("des");

                            %>
                            <table>
                                <tbody>
                                    <tr>
                                        <td><label style="font-size: 20px"><%=d%></label></td>                                        </td>


                                    </tr>

                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                            <input type="hidden" name="locationId"  value="<%=locationD.getLocationId()%>"  /><br>
                            <% String id = Integer.toString(locationD.getLocationId());
                            %>
                            <h3 class="nobg extra-margin top">
                                <p class="center extra-margin top">
                                    <a href="LocationControlServlet?action=viewonmap&locationId=<%=locationD.getLocationId()%>" class="medium steel-blue button">View On Map</a>
                                    <a href="TourPlanListControlServlet?action=checkexisitng&locationId=<%=id%>" class="medium steel-blue button">Add To Tour</a>
                                    <a href="forwardAction?forwardPage=feedback/addreview.jsp?locationId=<%=id%>" class="medium steel-blue button">Add Review</a>
                                </p>
                            </h3>


                        </div>

                    </div>

                    <%
                        reviewDetails = (List<ReviewDetails>) request.getAttribute("reviewDetails");

                        for (ReviewDetails details : reviewDetails) {
                    %>
                    <div id="comment-content" class="end">
                        <h4> Comments</h4>
                        <div class="comment-item">
                            <div class="commenter-photo">
                                <img src="images/blog/anonymous.jpg" alt="" />
                            </div>
                            <div class="comment-post">
                                <p class="comment-item-meta">

                                    <span class="commenter-name">  <%=details.getUsername()%> </span>

                                    </span>
                                </p>
                                <p>
                                    <%=details.getComment()%>
                                    <%}%>

                                </p>
                            </div>
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

