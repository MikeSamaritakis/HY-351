package Servlets;

import database.tables.EditAdminsTable;
import database.tables.EditApplicantsTable;
import mainClasses.Admin;
import mainClasses.Applicant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class AddAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //admin adds new admin - CREATES ARMY AND TAKES OVER???!!!

        Admin admin = new Admin();

        admin.setID(Integer.parseInt(request.getParameter("newadminID")));
        admin.setEmployeeName(request.getParameter("newadminname"));
        admin.setAge(Integer.parseInt(request.getParameter("newadminage")));
        admin.setSalary(Integer.parseInt(request.getParameter("newadminsalary")));
        admin.setHierarchicalPosition(Integer.parseInt(request.getParameter("newadminhierarchicalposition")));
        admin.setRoomID(Integer.parseInt(request.getParameter("newadminroomid")));

        EditAdminsTable eat = new EditAdminsTable();
        try {
            eat.addNewAdmin(admin);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
