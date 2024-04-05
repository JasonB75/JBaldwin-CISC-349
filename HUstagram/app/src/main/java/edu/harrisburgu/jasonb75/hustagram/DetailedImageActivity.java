package edu.harrisburgu.jasonb75.hustagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedImageActivity extends AppCompatActivity {
    /*Parts of code adapted from https://www.geeksforgeeks.org/how-to-build-a-photo-viewing-application-in-android/ */
    private ImageView imageView;
    private TextView commentView;
    private ScaleGestureDetector scaleGestureDetector;
    private static Post post;

    // on below line we are defining our scale factor.
    private float mScaleFactor = 1.0f;


    public static Intent newDetailedIntent(Context packageContext, Post incoming_post) {
        Intent i = new Intent(packageContext, DetailedImageActivity.class);
        //i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        post = incoming_post;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_image);

        Bitmap image = post.getImage();
        String comment = post.getComment();

        // initializing our image view.
        imageView = findViewById(R.id.idIVImage);
        commentView = findViewById(R.id.commentView);

        // on below line we are initializing our scale gesture detector for zoom in and out for our image.
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        imageView.setImageBitmap(image);
        commentView.setText(post.getComment());
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // inside on touch event method we are calling on
        // touch event method and passing our motion event to it.
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        // on below line we are creating a class for our scale
        // listener and  extending it with gesture listener.
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {

            // inside on scale method we are setting scale
            // for our image in our image view.
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

            // on below line we are setting
            // scale x and scale y to our image view.
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}