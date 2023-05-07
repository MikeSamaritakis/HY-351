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

        Connection con = null;
        try {
            con = DB_Connection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ResultSet rs;
        String query = "SELECT * FROM requests WHERE ReqID = '" + requestID + "'";

        try {

            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            String reserverid = null;
            String roomid = null;
            String reqdate = null;
            String reqtype = null;
            while (rs.next()) {
                roomid = rs.getString("RoomIDReq");
                reqdate = rs.getString("DateReq");
                reserverid = rs.getString("ReserverIDReq");
                reqtype = rs.getString("ReqType");
            }

            if (reqtype == "1") {
                //make reservation request
                Request dbrequest = new Request();
                Reservation reservation = new Reservation();

                dbrequest.setReserverIDreq(Integer.parseInt(reserverid));
                dbrequest.setRoomIDreq(Integer.parseInt(roomid));
                dbrequest.setDatereq(reqdate);

                EditRequestsTable.deleteRequest(requestID);

                reservation.setDate(reqdate);
                reservation.setReserverID(Integer.parseInt(reserverid));
                reservation.setRoomID(Integer.parseInt(roomid));

                EditReservationsTable.addNewReservation(reservation);
            }else if (reqtype == "2"){
                // delete reservation request
                String rescancelid = request.getParameter("deletereqreservationid");
                EditReservationsTable.deleteReservation(rescancelid);
                EditRequestsTable.deleteRequest(requestID);
            }else if (reqtype == "3"){
                //date modification request
                String moddate = request.getParameter("datemodrequest");
                String resid = request.getParameter("modreqreservationid");
                EditReservationsTable.updateReservationDate(moddate, resid);
                EditRequestsTable.deleteRequest(requestID);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
