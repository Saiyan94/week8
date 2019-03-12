package io.gino.whatthehellisjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        Connection con = null;

        System.out.println("Opening Connection...");
        try {
            Class.forName (DBConstance.DRIVER);
            con = DriverManager.getConnection(DBConstance.URL, DBConstance.USER, DBConstance.PASS);
        }
        catch (SQLException c) {
            System.err.println ("Failed to Make Connection!");
            System.exit(1);
        }
        catch (ClassNotFoundException e) {
            System.err.println ("Failed to Register Driver!");
            System.exit(1);
        }

        System.out.println("Creating Data...");
        try {
            Statement s = con.createStatement();
            s.executeUpdate ("drop table if exists firstnames");
            s.executeUpdate ("create table firstnames (name varchar (32))");
            s.executeUpdate ("insert into firstnames values ('tom')");
            s.executeUpdate ("insert into firstnames values ('dick')");
            s.executeUpdate ("insert into firstnames values ('harry')");
        } catch (SQLException e1) {
            e1.printStackTrace();
            System.err.println ("Failed to Create Data!");
            System.exit(1);
        }

        System.out.println("Closing Connection...");
        try {
            con.close ();
        } catch (SQLException e) {
            System.err.println ("Failed to Close Connection!");
            System.exit(1);
        }
    }

}
