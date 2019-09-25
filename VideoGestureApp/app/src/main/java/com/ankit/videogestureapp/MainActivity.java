package com.ankit.videogestureapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    VideoView videoView;
    ImageView imageView;
    TextView textView2;
    GestureDetectorCompat gestureDetectorCompat = null;

    String[] videoPath = {"https://cdn.videvo.net/videvo_files/converted/2013_10/preview/BrightRedAutumnLeavesVidevo.mov71237.webm",
            "https://cdn.videvo.net/videvo_files/converted/2013_11/preview/ParkPathwayinAutumnVidevo.mov26856.webm",
            "https://cdn.videvo.net/videvo_files/converted/2014_08/preview/Palm_Trees_6Videvo_1.mov28467.webm",
            "https://cdn.videvo.net/videvo_files/converted/2013_11/preview/RedBerryinSunlightVidevo.mov42495.webm",
            "https://cdn.videvo.net/videvo_files/converted/2014_06/preview/Blue_Sky_and_Clouds_Timelapse_0892__Videvo.mov75480.webm"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // https://cdn.videvo.net/videvo_files/converted/2013_10/preview/BrightRedAutumnLeavesVidevo.mov71237.webm
        // https://cdn.videvo.net/videvo_files/converted/2013_11/preview/ParkPathwayinAutumnVidevo.mov26856.webm
        //https://cdn.videvo.net/videvo_files/converted/2014_08/preview/Palm_Trees_6Videvo_1.mov28467.webm
        //https://cdn.videvo.net/videvo_files/converted/2013_11/preview/RedBerryinSunlightVidevo.mov42495.webm
        //https://cdn.videvo.net/videvo_files/converted/2014_06/preview/Blue_Sky_and_Clouds_Timelapse_0892__Videvo.mov75480.webm
        //textView = (TextView) findViewById(R.id.textview);
        textView = (TextView) findViewById(R.id.VideoNumber);
        videoView = (VideoView) findViewById(R.id.videoview);
        imageView = (ImageView) findViewById(R.id.image);
        textView2 = (TextView) findViewById(R.id.text);

        Uri uri = Uri.parse(videoPath[2]);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        DetectSwipeGesture swipeGesture = new DetectSwipeGesture();
        swipeGesture.setActivity(MainActivity.this);
        gestureDetectorCompat = new GestureDetectorCompat(this, swipeGesture);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

   /* public void displayMessage(String Message){
        textView.setText(Message);
    }*/

}
