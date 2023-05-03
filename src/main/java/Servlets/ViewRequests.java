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

@WebServlet(name = "ViewRequests", value = "/ViewRequests")
public class ViewRequests extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //admin can see ALL available requests
        PrintWriter out = response.getWriter();

        Connection con = null;
        try {
            con = DB_Connection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ResultSet rs;
        String query = "select * from requests";

        response.setContentType("text/html");
        out.println("<html><body>");

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            out.println("<table border=1 width=50% height=50%>");
            out.println("All Requests\n");
            while(rs.next()){
                String reqid = rs.getString("ReqID");
                String resid = rs.getString("ReserverIDReq");
                String datereq = rs.getString("DateReq");
                String roomid = rs.getString("RoomIDReq");
                out.println("<tr><td>ID:" + reqid + "</td><td>DATE:" + datereq + "</td><td>ROOM:" + roomid + "</td><td>APPLICANT:" + resid + "</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            out.println("An error has occured.");
        }

    }
}
