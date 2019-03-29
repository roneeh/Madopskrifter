package com.example.madopskrifter;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQuery extends AsyncTask<String, Void, Void> {
    private JobInterface jobInterface;

    public SQLQuery(JobInterface jobInterface)
    {
        this.jobInterface = jobInterface;
    }

    @Override
    protected Void doInBackground(String... sqlStatement) {
        String connectionString = "jdbc:jtds:sqlserver://madopskrifter.database.windows.net/MadOpskrifter";

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // Det virker uden denne linje. Hvilket undrer os, da i alle eksempler vi har fundet bliver dette brugt i.
            Connection connection = DriverManager.getConnection(connectionString, "sonron", "Passw0rd");
            Statement statement = connection.createStatement();
            statement.execute(sqlStatement[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        jobInterface.doJob();
    }
}
