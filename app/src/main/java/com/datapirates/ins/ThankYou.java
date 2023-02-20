package com.datapirates.ins;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rssireader.R;

import java.util.ArrayList;

public class ThankYou extends AppCompatActivity {
    private Button closeApplicationBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        	TextView textView;
        	textView = findViewById(R.id.plist);
            ArrayList<String> productsList = (ArrayList<String>) getIntent().getSerializableExtra("keyName");

        textView.setText(String.valueOf(productsList));

        // on below line we are initializing variables with ids.
        closeApplicationBtn = findViewById(R.id.button3);

        // on below line we are adding click listener for our button
        closeApplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are finishing activity.
                ThankYou.this.finishAffinity();

                // on below line we are exiting our activity
                System.exit(0);
            }
        });

    }

//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
    public void onClickAgain (View view){

        shopagainBtn();
    }

    public void shopagainBtn(){

        Intent intent = new Intent(this, selectProduct.class);
        startActivity(intent);

    }
}