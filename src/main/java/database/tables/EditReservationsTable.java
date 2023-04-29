package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Applicant;
import mainClasses.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditReservationsTable extends Reservation {

    public void createReservationTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE reservations "
                + "(resdate char(255), "
                + " ReserverID INTEGER,"
                + " RoomID INTEGER NOT NULL"
                + ")";
        stmt.execute(query);
        stmt.close();

        insertdefaultreservatationvalues();
    }

    public void insertdefaultreservatationvalues(){
        try {
            Connection con = null;
            try {
                con = DB_Connection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " reservations (resdate, ReserverID, RoomID)"
                    + " VALUES ('31/12/2007', 1234, 2)";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The reservation was successfully added in the database.");

            String insertQuery2 = "INSERT INTO "
                    + " reservations (resdate, ReserverID, RoomID)"
                    + " VALUES ('31/12/2032', 4321, 1)";
            //stmt.execute(table);
            System.out.println(insertQuery2);
            stmt.executeUpdate(insertQuery2);
            System.out.println("# The reservation2 was successfully added in the database.");

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditReservationsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewReservation(Reservation reservation) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " reservations (resdate, ReserverID, RoomID)"
                    + " VALUES ("
                    + "'" + reservation.getDate() + "',"
                    + "'" + reservation.getReserverID() + "',"
                    + "'" + reservation.getRoomID() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The reservation was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditReservationsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Reservation databaseToReservation(int reserverID, int roomID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM reservations WHERE RoomID = '" + roomID + "'AND ReserverID = '" + reserverID + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Reservation reservation = gson.fromJson(json, Reservation.class);
            return reservation;
        } catch (Exception e) {
            System.out.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("The given reservationID does not exist.");
        }
        return null;
    }

}