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
public class LoginFragment extends Fragment {

    EditText txtUsername, txtPassword;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = view.findViewById(R.id.loginButton);
        Button registerButton = view.findViewById(R.id.registerButton);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtUsername = view.findViewById(R.id.txtUsername);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLQueryWithResult sqlQueryWithResult = new SQLQueryWithResult(new JobInterface() {
                    @Override
                    public void doJob(ResultSet resultSet) {
                            if(resultSet != null) {
                                MainActivity.currentMainActivity.ChangeFragment(new ForsideFragment());
                                MainActivity.currentMainActivity.findViewById(R.id.menuLayout).setVisibility(View.VISIBLE);
                            }
                    }
                });
                sqlQueryWithResult.execute("SELECT * FROM Bruger WHERE brugerNavn='" + LoginFragment.this.txtUsername.getText() + "' AND brugerPassword='" + LoginFragment.this.txtPassword.getText() + "'" );
            }
        });
        return view;
    }

}
