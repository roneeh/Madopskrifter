package com.example.madopskrifter;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpretOpskriftFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    private LinearLayout billedeTitelLayout, ingrediensLayout, trinLayout;
    private ImageView opskriftBilledeImageView;
    private TextView opskriftBilledeTextView, overskriftTextView;
    private EditText titelOpskriftEditText;
    private Button naesteButton;
    private View view;

    private int opretOpskriftTrin = 0;

    public OpretOpskriftFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_opret_opskrift, container, false);

        opskriftBilledeImageView = view.findViewById(R.id.opskriftBilledeImageView);
        opskriftBilledeTextView = view.findViewById(R.id.opskriftBilledeTextView);
        titelOpskriftEditText = view.findViewById(R.id.titelOpskriftEditText);
        naesteButton = view.findViewById(R.id.naesteButton);
        trinLayout = view.findViewById(R.id.trinLayout);
        billedeTitelLayout = view.findViewById(R.id.billedeTitelLayout);
        ingrediensLayout = view.findViewById(R.id.ingrediensLayout);
        overskriftTextView = view.findViewById(R.id.overskriftTextView);

        opskriftBilledeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Vælg Billede"), PICK_IMAGE);
            }
        });

        titelOpskriftEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (Helpers.KeyBoardEnterPressed(event, keyCode)) {
                    Helpers.RemoveKeyboard(titelOpskriftEditText);
                    return true;
                }

                return false;
            }
        });

        naesteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opretOpskriftTrin++;
                switch (opretOpskriftTrin) {
                    case 1:
                        billedeTitelLayout.setVisibility(View.GONE);
                        ingrediensLayout.setVisibility(View.VISIBLE);
                        overskriftTextView.setText("Vælg ingredienser");
                        break;
                    case 2:
                        ingrediensLayout.setVisibility(View.GONE);
                        trinLayout.setVisibility(View.VISIBLE);
                        overskriftTextView.setText("Opret trin");
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            if (data.getData() != null) {
                Uri selectedImage = data.getData();
                Picasso.with(this.getContext()).load(selectedImage).rotate(Helpers.getExifAngle(this.getContext(), selectedImage)).fit().into(opskriftBilledeImageView);
                opskriftBilledeTextView.setText(null);
            }
        }
    }
}
