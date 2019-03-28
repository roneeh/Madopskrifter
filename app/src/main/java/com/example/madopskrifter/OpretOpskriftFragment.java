package com.example.madopskrifter;

import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpretOpskriftFragment extends Fragment {

    public static final int PICK_IMAGE = 1;

    private ImageView opskriftBillede;
    private View view;

    public OpretOpskriftFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_opret_opskrift, container, false);
        opskriftBillede = view.findViewById(R.id.opskriftBillede);
        opskriftBillede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Vælg Billede"), PICK_IMAGE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            if(data.getData() != null) {
                Uri selectedImage = data.getData();
                Picasso.with(this.getContext()).load(selectedImage).rotate(Helpers.getExifAngle(this.getContext(), selectedImage)).fit().into(opskriftBillede);
            }
        }
    }



}
