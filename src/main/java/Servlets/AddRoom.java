package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddRoom", value = "/AddRoom")
public class AddRoom extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //add a new room
        String roomID = request.getParameter("roomID");
        String capacity = request.getParameter("capacity");
        String equipmenttype = request.getParameter("equipmenttype");
        String adminID = request.getParameter("adminID");


    }
}
