package com.example.sonyvaio.funtime;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Vedio extends AppCompatActivity {

    ListView lvVedio;
    String name[],path[];
    MediaPlayer mp;
    private int currentSongIndex;


    Button btnPause,btnStop;
    ImageButton imageButtonForward, imageButtonRewind,imageButtonNext, imageButtonPrevious;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);


        lvVedio = (ListView) findViewById(R.id.lvVedio);
        final VideoView videoView = (VideoView) findViewById(R.id.videoView);
        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop = (Button) findViewById(R.id.btnStop);
        imageButtonForward = (ImageButton) findViewById(R.id.imageButtonForward);
        imageButtonRewind = (ImageButton) findViewById(R.id.imageButtonRewind);
        imageButtonNext = (ImageButton) findViewById(R.id.imageButtonNext);
        imageButtonPrevious = (ImageButton) findViewById(R.id.imageButtonPrevious);


        mp = new MediaPlayer();

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        name = new String[cursor.getCount()];
        path = new String[cursor.getCount()];

        int i = 0;

        while (cursor.moveToNext()) {

            name[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
            path[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
            i++;


        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);

        lvVedio.setAdapter(adapter);

        final int finalI = i;
        lvVedio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String p = path[i];

                videoView.setMediaController(mediaController);
                videoView.setVideoPath(p);
                videoView.start();

            }
        });

    }
    public void onBackPressed()  {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit this Music Player?");
        builder.setCancelable(false);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mp.stop();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Exit");
        alert.show();


    }


}
