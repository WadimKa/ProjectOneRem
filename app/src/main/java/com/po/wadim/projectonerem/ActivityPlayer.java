package com.po.wadim.projectonerem;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class ActivityPlayer extends AppCompatActivity {
    private int id, FLAG = 0;
    private MediaPlayer mediaPlayer;
    private String TAG_FOR_SEND_STATION, nameOfStationsImage, TYPE_OF_RESOURCE = "drawable";
    private TextView title;
    private ImageView imageOfStation, buttonPlay;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        TAG_FOR_SEND_STATION = ActivityOfListStation.TAG_FOR_SEND_STATION;
        id = getIntent().getIntExtra(TAG_FOR_SEND_STATION, 0);
        nameOfStationsImage = getResources().getStringArray(R.array.listOfImages)[id];

        title = (TextView) findViewById(R.id.TVPlayerTitle);
        buttonPlay = (ImageView) findViewById(R.id.IBPlayerPlayButton);
        imageOfStation = (ImageView) findViewById(R.id.IVPlayerImageOfStation);
        progressBar = (ProgressBar) findViewById(R.id.PBPlayer);

        title.setText(getResources().getStringArray(R.array.listOfNames)[id]);
        imageOfStation.setImageResource(getResources().getIdentifier(nameOfStationsImage, TYPE_OF_RESOURCE, getPackageName()));


    }

    public void onClick(View view) throws IOException {
        if(FLAG == 0) {
            progressBar.setVisibility(View.VISIBLE);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getResources().getStringArray(R.array.listOfLinks)[id]);
            mediaPlayer.prepare();
            mediaPlayer.start();
            buttonPlay.setImageResource(R.drawable.button_pause);
            progressBar.setVisibility(View.INVISIBLE);
            FLAG = 1;
        }else{
            mediaPlayer.release();
            mediaPlayer = null;
            buttonPlay.setImageResource(R.drawable.button_play);
            FLAG = 0;
        }
    }
}
