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
        String sDate1 = null;

        Date date1;

        Request dbrequest;
        Reservation reservation = new Reservation();

        EditRequestsTable req = new EditRequestsTable();
        EditReservationsTable res = new EditReservationsTable();

        try {
            dbrequest = req.databaseToRequest(requestID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        assert dbrequest != null;

        //Converting String to Date type
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-MM-yyyy");
        try {
            date1=formatter1.parse(sDate1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        reservation.setDate(dbrequest.getDatereq());
        reservation.setReserverID(dbrequest.getReserverIDreq());
        reservation.setRoomID(dbrequest.getRoomIDreq());

        try {
            res.addNewReservation(reservation);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
