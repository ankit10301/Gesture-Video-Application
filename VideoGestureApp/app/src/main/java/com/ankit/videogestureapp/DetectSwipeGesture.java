package com.ankit.videogestureapp;

import android.net.Uri;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class DetectSwipeGesture extends GestureDetector.SimpleOnGestureListener {

    private static int MIN_DISTANCE_X = 150;
    private static int MIN_DISTANCE_Y = 150;

    private static int MAX_DISTANCE_X = 1000;
    private static int MAX_DISTANCE_Y = 1000;

    int vidCount = 2;

    private MainActivity activity = null;

    public MainActivity getActivity(){
        return activity;
    }

    public void setActivity(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaX = e1.getX() - e2.getX();
        float deltaY = e1.getY() - e2.getY();

        float deltaXabs = Math.abs(deltaX);
        float deltaYabs = Math.abs(deltaY);
// Detects Left or Right Swipe
        if(deltaXabs >= MIN_DISTANCE_X && deltaXabs <= MAX_DISTANCE_X){
            if (deltaX > 0){
                //activity.displayMessage("Swipe to left");

                Toast.makeText(activity, "Subscribed!", Toast.LENGTH_SHORT).show();
            }else {
                //activity.displayMessage("Swipe to right");
                activity.textView2.setVisibility(View.VISIBLE);
                activity.imageView.setVisibility(View.VISIBLE);
            }
        }
// Detects Top or Down Swipe
        if(deltaYabs >= MIN_DISTANCE_Y && deltaYabs <= MAX_DISTANCE_Y){
            if (deltaY > 0){
                //activity.displayMessage("Swipe to top");
                if (vidCount>0) {
                    Uri uri = Uri.parse(activity.videoPath[vidCount - 1]);
                    vidCount = vidCount - 1;
                    activity.videoView.setVideoURI(uri);
                    int v2 = vidCount + 1;
                    Toast.makeText(activity, "Loading Video: " + v2 + " Please Wait!", Toast.LENGTH_SHORT).show();
                    activity.textView.setText("Video Number: " + v2);
                }

            }else {
                //activity.displayMessage("Swipe to down");
                if (vidCount<4){
                    Uri uri = Uri.parse(activity.videoPath[vidCount + 1]);
                    vidCount = vidCount + 1;
                    activity.videoView.setVideoURI(uri);
                    int v2 = vidCount + 1;
                    Toast.makeText(activity, "Loading Video: " + v2 + " Please Wait!", Toast.LENGTH_SHORT).show();
                    activity.textView.setText("Video Number: " + v2);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        //activity.displayMessage("Single Tap");
        activity.textView2.setVisibility(View.INVISIBLE);
        activity.imageView.setVisibility(View.INVISIBLE);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //activity.displayMessage("Double Tap");
        return true;
    }
}
