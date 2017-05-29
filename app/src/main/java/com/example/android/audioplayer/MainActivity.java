package com.example.android.audioplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    SeekBar scrubber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this,R.raw.alia);

        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxvol=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currvol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl=(SeekBar)findViewById(R.id.seekBar);
        volumeControl.setMax(maxvol);
        volumeControl.setProgress(currvol);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        scrubber=(SeekBar)findViewById(R.id.scrubber);
        scrubber.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mediaPlayer.getCurrentPosition());

            }
        },0,1000);


        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        }




    public void play(View view) {
        mediaPlayer.start();
    }

    public void stop(View view) {
        mediaPlayer.stop();
    }
    public void pause(View view) {
        mediaPlayer.pause();
    }
}
