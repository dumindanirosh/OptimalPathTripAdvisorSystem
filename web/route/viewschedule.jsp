

<%@page import="com.optimalpath.duminda.optimalroutefinder.Scheduler"%>
<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.TourPlannerListDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <title>Optimal Path Trip Advisor</title>


        <link rel="stylesheet" href="css/skeleton.css" />
        <link rel="stylesheet" href="css/flexslider.css" />
        <link rel="stylesheet" href="css/lamoon.css" />
        <link rel="stylesheet" href="css/base.css" />


    </head>
    <%!        LocationDetails location;
        List<LocationDetails> locationList;
        List<LocationCategoryDetails> listOfCategories;
        List<TourPlannerListDetails> list;
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



                <div id="content" class="reservation">
                    <div class="container section">


<!--                        <div id="intro">-->
                            <h3 class="nobg" align="middle">View Schedule</h3>
                            <h3 class="extra-margin top" align="middle"><span class="center">Tour Info</span></h3>
                            <table border="1">
                                <tbody>
                                    <tr>
                                        <td> <label style="font-size: 20px">Start  Location</label></td>
                                        <td><label style="font-size: 20px"> End Location</label></td>
                                        <td><label style="font-size: 20px"> Location Distance(Km)</label></td>
                                         <td><label style="font-size: 20px"> Travel Duration(H) </label></td>
                                        <td><label style="font-size: 20px"> Visiting Duration(H) </label></td>
                                        <td><label style="font-size: 20px"> Arriving Date/Time</label></td>
                                        <td><label style="font-size: 20px"> Departure Date/Time </label>

                                    </tr>
                                    <% ArrayList<Scheduler> schedulerList = (ArrayList) request.getAttribute("dailySchedulers");
                                        for (Scheduler scheduler : schedulerList) {


                                    %>
                                    <tr>
                                        <td><%=scheduler.getLocation1()%></td>
                                        <td><%=scheduler.getLocation2()%></td>
                                         <td><%=scheduler.getLocationDistance()%>Km</td>
                                        <td><%=scheduler.getTravelDuration()%>H</td>
                                        <td><% 
                                            
                                            double duration = scheduler.getVisitingDuration();
                                            if(duration > 0){
                                                out.println(duration + "H");
                                            }else{
                                                out.println("Not Selected To Visit");
                                            }
                                        %></td>
                                        <td><%=scheduler.getArrivingTime()%></td>
                                        <td><%=scheduler.getVistingTime()%></td>
                                    </tr>

                                    <%}%>
                                </tbody>
                            </table>
                            <br/>
                            <div align="middle">
                                <a href="TourPlanListControlServlet?action=addnewtourdetails" class="large gray button">Start New Tour</a>
                                 <a href="/OptimalPathTripAdvisorSystem/WelcomeControlServlet" class="large gray button">Back To Main</a>
                            </div>

                            <% } else {
                                    response.sendRedirect("/OptimalPathTripAdvisorSystem/WelcomeControlServlet");
                                }
                            %>
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
