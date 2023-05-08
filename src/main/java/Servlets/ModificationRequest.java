package Servlets;

import database.tables.EditRequestsTable;
import database.tables.EditReservationsTable;
import mainClasses.Request;
import mainClasses.Reservation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet()
public class ModificationRequest extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //applicant makes a cancellation request

        //String applicantID = request.getParameter("applicantID");
        HttpSession session = request.getSession(false);
        String applicantID = session.getAttribute("applicantID").toString();

        String date = request.getParameter("datemodrequest");
        String reservationID = request.getParameter("modreqreservationid");
        //System.out.println("date=" + date);
        //String roomID = request.getParameter("roomID");

        Request newrequest = new Request();
        Reservation reservation = new Reservation();

        try {
            reservation = EditReservationsTable.databaseToReservationbyResID(reservationID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        System.out.println("roomid= "+reservation.getRoomID());
        newrequest.setRoomIDreq(reservation.getRoomID());
        newrequest.setDatereq(date);
        newrequest.setReserverIDreq(Integer.parseInt(applicantID));
        newrequest.setReqType(3);

        try {
            EditRequestsTable.addNewRequest(newrequest);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/applicanthomepage.html");
    }
}

