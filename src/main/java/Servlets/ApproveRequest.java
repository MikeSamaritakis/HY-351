package Servlets;

import database.DB_Connection;
import database.tables.EditRequestsTable;
import database.tables.EditReservationsTable;
import mainClasses.Request;
import mainClasses.Reservation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet()
public class ApproveRequest extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestID = request.getParameter("requestID");

        try {
            Request request1 = EditRequestsTable.databaseToRequest(requestID);

            if (request1.getReqType() == 1){
                //make reservation request

                Reservation reservation = null;

                reservation.setDate(request1.getDatereq());
                System.out.println(request1.getDatereq());
                reservation.setReserverID(request1.getReserverIDreq());
                System.out.println(request1.getReserverIDreq());
                reservation.setRoomID(request1.getRoomIDreq());
                System.out.println(request1.getRoomIDreq());

                EditReservationsTable.addNewReservation(reservation);
                EditRequestsTable.deleteRequest(requestID);

            }else if (request1.getReqType() == 2){
                // delete reservation request

                System.out.println("approve");

                //EditReservationsTable.deleteReservation();

                EditRequestsTable.deleteRequest(requestID);

            }else if (request1.getReqType() == 3){
                //date modification request

                Request request2 = EditRequestsTable.databaseToRequest(requestID);

                Reservation reservation = EditReservationsTable.databaseToReservation(request2.getReserverIDreq(), request2.getRoomIDreq());

                EditReservationsTable.updateReservationDate(request2.getDatereq(), request2.getRoomIDreq());

                EditRequestsTable.deleteRequest(requestID);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
