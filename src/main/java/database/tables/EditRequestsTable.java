package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Request;
import mainClasses.Room;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditRequestsTable extends Request {
    public void createRequestTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE requests "
                + "(ReqID INTEGER  UNIQUE PRIMARY KEY AUTO_INCREMENT,"
                + " DateReq char(255)  , "
                + " ReserverIDReq INTEGER , "
                + " RoomIDReq INTEGER "
                + ")";
        stmt.execute(query);
        stmt.close();

        insertdefaultrequestsvalues();
    }

    public void insertdefaultrequestsvalues(){
        try {
            Connection con = null;
            try {
                con = DB_Connection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " requests (DateReq, ReserverIDReq, RoomIDReq)"
                    + " VALUES ('2023-05-30', 13, 77)";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The request was successfully added in the database.");

            String insertQuery2 = "INSERT INTO "
                    + " requests (DateReq, ReserverIDReq, RoomIDReq)"
                    + " VALUES ('2020-12-24', 43, 3)";
            //stmt.execute(table);
            System.out.println(insertQuery2);
            stmt.executeUpdate(insertQuery2);
            System.out.println("# The request2 was successfully added in the database.");

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditRequestsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewRequest(Request request) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " requests (DateReq, ReserverIDReq, RoomIDReq)"
                    + " VALUES  ("
                    + "'" + request.getDatereq() + "',"
                    + "'" + request.getReserverIDreq() + "',"
                    + "'" + request.getRoomIDreq() + "' "
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The request was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditRequestsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public static Request databaseToRequest(String reqID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM requests WHERE RoomIDReq = '" + reqID + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Request req = gson.fromJson(json, Request.class);

            return req;
        } catch (Exception e) {
            System.out.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("The given RequestID does not exist.");
        }
        return null;

    }

    public static void deleteRequest(String reqID) throws ClassNotFoundException{
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "DELETE FROM requests WHERE ReqID = '" + reqID + "'";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The request was successfully deleted from the database.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditRequestsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
