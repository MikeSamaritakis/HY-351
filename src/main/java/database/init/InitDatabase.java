/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

import static database.DB_Connection.getInitialConnection;

import database.tables.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alex
 */
public class InitDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //dropDatabase();
        InitDatabase init = new InitDatabase();
        init.initDatabase();

        //init.dropDatabase();
    }

    public void initDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE DATABASE HY351");
        stmt.close();
        conn.close();
        initTables();
    }

    public static void dropDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("DROP DATABASE HY351");
        stmt.close();
        conn.close();
    }

    public void initTables() throws SQLException, ClassNotFoundException {
        EditAdminsTable eat = new EditAdminsTable();
        eat.createAdminTable();

        EditApplicantsTable eat2 = new EditApplicantsTable();
        eat2.createApplicantTable();

        EditFavoritesTable eft = new EditFavoritesTable();
        eft.createFavoritesTable();

        EditInvitationsTable eit = new EditInvitationsTable();
        eit.createInvitationsTable();

        EditRoomsTable ert = new EditRoomsTable();
        ert.createRoomTable();

        EditReservationsTable ert2 = new EditReservationsTable();
        ert2.createReservationTable();

        EditRequestsTable ert3 = new EditRequestsTable();
        ert3.createRequestTable();
    }
}
