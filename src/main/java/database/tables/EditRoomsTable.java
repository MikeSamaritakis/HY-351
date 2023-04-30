package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Admin;
import mainClasses.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditRoomsTable extends Room {

    public void createRoomTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE rooms "
                + "(RoomID INTEGER NOT NULL UNIQUE PRIMARY KEY, "
                + " Capacity INTEGER,"
                + " Availability INTEGER,"
                + " EquipmentType INTEGER, "
                + " AdminID INTEGER NOT NULL"
                + ")";
        stmt.execute(query);
        stmt.close();

        insertdefaultroomvalues();
    }

    public void insertdefaultroomvalues(){
        try {
            Connection con = null;
            try {
                con = DB_Connection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " rooms (RoomID, Capacity, Availability, EquipmentType, AdminID)"
                    + " VALUES (2, 24, 2, 13, 77)";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The room was successfully added in the database.");

            String insertQuery2 = "INSERT INTO "
                    + " rooms (RoomID, Capacity, Availability, EquipmentType, AdminID)"
                    + " VALUES (3, 25, 3, 14, 80)";
            //stmt.execute(table);
            System.out.println(insertQuery2);
            stmt.executeUpdate(insertQuery2);
            System.out.println("# The room2 was successfully added in the database.");

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditRoomsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewRoom(Room room) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " rooms (RoomID, Capacity, Availability, EquipmentType, AdminID)"
                    + " VALUES  ("
                    + "'" + room.getRoomID() + "',"
                    + "'" + room.getCapacity() + "',"
                    + "'" + room.getAvailability() + "',"
                    + "'" + room.getEquipementType() + "', "
                    + "'" + room.getAdminID() + "' "
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The room was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditRoomsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Room databaseToRoom(String roomID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM rooms WHERE RoomID = '" + roomID + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Room room = gson.fromJson(json, Room.class);
            return room;
        } catch (Exception e) {
            System.out.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("The given Room does not exist.");
        }
        return null;
    }

}
