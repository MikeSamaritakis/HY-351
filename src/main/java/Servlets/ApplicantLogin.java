package Servlets;

import database.tables.EditApplicantsTable;
import mainClasses.Applicant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ApplicantLogin", value = "/ApplicantLogin")
public class ApplicantLogin extends HttpServlet {
    private static final long serialVersionUID = 1L; //https://www.codejava.net/coding/java-servlet-and-jsp-hello-world-tutorial-with-eclipse-maven-and-apache-tomcat

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost is for Applicant Login
        String applicantID = request.getParameter("applicantID");

        Applicant applicant = new Applicant();
        String page = "";

        try {
            applicant = EditApplicantsTable.databaseToApplicant(applicantID);
        } catch (SQLException e) {
            page = "loginpage.html";
            RequestDispatcher dd =request.getRequestDispatcher(page);
            dd.forward(request,response);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (applicant == null){
            page = "loginpage.html";
            RequestDispatcher dd =request.getRequestDispatcher(page);
            dd.forward(request,response);
        }
        // If the log-in fails then an exception is thrown meaning that the user will not see the
        // page created as a welcome user, thus he/she will not have access to any sensitive information.

        page="applicanthomepage.html";
        RequestDispatcher dd =request.getRequestDispatcher(page);
        dd.forward(request,response);
    }
}
