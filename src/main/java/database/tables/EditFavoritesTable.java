package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Admin;
import mainClasses.Favorites;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditFavoritesTable extends Favorites {

    public void createFavoritesTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE favorites "
                + "(RoomID INTEGER, "
                + " ApplicantID INTEGER PRIMARY KEY NOT NULL "
                + ")";
        stmt.execute(query);
        stmt.close();

        insertdefaultfavoritesvalues();
    }

    public void insertdefaultfavoritesvalues(){
        try {
            Connection con = null;
            try {
                con = DB_Connection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " favorites (RoomID, ApplicantID)"
                    + " VALUES (1, 1234)";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The favorite was successfully added in the database.");

            String insertQuery2 = "INSERT INTO "
                    + " favorites (RoomID, ApplicantID)"
                    + " VALUES (2, 4321)";
            //stmt.execute(table);
            System.out.println(insertQuery2);
            stmt.executeUpdate(insertQuery2);
            System.out.println("# The favorite2 was successfully added in the database.");

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditFavoritesTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addNewFavorite(Favorites favorite) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " favorites (RoomID,ApplicantID)"
                    + " VALUES ("
                    + "'" + favorite.getRoomID() + "',"
                    + "'" + favorite.getApplicantID() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The favorite was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditFavoritesTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Favorites databaseToFavorite(int roomID, int applicantID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM favorites WHERE RoomID = '" + roomID + "'AND ApplicantID = '" + applicantID + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Favorites favorite = gson.fromJson(json, Favorites.class);
            return favorite;
        } catch (Exception e) {
            System.out.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("The given favorite does not exist.");
        }
        return null;
    }

    public static void deleteFavorite(String roomID, String applicantID) throws ClassNotFoundException{
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = ("DELETE FROM favorites WHERE RoomID = '" + roomID + "'AND ApplicantID = '" + applicantID + "'");
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The favorite was successfully deleted from the database.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditFavoritesTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
