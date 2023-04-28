/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

import static database.DB_Connection.getInitialConnection;

import database.tables.EditAdminsTable;
import database.tables.EditApplicantsTable;
import database.tables.EditFavoritesTable;
import database.tables.EditInvitationsTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alex
 */
public class InitDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

    public void dropDatabase() throws SQLException, ClassNotFoundException {
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
    }
}
