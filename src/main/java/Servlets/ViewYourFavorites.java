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
public class ViewYourFavorites extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //applicant views favorites


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
        String query = "SELECT * FROM favorites WHERE ApplicantID = '" + applicantID + "'";

        response.setContentType("text/html");
        out.println("<html><body>");
        out.println("<form action=\"http://localhost:8080/lab_example_web_war_exploded/applicanthomepage.html\">\n" +
                "    <input type=\"submit\" value=\"Go back to HomePage\" />\n" +
                "</form>");
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            out.println("<table border=1 width=50% height=50%>");
            out.println("Your Favorites\n");
            while(rs.next()){
                String roomid = rs.getString("RoomID");
                out.println("<tr><td>Room ID:" + roomid + "</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            out.println("An error has occured.");
        }

    }
}
