package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Admin;
import mainClasses.Applicant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditApplicantsTable extends Applicant {

    public void createApplicantTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE applicants "
                + "(ID INTEGER NOT NULL UNIQUE PRIMARY KEY , "
                + " EmployeeName char(255),"
                + " Age INTEGER,"
                + " Salary INTEGER,"
                + " Branch INTEGER,"
                + " Priority INTEGER "
                + ")";
        stmt.execute(query);
        stmt.close();

        insertdefaultapplicantvalues();
    }

    public void insertdefaultapplicantvalues(){
        try {
            Connection con = null;
            try {
                con = DB_Connection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " applicants (ID, EmployeeName, Age, Salary, Branch, Priority)"
                    + " VALUES (321, 'mhtsos', 22, 750, 1, 1)";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The applicant was successfully added in the database.");

            String insertQuery2 = "INSERT INTO "
                    + " applicants (ID, EmployeeName, Age, Salary, Branch, Priority)"
                    + " VALUES (23, 'farmas', 21, 650, 2, 4)";
            //stmt.execute(table);
            System.out.println(insertQuery2);
            stmt.executeUpdate(insertQuery2);
            System.out.println("# The applicant2 was successfully added in the database.");

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditApplicantsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addNewApplicant(Applicant applicant) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " applicants (ID,EmployeeName, Age, Salary, Branch, Priority)"
                    + " VALUES ("
                    + "'" + applicant.getID() + "',"
                    + "'" + applicant.getEmployeeName() + "',"
                    + "'" + applicant.getAge() + "',"
                    + "'" + applicant.getSalary() + "',"
                    + "'" + applicant.getBranch() + "',"
                    + "'" + applicant.getPriority() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The applicant was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditApplicantsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Applicant databaseToApplicant(String applicantID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM applicants WHERE ID = '" + applicantID + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Applicant applicant = gson.fromJson(json, Applicant.class);
            return applicant;
        } catch (Exception e) {
            System.out.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("The given ID does not exist.");
        }
        return null;
    }


}
