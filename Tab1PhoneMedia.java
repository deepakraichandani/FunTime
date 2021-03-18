package com.example.sonyvaio.funtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by sony vaio on 21/03/2017.
 */
public class Tab1PhoneMedia extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.tab1phonemedia, container, false);

        final Intent i = new Intent(getActivity(), Music.class);
        Button Music = (Button) View.findViewById(R.id.btnMusic);
        Music.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(i);
            }
        });

        final Intent a = new Intent(getActivity(),Vedio.class);
        Button Video = (Button) View.findViewById(R.id.btnVideo);
       Video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(a);
            }
        });

        return View;
    }
}
