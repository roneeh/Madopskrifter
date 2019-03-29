package com.example.madopskrifter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.sql.ResultSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText txtUsername, txtEmail, txtPassword, txtConfirmPass;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        Button register = view.findViewById(R.id.registerButton);
        Button cancel = view.findViewById(R.id.cancelButton);
        txtUsername = view.findViewById(R.id.txtUsername);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtConfirmPass = view.findViewById(R.id.txtConfirmPassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLQuery sqlQuery = new SQLQuery(new JobInterface() {

                    @Override
                    public void doJob(ResultSet resultSet) {

                    }
                });

                sqlQuery.execute("Create Table  Bruger where brugerNavn='" + txtUsername + "' +
                    brugerPassword='" txtPassword "' " );

            }
        });

        return view;
    }

}
