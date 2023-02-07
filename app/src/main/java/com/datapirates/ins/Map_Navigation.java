package com.datapirates.ins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.rssireader.R;

public class Map_Navigation extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_navigation);

        image = (ImageView) findViewById(R.id.imageView);
        moveAnimation();


    }

    public void moveAnimation() {
        Animation img = new TranslateAnimation(Animation.ABSOLUTE, 200,Animation.ABSOLUTE,Animation.ABSOLUTE);
        img.setDuration(1000);
        img.setFillAfter(true);

        image.startAnimation(img);
    }
}
