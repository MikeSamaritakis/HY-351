//package Servlets;
//
//import database.DB_Connection;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//@WebServlet(name = "ViewRequests", value = "/ViewRequests")
//public class ViewRequests extends HttpServlet {
//    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //admin can see ALL available requests
//        PrintWriter out = response.getWriter();
//
//        Connection con = null;
//        try {
//            con = DB_Connection.getConnection();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Statement stmt = con.createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        ResultSet rs;
//        String query = "select * from requests";
//
//        response.setContentType("text/html");
//        out.println("<html><body>");
//
//        try {
//            rs = stmt.executeQuery(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try
//        {
//            rs = stmt.executeQuery(query);
//            out.println("<table border=1 width=50% height=50%>");
//            out.println("Requests \n");
//            while(rs.next()){
//                String n = rs.getString("username");
//                out.println("<tr><td>" + n  + "</td></tr>");
//            }
//            out.println("</table>");
//        }
//        catch (Exception e){
//            out.println("error");
//        }
//
//    }
//}
