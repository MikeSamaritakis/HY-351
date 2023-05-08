package Servlets;

import database.tables.EditRequestsTable;
import database.tables.EditRoomsTable;
import mainClasses.Request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet()
public class MakeRequest extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //applicant makes a request

        //String applicantID = request.getParameter("applicantID");
        HttpSession session = request.getSession(false);
        String applicantID = (String) session.getAttribute("applicantID");

        String date = request.getParameter("date");
        String roomID = request.getParameter("roomID");

        Request newrequest = new Request();

        newrequest.setDatereq(date);
        newrequest.setRoomIDreq(Integer.parseInt(roomID));
        newrequest.setReserverIDreq(Integer.parseInt(applicantID));
        newrequest.setReqType(1);

        try {
            if (EditRoomsTable.databaseToRoom(roomID) != null) {
                EditRequestsTable.addNewRequest(newrequest);
            }else {
                System.out.println("ERROR: THE ROOM YOU REQUESTED DOES NOT EXIST!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/applicanthomepage.html");
    }
}
