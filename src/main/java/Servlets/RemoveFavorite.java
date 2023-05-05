package Servlets;

import database.tables.EditFavoritesTable;
import database.tables.EditRequestsTable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class RemoveFavorite extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //appllicant removes a favorite room
        String roomID = request.getParameter("roomID");

        HttpSession session = request.getSession(false);
        String applicantID = (String) session.getAttribute("applicantID");

        try {
            EditFavoritesTable.deleteFavorite(roomID, applicantID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/applicanthomepage.html");
    }
}
