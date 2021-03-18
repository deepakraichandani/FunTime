package com.example.sonyvaio.funtime;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by sony vaio on 21/03/2017.
 */
public class Tab2Online extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.tab2online, container, false);

        final Intent i = new Intent(getActivity(), Online.class);
        Button Online = (Button) View.findViewById(R.id.btnOnline);
        Online.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url="https://www.youtube.com/channel/UC-9-kyTW8ZkZNDHQJ6FgpwQ";
                Intent w=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(w);
            }
        });
        return View;
    }
}