package com.example.madopskrifter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;


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
            Helpers helpers = new Helpers();
            ResultSet resultSet = helpers.execute("SELECT * FROM Brugere").get();
            while (resultSet.next()) {
                textView.append("Username:" + resultSet.getString("Brugernavn"));
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
