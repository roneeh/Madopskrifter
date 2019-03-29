package com.example.madopskrifter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;


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

        SQLQueryWithResult query = new SQLQueryWithResult(jobInterface);

        query.execute("SELECT * FROM Bruger");

        return view;
    }
}
