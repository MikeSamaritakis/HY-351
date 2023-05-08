package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditAdminsTable extends Admin {

    public void createAdminTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE admins "
                + "(ID INTEGER NOT NULL UNIQUE PRIMARY KEY, "
                + " EmployeeName char(255),"
                + " Age INTEGER,"
                + " Salary INTEGER,"
                + " HierarchicalPosition INTEGER"
                + ")";
        stmt.execute(query);
        stmt.close();

        insertdefaultadminvalues();
    }

    public void insertdefaultadminvalues(){
        try {
            Connection con = null;
            try {
                con = DB_Connection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " admins (ID, EmployeeName, Age, Salary, HierarchicalPosition)"
                    + " VALUES (1234, 'tsoukos', 22, 750, 1)";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The admin was successfully added in the database.");

            String insertQuery2 = "INSERT INTO "
                    + " admins (ID, EmployeeName, Age, Salary, HierarchicalPosition)"
                    + " VALUES (4321, 'sfalmas', 21, 650, 2)";
            //stmt.execute(table);
            System.out.println(insertQuery2);
            stmt.executeUpdate(insertQuery2);
            System.out.println("# The admin2 was successfully added in the database.");

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditAdminsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addNewAdmin(Admin admin) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " admins (ID,EmployeeName, Age, Salary, HierarchicalPosition)"
                    + " VALUES ("
                    + "'" + admin.getID() + "',"
                    + "'" + admin.getEmployeeName() + "',"
                    + "'" + admin.getAge() + "',"
                    + "'" + admin.getSalary() + "',"
                    + "'" + admin.getHierarchicalPosition() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The admin was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditAdminsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Admin databaseToAdmin(String adminID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM admins WHERE ID = '" + adminID + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Admin admin = gson.fromJson(json, Admin.class);
            return admin;
        } catch (Exception e) {
            System.out.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("The given ID does not exist.");
        }
        return null;
    }

}


