package Servlets;

import database.tables.EditRoomsTable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class RemoveRoom extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //remove a room
        String roomID = request.getParameter("roomID");

        EditRoomsTable ert = new EditRoomsTable();

        try {
            ert.deleteRoom(roomID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
