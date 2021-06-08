package net.kensaigm.dp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSingletonDemo {

    public static void main(String[] args) {
        DbSingleton instance = DbSingleton.getInstance();

        long timeBefore = 0;
        long timeAfter  = 0;

        System.out.println(instance);

        timeBefore = System.currentTimeMillis();
        Connection conn = instance.getConnection();
        timeAfter  = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);

        Statement statement;
        try {
            statement = conn.createStatement();
            int count = statement
                    .executeUpdate("CREATE TABLE Address (ID INT, StreetName VARCHAR(40),"
                    + " City VARCHAR(25), State CHAR(2), Zip CHAR(10))");
            System.out.println("Table created.");
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        timeBefore = System.currentTimeMillis();
        conn = instance.getConnection();
        timeAfter  = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);

        System.out.println(conn);

        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Address");

            System.out.println(rs.getWarnings());
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
