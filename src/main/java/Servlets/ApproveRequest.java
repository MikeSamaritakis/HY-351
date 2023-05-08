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
        //System.out.println("APPROVE REQUEST");

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

            int reserverid = 0;
            int roomid = 0;
            String reqdate = null;
            int reqtype = 0;
            while (rs.next()) {
                roomid = rs.getInt("RoomIDReq");
                reqdate = rs.getString("DateReq");
                reserverid = rs.getInt("ReserverIDReq");
                reqtype = rs.getInt("ReqType");
            }
//            System.out.println("reserverid  " + reserverid);
//            System.out.println("reqdate  " + reqdate);
//            System.out.println("roomid  " + roomid);

            if (reqtype == 1){
                //make reservation request

                Reservation reservation = new Reservation();

                reservation.setReserverID(reserverid);
                reservation.setDate(reqdate);
                reservation.setRoomID(roomid);

                EditReservationsTable.addNewReservation(reservation);

                EditRequestsTable.deleteRequest(requestID);
            }else if (reqtype == 2){
                //cancellation request

                Reservation reservation = new Reservation();

                EditReservationsTable.deleteReservationbyReserverIDandRoomID(reserverid, roomid);

                EditRequestsTable.deleteRequest(requestID);
            }else if (reqtype == 3){
                //modify request

                EditReservationsTable.updateReservationDate(reqdate, roomid, reserverid);

                EditRequestsTable.deleteRequest(requestID);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
