package Servlets;

import database.tables.EditRequestsTable;
import database.tables.EditReservationsTable;
import database.tables.EditRoomsTable;
import mainClasses.Request;
import mainClasses.Reservation;
import mainClasses.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet()
public class ApproveRequest extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("APPROVE REQUEST");

        String requestID = request.getParameter("requestID");
        int resID = Integer.parseInt(requestID);

        Request dbrequest;
        Reservation reservation = new Reservation();

        EditRequestsTable req = new EditRequestsTable();
        EditReservationsTable res = new EditReservationsTable();

        try {
            dbrequest = req.databaseToRequest(resID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(dbrequest.getDatereq());
        reservation.setDate(dbrequest.getDatereq());

        System.out.println(dbrequest.getReserverIDreq());
        reservation.setReserverID(dbrequest.getReserverIDreq());

        System.out.println(dbrequest.getRoomIDreq());
        reservation.setRoomID(dbrequest.getRoomIDreq());

        try {
            res.addNewReservation(reservation);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
