package Servlets;

import database.DB_Connection;

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
public class ViewYourReservations extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //applicant can see his/her reservations-approved requests

        HttpSession session = request.getSession(false);
        String applicantID = (String) session.getAttribute("applicantID");
        //System.out.println(applicantID);

        PrintWriter out = response.getWriter();

        Connection con = null;
        try {
            con = DB_Connection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ResultSet rs;
        String query = "SELECT * FROM reservations WHERE ReserverID = '" + applicantID + "'";

        response.setContentType("text/html");
        out.println("<html><body>");
        out.println("<form action=\"http://localhost:8080/lab_example_web_war_exploded/applicanthomepage.html\">\n" +
                "    <input type=\"submit\" value=\"Go back to HomePage\" />\n" +
                "</form>");
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            out.println("<table border=1 width=50% height=50%>");
            out.println("Your Reservations\n");
            while(rs.next()){
                String roomid = rs.getString("RoomID");
                String resdate = rs.getString("resdate");
                String reservationid = rs.getString("ReservationID");
                out.println("<tr><td>RESERVATION ID:" + reservationid + "</td><td>ROOM ID:" + roomid + "</td><td>DATE:" + resdate + "</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            out.println("An error has occured.");
        }
    }
}
