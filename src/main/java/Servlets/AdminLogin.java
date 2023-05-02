package Servlets;

import database.tables.EditAdminsTable;
import mainClasses.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet()
public class AdminLogin extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost is for Admin Login
        String adminID = request.getParameter("employeeID");
        Admin admin;
        String page;

        try {
            admin = EditAdminsTable.databaseToAdmin(adminID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (admin == null){
            page = "loginpage.html";
            RequestDispatcher dd =request.getRequestDispatcher(page);
            dd.forward(request,response);
        }
        // If the log-in fails then an exception is thrown meaning that the user will not see the
        // page created as a welcome user, thus he/she will not have access to any sensitive information.

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
