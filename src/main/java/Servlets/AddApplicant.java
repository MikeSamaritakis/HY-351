package Servlets;

import database.tables.EditApplicantsTable;
import mainClasses.Applicant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet()
public class AddApplicant extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //admin adds new applicant

        Applicant applicant = new Applicant();

//        applicant.setID(Integer.parseInt(request.getParameter("newapplicantID")));
//        applicant.setEmployeeName(request.getParameter("newapplicantname"));
//        applicant.setAge(Integer.parseInt(request.getParameter("newapplicantage")));
//        applicant.setSalary(Integer.parseInt(request.getParameter("newapplicantsalary")));
//        applicant.setHierarchicalPosition(Integer.parseInt(request.getParameter("newapplicanthierarchicalposition")));
        applicant.setPriority(Integer.parseInt(request.getParameter("newapplicantpriority")));

        applicant.setID(Integer.parseInt(request.getParameter("newadminid")));
        applicant.setEmployeeName(request.getParameter("newadminname"));
        applicant.setAge(Integer.parseInt(request.getParameter("newadminage")));
        applicant.setSalary(Integer.parseInt(request.getParameter("newadminsalary")));
        applicant.setBranch(Integer.parseInt(request.getParameter("newadminhierarchicalposition")));

        try {
            if (EditApplicantsTable.databaseToApplicant(String.valueOf(applicant.getID())) != null) {
                EditApplicantsTable.addNewApplicant(applicant);
            }else{
                System.out.println("ERROR: THE APPLICANT CREDENTIALS YOU PROVIDED ALREADY EXISTS!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/lab_example_web_war_exploded/adminhomepage.html");
    }
}
