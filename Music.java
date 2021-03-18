package com.example.sonyvaio.funtime;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;

import java.io.IOException;

public class Music extends AppCompatActivity {

    ListView lvMusic;
    String name[],path[];
    MediaPlayer mp;
    Button btnPause,btnStop;
    ImageButton imageButtonForward, imageButtonRewind,imageButtonNext, imageButtonPrevious;
    private int currentSongIndex;

    private Handler myHandler = new Handler();

    final int seekForwardTime = 5 * 1000;
    final int seekBackwardTime = 5 * 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        lvMusic = (ListView) findViewById(R.id.lvMusic);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop = (Button) findViewById(R.id.btnStop);
        imageButtonForward = (ImageButton) findViewById(R.id.imageButtonForward);
        imageButtonRewind = (ImageButton) findViewById(R.id.imageButtonRewind);
        imageButtonNext = (ImageButton) findViewById(R.id.imageButtonNext);
        imageButtonPrevious = (ImageButton) findViewById(R.id.imageButtonPrevious);







        mp = new MediaPlayer();
        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(lvMusic);
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        name = new String[cursor.getCount()];
        path = new String[cursor.getCount()];

        int i = 0;

        while (cursor.moveToNext()) {

            name[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            path[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            i++;


        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);

        lvMusic.setAdapter(adapter);

        final int finalI = i;
        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String p = path[i];


                try {
                    mp.reset();
                    mp.setDataSource(p);
                    mp.prepare();
                    mp.start();
                    btnPause.setEnabled(true);
                    btnStop.setEnabled(true);
                    btnPause.setText("Pause");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mp.isPlaying()) {
                    mp.pause();
                    btnPause.setText("Resume");
                } else {
                    mp.start();
                    btnPause.setText("Pause");

                }

            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                btnStop.setEnabled(false);
                btnStop.setEnabled(false);
            }
        });


        imageButtonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mp.getCurrentPosition();

                if (currentPosition + seekForwardTime <= mp.getDuration()) {
                    mp.seekTo(currentPosition + seekForwardTime);
                } else {

                    mp.seekTo(mp.getDuration());
                }
            }

        });
        imageButtonRewind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                int currentPosition = mp.getCurrentPosition();

                if (currentPosition - seekBackwardTime >= 0) {

                    mp.seekTo(currentPosition - seekBackwardTime);
                } else {

                    mp.seekTo(0);
                }


            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            // @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                try {

                    // String p = path[(currentSongIndex+1)%lvMusic.getScrollBarSize()];

                    if (currentSongIndex == lvMusic.getCount() + 1) {
                        currentSongIndex = 0;
                    } else {
                        currentSongIndex++;
                    }

                    String p = path[currentSongIndex];
                    mp.reset();
                    mp.setDataSource(p);
                    mp.prepare();
                    mp.start();
                    btnPause.setEnabled(true);
                    btnStop.setEnabled(true);
                    imageButtonRewind.setEnabled(true);
                    imageButtonForward.setEnabled(true);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        });
        imageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    // String p = path[(currentSongIndex+1)%lvMusic.getScrollBarSize()];

                    if (currentSongIndex == 0) {
                        currentSongIndex = lvMusic.getCount() - 1;
                    } else {
                        currentSongIndex--;
                    }

                    String p = path[currentSongIndex];
                    mp.reset();
                    mp.setDataSource(p);
                    mp.prepare();
                    mp.start();
                    btnPause.setEnabled(true);
                    btnStop.setEnabled(true);
                    imageButtonRewind.setEnabled(true);
                    imageButtonForward.setEnabled(true);


                } catch (IOException e) {
                    e.printStackTrace();
                }

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
                        btnStop.setEnabled(false);
                        btnStop.setEnabled(false);

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
