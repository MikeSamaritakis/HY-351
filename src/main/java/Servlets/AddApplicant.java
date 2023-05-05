package Servlets;

import database.tables.EditApplicantsTable;
import mainClasses.Applicant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class AddApplicant extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //admin adds new applicant

        Applicant applicant = new Applicant();

        applicant.setID(Integer.parseInt(request.getParameter("newapplicantID")));
        applicant.setEmployeeName(request.getParameter("newapplicantname"));
        applicant.setAge(Integer.parseInt(request.getParameter("newapplicantage")));
        applicant.setHierarchicalPosition(Integer.parseInt(request.getParameter("newapplicanthierarchicalposition")));
        applicant.setPriority(Integer.parseInt(request.getParameter("newapplicantpriority")));
        applicant.setSalary(Integer.parseInt(request.getParameter("newapplicantsalary")));

        EditApplicantsTable eat = new EditApplicantsTable();
        try {
            eat.addNewApplicant(applicant);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
