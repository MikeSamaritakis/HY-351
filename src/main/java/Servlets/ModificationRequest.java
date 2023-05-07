package Servlets;

import database.tables.EditRequestsTable;
import mainClasses.Request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class ModificationRequest extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //applicant makes a cancellation request

        //String applicantID = request.getParameter("applicantID");
        HttpSession session = request.getSession(false);
        String applicantID = (String) session.getAttribute("applicantID");

        String date = request.getParameter("date");
        String roomID = request.getParameter("roomID");

        EditRequestsTable ert = new EditRequestsTable();
        Request newrequest = new Request();

        newrequest.setDatereq(date);
        newrequest.setRoomIDreq(Integer.parseInt(roomID));
        newrequest.setReserverIDreq(Integer.parseInt(applicantID));
        newrequest.setReqType(3);

        try {
            ert.addNewRequest(newrequest);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/applicanthomepage.html");
    }
}
