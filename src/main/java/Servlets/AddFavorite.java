package Servlets;

import database.tables.EditFavoritesTable;
import mainClasses.Favorites;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class AddFavorite extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //appllicant adds a favorite room

        HttpSession session = request.getSession(false);
        String applicantID = (String) session.getAttribute("applicantID");

        String roomID = request.getParameter("roomID");

        Favorites favorite = new Favorites();

        favorite.setApplicantID(Integer.parseInt(applicantID));
        favorite.setRoomID(Integer.parseInt(roomID));

        try {
            EditFavoritesTable.addNewFavorite(favorite);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/applicanthomepage.html");
    }
}
