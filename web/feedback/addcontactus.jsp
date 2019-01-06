

<%@page import="com.optimalpath.duminda.util.BackendConstants"%>
<%@page import="com.optimalpath.duminda.util.LocationCategoryDetails"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <title>Optimal Path Trip Advisor </title>

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


                <div id="content" >
                    <div id="intro">
                        <h1><span> Contact Us</span></h1>

                    </div>
                    <div class="container section" >


                        <div id="intro" align="center" style="border: #212121 dashed">                        
                            <form action="/OptimalPathTripAdvisorSystem/FeedbackControlServlet" method="POST">
                                <table border="1">

                                    <tbody>
                                        <tr>
                                            <td><label for="firstname" style="font-size: 20px"> Name:</label></td>
                                            <td><input type="text" name="name"  /></td>
                                        </tr>
                                        <tr>
                                            <td><label for="email" style="font-size: 20px">Email:</label></td>
                                            <td><input placeholder="Example: john@doe.com" type="text" name="emailAddress" /> </td>
                                        </tr>
                                        <tr>
                                            <td><label for="contactNo" style="font-size: 20px">Contact No :</label></td>
                                            <td><input type="text" name="contactNo"  /></td>
                                        </tr>

                                        <tr>
                                            <td><label for="message" style="font-size: 20px">Message :</label></td>
                                            <td><input type="text" name="message"  /></td>
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
                                    <br/>
                                    <input type="submit" id="submit" class="medium gray button" value="Submit" />
                                    <input type="reset" id="submit" class="medium gray button" value="Reset" /> 

                                    <input type = "hidden" name = "action" value ="addcontactus">
                                </div>
                            </form>
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
