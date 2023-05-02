package Servlets;

import database.tables.EditRequestsTable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class DenyRequest extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("DENY REQUEST");
        String requestID = request.getParameter("requestID");

        EditRequestsTable ert = new EditRequestsTable();

        try {
            ert.deleteRequest(requestID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
