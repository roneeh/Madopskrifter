package com.example.madopskrifter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public Profil currentProfile;

    public static MainActivity currentMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentMainActivity = this;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        findViewById(R.id.forsideButton).setOnClickListener(this);
        findViewById(R.id.opretOpskriftButton).setOnClickListener(this);
        findViewById(R.id.hjaelpButton).setOnClickListener(this);
        findViewById(R.id.indstillingerButton).setOnClickListener(this);
        findViewById(R.id.menuLayout).setVisibility(View.GONE);
        ChangeFragment(new LoginFragment());

    }

    protected void ChangeFragment(Fragment fragment)
    {
        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.mainContent) != null) {
            fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.mainContent)).commit();
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainContent,fragment, null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.opretOpskriftButton:
                ChangeFragment(new OpretOpskriftFragment());
                break;
/*            case R.id.indstillingerButton:
                ChangeFragment(new );*/
            default:
                ChangeFragment(new ForsideFragment());
                break;
        }
    }
}
