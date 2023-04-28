package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Favorites;
import mainClasses.Invitation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditInvitationsTable extends Invitation {

    public void createInvitationsTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE invitations "
                + "(ContactInfo INTEGER NOT NULL , "
                + " RoomID INTEGER NOT NULL , "
                + " invdate DATE NOT NULL "
                + ")";
        stmt.execute(query);
        stmt.close();

        insertdefaultinvitationsvalues();
    }

    public void insertdefaultinvitationsvalues(){
        try {
            Connection con = null;
            try {
                con = DB_Connection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " invitations (ContactInfo, invdate, RoomID)"
                    + " VALUES (6906120834, 2022-11-30, 2)";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The invitation was successfully added in the database.");

            String insertQuery2 = "INSERT INTO "
                    + " invitations (ContactInfo, invdate, RoomID)"
                    + " VALUES (6977997200, 2029-04-10, 1)";
            //stmt.execute(table);
            System.out.println(insertQuery2);
            stmt.executeUpdate(insertQuery2);
            System.out.println("# The invitation2 was successfully added in the database.");

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditInvitationsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewInvitation(Invitation invitation) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " invitations (ContactInfo, invdate, RoomID)"
                    + " VALUES ("
                    + "'" + invitation.getContactInfo() + "',"
                    + "'" + invitation.getDate() + "',"
                    + "'" + invitation.getRoomID() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The invitation was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditInvitationsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Invitation databaseToInvitation(int contactinfo, Date invdate, int roomID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM invitations WHERE RoomID = '" + roomID + "'AND invdate = '" + invdate + "'AND ContactInfo = '" + contactinfo + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Invitation invitation = gson.fromJson(json, Invitation.class);
            return invitation;
        } catch (Exception e) {
            System.out.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("The given invitation does not exist.");
        }
        return null;
    }


}
