

<%@page import="com.optimalpath.duminda.util.LocationDetails"%>
<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
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
        List<String> locationCityList;
    %>
    <body>
        <%
            String locationId = (String) request.getAttribute("locationId");
           
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
                                <%listOfCategories = (List<LocationCategoryDetails>)session.getAttribute("categoryList");
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
                            
                            <li>
                            <a href="forwardAction?forwardPage=user/adduser.jsp" >Register</a>
                        </li>
                      

                    </ul>
                </div>



                <div id="content" class="room-content">
                    <div id="intro">
                        <h1><span> Login</span></h1>

                    </div>
                    <div class="container section" >
                        

                        <div id="intro" align="center" style="border: #212121 dashed">                        
                            <form action="UserControlServlet" method="POST">
                                <table border="1">
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

                                <div style="color: #cd0a0a">
                                    <%
                                        String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                                        if (errorMessage != null) {
                                            out.println(errorMessage);
                                        }%>
                                </div>

                                <div>

                                    <input type = "hidden" name = "locationId" value="<%=locationId%>" >
                                    <input type = "hidden" name = "action" value ="login" >
                                    <input type="submit" value="Login" class="large gray button" />
                                    <input type="reset" value="Reset"  class="large gray button" />
                                </div>
                            </form>
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

