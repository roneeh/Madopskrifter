package com.example.madopskrifter;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

public class Helpers extends AsyncTask<String, String, ResultSet>{
    @Override
    protected ResultSet doInBackground(String... sqlStatement) {
        ResultSet resultSet = null;
        String connectionString = "jdbc:jtds:sqlserver://madopskrifter.database.windows.net/databaseName=MadOpskrifter; user = sonron; password = Passw0rd;";

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement[0]);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
