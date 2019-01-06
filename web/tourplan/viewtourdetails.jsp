


<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.model.User"%>
<%@page import="com.optimalpath.duminda.util.TourPlannerListDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
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

        <!-- Background Image -->
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


                        <div id="intro">
                            <h3 class="nobg">Select Tour</h3>
                            <h3 class="extra-margin top"><span class="left">Tour Info</span></h3>
                            <table border="1">
                                <%
                                    String locationId = (String) request.getAttribute("locationId");

                                    list = (List<TourPlannerListDetails>) request.getAttribute("list");
                                    Iterator<TourPlannerListDetails> it1 = list.iterator();
                                    while (it1.hasNext()) {
                                        TourPlannerListDetails add = it1.next();
                                        String tourplannerlistid = Integer.toString(add.getTourPlannerListId());

                                %> 
                                <tbody>
                                    <tr>
                                        <td><label style="font-size: 20px">Tour Planner List ID: </label></td>
                                        <td><%=add.getTourPlannerListId()%></td>
                                        <td>

                                            <a href="TourPlanListControlServlet?action=selecttourplannerlist&tourplannerlistid=<%=tourplannerlistid%>&locationId=<%=locationId%>" >Select</a> 
                                            
                                        </td>
                                        <td><a href="TourPlanListControlServlet?action=viewtourplannerlist&tourplannerlistid=<%=tourplannerlistid%>&locationId=<%=locationId%>" >View</a> </td>
                                    </tr>
                                    <tr>
                                        <td> <label style="font-size: 20px">Start  Date :</label></td>
                                        <td><%=add.getCheckinDate()%></td>

                                    </tr>
                                    <tr>
                                        <td><label style="font-size: 20px">End date:</label></td>
                                        <td><%=add.getCheckoutDate()%></td>

                                    </tr>


                                </tbody>
                                <%

                                    }%>

                            </table>
                            <div style="color: #cd0a0a">
                                <%
                                    String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                    if (errorMessage != null) {
                                        out.println(errorMessage);
                                    }%>
                            </div>
                            <br/>
                            <div >
                                <a href="TourPlanListControlServlet?action=addnewtourdetails" class="large gray button">Start New Tour</a>
                            </div>

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
