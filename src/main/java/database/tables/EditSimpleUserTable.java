/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import mainClasses.SimpleUser;
import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class EditSimpleUserTable {

    public void updateSimpleUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE users SET password='" + password + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
        stmt.close();
        con.close();
    }

    public void deleteSimpleUser(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM users WHERE username = '" + username + "'";
        stmt.executeUpdate(delete);
        stmt.close();
        con.close();
    }

    public SimpleUser databaseToSimpleUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password='" + password + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            SimpleUser user = gson.fromJson(json, SimpleUser.class);
            return user;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    /* Returns all SimpleUsers in the Database. */
    public ArrayList<SimpleUser> databaseToSimpleUsers() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<SimpleUser> users = new ArrayList<SimpleUser>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                SimpleUser user = gson.fromJson(json, SimpleUser.class);
                users.add(user);
            }
            return users;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public boolean usernameInDataBase(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return false;
    }

    public boolean emailInDataBase(String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return false;
    }

    public void createSimpleUserTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE users "
                + "(user_id INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    email VARCHAR(40) not null unique,"
                + "    password VARCHAR(32) not null,"
                + " PRIMARY KEY ( user_id))";
        stmt.execute(query);
        stmt.close();
        con.close();
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewSimpleUser(String username, String email, String password) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " users (username,email,password)"
                    + " VALUES ("
                    + "'" + username + "',"
                    + "'" + email + "',"
                    + "'" + password + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The user was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditSimpleUserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
