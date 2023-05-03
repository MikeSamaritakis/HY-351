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
public class ApproveRequest extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("APPROVE REQUEST");

        String requestID = request.getParameter("requestID");

        Request dbrequest;
        Reservation reservation = new Reservation();

        EditRequestsTable req = new EditRequestsTable();
        EditReservationsTable res = new EditReservationsTable();

        try {
            dbrequest = req.databaseToRequest(requestID);
            System.out.println(requestID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//        String tmp;
//        tmp = dbrequest.getDatereq();
//        System.out.println(tmp);
         String ls = dbrequest.getDatereq();
        System.out.println(ls);
        reservation.setDate(dbrequest.getDatereq());
        reservation.setReserverID(dbrequest.getReserverIDreq());
        reservation.setRoomID(dbrequest.getRoomIDreq());

        try {
            res.addNewReservation(reservation);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//        try {
//            req.deleteRequest(requestID);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
