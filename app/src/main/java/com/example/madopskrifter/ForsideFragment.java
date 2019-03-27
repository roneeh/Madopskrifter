package com.example.madopskrifter;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForsideFragment extends Fragment {


    public TextView textView;

    public ForsideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forside, container, false);
        textView = view.findViewById(R.id.SomeText);
        JobInterface jobInterface = new JobInterface() {
            @Override
            public void doJob(ResultSet resultSet) {
                if (resultSet != null) {
                    try {
                        while (resultSet.next()) {

                            ForsideFragment.this.textView.append("\nUsername:" + resultSet.getString("brugerNavn"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        SQLQuery query = new SQLQuery(jobInterface);

        query.execute("SELECT * FROM Bruger");

        return view;
    }
}
