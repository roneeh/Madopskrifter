package com.example.madopskrifter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForsideFragment extends Fragment {


    public ForsideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forside, container, false);

        TextView textView = view.findViewById(R.id.SomeText);
        try {
            SQLQuery query = new SQLQuery();
            ResultSet resultSet = query.execute("SELECT * FROM Bruger").get();
            if (resultSet != null)
            {
                while (resultSet.next()) {
                    textView.append("\nUsername:" + resultSet.getString("brugerNavn"));
                }
            }
        }
        catch (SQLException ex)
        {
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return view;
    }
}
