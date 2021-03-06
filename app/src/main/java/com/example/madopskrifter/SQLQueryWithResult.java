package com.example.madopskrifter;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQueryWithResult extends AsyncTask<String, Void, ResultSet> {
    private JobInterfaceParam jobInterface;

    public SQLQueryWithResult(JobInterfaceParam jobInterface)
    {
        this.jobInterface = jobInterface;
    }

    @Override
    protected ResultSet doInBackground(String... sqlStatement) {
        ResultSet resultSet = null;
        String connectionString = "jdbc:jtds:sqlserver://madopskrifter.database.windows.net/MadOpskrifter";

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // Det virker uden denne linje. Hvilket undrer os, da i alle eksempler vi har fundet bliver dette brugt i.
            Connection connection = DriverManager.getConnection(connectionString, "sonron", "Passw0rd");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    protected void onPostExecute(ResultSet resultSet) {
        super.onPostExecute(resultSet);
        try {
            jobInterface.doJob(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
