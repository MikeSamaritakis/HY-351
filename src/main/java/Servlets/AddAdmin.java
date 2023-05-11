package Servlets;

import database.tables.EditAdminsTable;
import database.tables.EditApplicantsTable;
import mainClasses.Admin;
import mainClasses.Applicant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet()
public class AddAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //admin adds new admin - CREATES ARMY AND TAKES OVER???!!!

        Admin admin = new Admin();

        admin.setID(Integer.parseInt(request.getParameter("newadminid")));
        admin.setEmployeeName(request.getParameter("newadminname"));
        admin.setAge(Integer.parseInt(request.getParameter("newadminage")));
        admin.setSalary(Integer.parseInt(request.getParameter("newadminsalary")));
        admin.setBranch(Integer.parseInt(request.getParameter("newadminhierarchicalposition")));

        try {
            if (EditAdminsTable.databaseToAdmin(String.valueOf(admin.getID())) != null) {
                EditAdminsTable.addNewAdmin(admin);
            }else{
                System.out.println("ERROR: THE ADMIN CREDENTIALS YOU PROVIDED ALREADY EXISTS!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
