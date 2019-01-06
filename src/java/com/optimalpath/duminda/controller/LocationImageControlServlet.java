/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optimalpath.duminda.controller;

import com.optimalpath.duminda.dao.LocationDAOImpl;
import com.optimalpath.duminda.dao.LocationDAO;
import com.optimalpath.duminda.dao.LocationImageDAOImpl;
import com.optimalpath.duminda.dao.LocationImageDAO;
import com.optimalpath.duminda.util.BackendConstants;
import com.optimalpath.duminda.util.LocationDetails;
import com.optimalpath.duminda.util.LocationImageDetails;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Duminda
 */
@WebServlet(name = "LocationImageControlServlet", urlPatterns = {"/LocationImageControlServlet"})
public class LocationImageControlServlet extends HttpServlet {
    private LocationDAO locationFacade = new LocationDAOImpl();
    private LocationImageDAO locationImageFacade = new LocationImageDAOImpl();
    RequestDispatcher requestDispatcher = null;
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "locationimages";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action").trim();
        if (action.equalsIgnoreCase("addlocationimage")) {
            String locationId = request.getParameter("locationID");
            String locationCategoryId = request.getParameter("locationCategoryID");
            if (!ServletFileUpload.isMultipartContent(request)) {
                return;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(REQUEST_SIZE);
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY + File.separator + locationId;;
         
            // C:\\Users\\Hp i7\\Desktop\\FYPSourceCode-CB004302\\EJBVersion\\TourPlannerEJB\\TourPlannerEJB-war\\web\\locationimages
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();
                boolean uploaded = false;
                String fileName = null;
                LocationDetails newUser = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        uploaded = true;
                    }
                }
                Integer locationID = Integer.parseInt(locationId);
                if (uploaded) {
                    String filePersistPath = locationId + "/" + fileName;
                    newUser = new LocationDetails(locationID, fileName, filePersistPath);
                    LocationDetails status = locationFacade.createLocationImage(newUser);
                    if (status != null) {
                        int locationId1 = status.getLocationId();
                        request.setAttribute("locationId", new Integer(locationId1));
                        request.setAttribute("locationCategoryId", locationCategoryId);
                        requestDispatcher = request.getRequestDispatcher("location/addmultiplelocationimages.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, "Upload Unsucessful");
                        requestDispatcher = request.getRequestDispatcher("location/addlocationimage.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        if (action.equalsIgnoreCase("addmultiplelocationimages")) {
            String locationId = request.getParameter("locationID");
            String locationCategoryId = request.getParameter("locationCategoryID");
            if (!ServletFileUpload.isMultipartContent(request)) {
                return;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(REQUEST_SIZE);
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY + File.separator + locationId;
            // uploadPath = "C:\\Users\\Hp i7\\Desktop\\FYPSourceCode-CB004302\\EJBVersion\\TourPlannerEJB\\TourPlannerEJB-war\\web\\locationimages" + "/" + locationId;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();
                boolean uploaded = false;
                String fileName = null;
                LocationImageDetails newUser = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        uploaded = true;
                    }
                }
                Integer locationID = Integer.parseInt(locationId);
                if (uploaded) {
                    String filePersistPath = locationId + "/" + fileName;
                    newUser = new LocationImageDetails(fileName, filePersistPath, locationID);
                    LocationImageDetails status = locationImageFacade.createLocationImage(newUser);
                    request.setAttribute("locationId", new Integer(locationId));
                    request.setAttribute("locationCategoryId", locationCategoryId);

                    if (status != null) {
                        request.setAttribute(BackendConstants.MESSAGE, "Upoaded");
                        String requestURL = "?locationID=" + locationId + "&locationCategoryID=" + locationCategoryId;
                        requestDispatcher = request.getRequestDispatcher("/location/addmultiplelocationimages.jsp" + requestURL);
                        requestDispatcher.forward(request, response);
                    } else {
                        request.getSession().setAttribute(BackendConstants.ERROR_MESSAGE, "Failed");
                        String requestURL = "?locationID=" + locationId + "&locationCategoryID=" + locationCategoryId;
                        requestDispatcher = request.getRequestDispatcher("/location/addmultiplelocationimages.jsp" + requestURL);
                        requestDispatcher.forward(request, response);
                    }
                }
            } catch (Exception ex) {
            

                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
