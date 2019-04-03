package com.example.madopskrifter;


import android.os.Bundle;
import android.support.constraint.solver.widgets.Helper;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText txtUsername, txtPassword;
    View view;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = view.findViewById(R.id.loginButton);
        Button registerButton = view.findViewById(R.id.registerButton);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtUsername = view.findViewById(R.id.txtUsername);

        txtUsername.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(Helpers.KeyBoardEnterPressed(event, keyCode))
                {
                    txtPassword.requestFocus();
                    return true;
                }
                return false;
            }
        });

        txtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(Helpers.KeyBoardEnterPressed(event, keyCode))
                {
                    Helpers.RemoveKeyboard(txtPassword);
                    Login();
                    return true;
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.currentMainActivity.ChangeFragment(new RegisterFragment());
            }
        });

        return view;
    }

    /**
     *Den skal eksekvere data fra datatbase til at login i applikationen
     */
    private void Login()
    {
        SQLQueryWithResult sqlQueryWithResult = new SQLQueryWithResult(new JobInterfaceParam() {
            @Override
            public void doJob(ResultSet resultSet) throws SQLException {
                if(resultSet.next()) {
                    MainActivity.currentMainActivity.ChangeFragment(new ForsideFragment());
                    MainActivity.currentMainActivity.findViewById(R.id.menuLayout).setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(view.getContext(), "Brugernavn eller password er forkert!", Toast.LENGTH_LONG).show();

                }
            }
        });
        sqlQueryWithResult.execute("SELECT * FROM Bruger WHERE brugerNavn='" + LoginFragment.this.txtUsername.getText() + "' AND brugerPassword='" + LoginFragment.this.txtPassword.getText() + "'" );
    }

}
