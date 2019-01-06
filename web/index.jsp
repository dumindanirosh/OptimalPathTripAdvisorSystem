

<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
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
    </head>
    <%! List<LocationDetails> locationList;
        List<LocationCategoryDetails> listOfCategories;
        List<LocationDetails> hotLocatonList;
        List<String> locationCityList;


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
                        <li>
                            <a href="forwardAction?forwardPage=user/membersignUp.jsp" >Register</a>
                        </li>

                    </ul>
                </div>

                <div >

                </div>           

               
                <div id="content">
                    <div id="intro">
                        <h1><span>Welcome to the Tour Planner</span></h1>
                    </div>

                    <div class="container section">
                        <div class="half column">
                            <div style="color: mediumslateblue; font-size: 26px">
                                Use Sri Lanka Travel Guide and Sri Lanka Travel Planner to explore Sri Lanka. Be your own travel guide and create your tour now.
                                </div><br/>
                            <p>
                                <a href="TourPlanListControlServlet?action=addnewtourdetails" class="medium gray button">Start New Tour</a>
                            </p>
                        </div>
                        <div class="half column last" style="border-style: solid; " >
                            <h3 class="nobg elements" style="font-size: 22px">Login</h3>

                            <form action="UserControlServlet" method="POST">
                                <table border="1" >
                                    <tbody>
                                        <tr>
                                            <td>Username :</td>
                                            <td> <input type="text" name="username" required="true"   /></td>
                                        </tr>
                                        <tr>
                                            <td>Password :</td>
                                            <td><input type="password" name="password" required="true" /> </td>
                                        </tr>

                                    </tbody>
                                </table>
                                <div >
                                    <%
                                        String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                        if (errorMessage != null) {
                                            out.println(errorMessage);
                                        }%>
                                </div>

                                <div>


                                    <input type = "hidden" name = "action" value ="login" >
                                    <input type="submit" value="Login" class="large gray button" />
                                    <input type="reset" value="Reset"  class="large gray button" />
                                </div>
                            </form>
                        </div>
                    </div>

                   
                    <div id="feature" class="container section">
                        <h1 align ="middle">Hot Locations</h1>
                        <%
                            int x = 0;
                            hotLocatonList = (List<LocationDetails>) session.getAttribute("hotLocationDetails");
                            Iterator<LocationDetails> iter = hotLocatonList.iterator();
                            while (iter.hasNext()) {
                                LocationDetails hotLocation = iter.next();
                                if (x < 8) {
                                    String imageURL = BackendConstants.IMAGE_VIEW_PATH + hotLocation.getMainImageUrl();
                                    
                        %>
                        <div class="one-fourth column">
                            <div class="one-fourth "> 
                                <div class="one-fourth " style="height: 40px"> 
                                    <h3> <%=hotLocation.getLocationName()%></h3></div>
                                <a href=LocationControlServlet?action=viewmorelocationdetails&locationId=<%=hotLocation.getLocationId()%>>
                               <br>     <p>        
                                        <img src="<%=imageURL%>" width="200px" height="200px" alt=""/>
                                    </p></a>
                            </div>
                            <h3><span>

                                    <a href="LocationControlServlet?action=viewmorelocationdetails&locationId=<%=hotLocation.getLocationId()%>">View More</a>
                                </span></h3>
                        </div>
                        <% ++x;
                                }
                            }%>
                    </div>


                    <div id="feature" class="container section">
                        <h1 align ="middle">All Locations</h1>
                        <%
                            locationList = (List<LocationDetails>) session.getAttribute("locationList");
                            Iterator<LocationDetails> ite = locationList.iterator();
                            while (ite.hasNext()) {
                                LocationDetails location = ite.next();
                               
                                String imageURL = "locationimages/" + location.getMainImageUrl();
                        %>
                        <div class="one-third column">
                            <div class="one-third "> 
                                <a href=LocationControlServlet?action=viewmorelocationdetails&locationId=<%=location.getLocationId()%>>
                                    <p>        
                                        <img src="<%=imageURL%>" width="300px" height="300px" alt=""/>
                                    </p></a>
                            </div>
                            <h3><span>
                                    <%=location.getLocationName()%><br/>
                                    <%=location.getLocationCategoryType()%>
                                    <br/>
                                    <a href="LocationControlServlet?action=viewmorelocationdetails&locationId=<%=location.getLocationId()%>">View More</a>
                                </span></h3>
                        </div>
                        <%
                            }%>
                    </div>


                    
                    <div id="footer">
                        <div class="container section end">

                            <li>
                                <a href="forwardAction?forwardPage=feedback/addcontactus.jsp" >Contact Us</a>
                            </li>

                        </div>
                    </div>
                    <div id="copyright"  >
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
