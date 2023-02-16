package com.datapirates.ins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rssireader.R;

import java.util.ArrayList;

public class Map_Navigation extends AppCompatActivity {

    public ArrayList<Integer> products;
    public ArrayList<Integer> routes;
    private static ImageView imageView;
    private static Button button;
    private int current_image;
    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_navigation);
        buttonclick();

        products = new ArrayList<>();
        products.add(1);        //A
        products.add(2);        //B
        products.add(3);        //C
        products.add(4);        //D
        products.add(5);        //E

        routes = new ArrayList<Integer>();
        if(products.size()==5){
            routes.add(R.drawable.a_a);
            routes.add(R.drawable.a_a);
            routes.add(R.drawable.a_b);
            routes.add(R.drawable.b_c);
            routes.add(R.drawable.c_e);
            routes.add(R.drawable.d_e);

        }
        if(products.size()==4){
            if (products.get(0)==1) {
                routes.add(R.drawable.a_a);
                routes.add(R.drawable.a_a);
                if (products.get(1)==2) {
                    routes.add(R.drawable.a_b);
                    if (products.get(2)==3) {
                        routes.add(R.drawable.b_c);
                        if (products.get(3)==4) {
                            routes.add(R.drawable.c_d);
                        }
                        if (products.get(3)==5) {
                            routes.add(R.drawable.c_e);
                        }

                    }
                    if (products.get(2)==4) {
                        routes.add(R.drawable.b_d);
                        routes.add(R.drawable.d_e);

                    }
                }
                if (products.get(1)==3) {
                    routes.add(R.drawable.a_c);
                    routes.add(R.drawable.c_e);
                    routes.add(R.drawable.d_e);
                }
            }
            if (products.get(0)==2) {
                routes.add(R.drawable.b_b);
                routes.add(R.drawable.b_b);
                routes.add(R.drawable.b_c);
                routes.add(R.drawable.c_e);
                routes.add(R.drawable.d_e);
            }
        }
        if(products.size()==3){
            if (products.get(0)==1){
                routes.add(R.drawable.a_a);
                routes.add(R.drawable.a_a);
                if (products.get(1)==2){
                    routes.add(R.drawable.a_b);
                    if (products.get(2)==3) {
                        routes.add(R.drawable.b_c);
                    }
                    if (products.get(2)==4){
                        routes.add(R.drawable.b_d);
                    }
                    if (products.get(2)==5){
                        routes.add(R.drawable.b_d);
                    }
                }
                if (products.get(1)==3){
                    routes.add(R.drawable.a_c);
                    if (products.get(2)==4){
                        routes.add(R.drawable.c_d);
                    }
                    if (products.get(2)==5){
                        routes.add(R.drawable.c_e);
                    }
                }
                if (products.get(1)==4){
                    routes.add(R.drawable.a_d);
                    routes.add(R.drawable.d_e);
                }
            }
            if (products.get(0)==2){
                routes.add(R.drawable.b_b);
                routes.add(R.drawable.b_b);
                if (products.get(1)==3) {
                    routes.add(R.drawable.b_c);
                    if (products.get(2)==4) {
                        routes.add(R.drawable.c_d);
                    }
                    if (products.get(2)==5) {
                        routes.add(R.drawable.c_e);
                    }
                }
                if (products.get(1)==4) {
                    routes.add(R.drawable.b_d);
                    routes.add(R.drawable.d_e);
                }
            }
            if (products.get(0)==3){
                routes.add(R.drawable.c_c);
                routes.add(R.drawable.c_c);
                routes.add(R.drawable.c_e);
                routes.add(R.drawable.d_e);

            }
        }
        if(products.size()==2) {
            if (products.get(0) == 1) {
                routes.add(R.drawable.a_a);
                routes.add(R.drawable.a_a);
                if (products.get(1) == 2) {
                    routes.add(R.drawable.a_b);
                }
                if (products.get(1) == 3) {
                    routes.add( R.drawable.a_c);
                }
                if (products.get(1) == 4) {
                    routes.add(R.drawable.a_d);
                }
                if (products.get(1) == 5) {
                    routes.add(R.drawable.a_e);
                }
            }
            if (products.get(0) == 2) {
                routes.add(R.drawable.b_b);
                routes.add(R.drawable.b_b);
                if (products.get(1) == 3) {
                    routes.add(R.drawable.b_c);
                }
                if (products.get(1) == 4) {
                    routes.add( R.drawable.b_d);
                }
                if (products.get(1) == 5) {
                    routes.add(R.drawable.b_e);
                }
            }
            if (products.get(0) == 3) {
                routes.add(R.drawable.c_c);
                routes.add(R.drawable.c_c);
                if (products.get(1) == 4) {
                    routes.add(R.drawable.c_d);
                }
                if (products.get(1) == 5) {
                    routes.add(R.drawable.c_e);
                }
            }
            if (products.get(0) == 4) {
                routes.add(R.drawable.d_d);
                routes.add(R.drawable.d_d);
                if (products.get(1) == 5) {
                    routes.add(R.drawable.d_e);
                }
            }
        }
        if(products.size()==1){
            if (products.get(0)==1){
                routes.add(R.drawable.a_a);
                routes.add(R.drawable.a_a);
            }
            if (products.get(0)==2){
                routes.add(R.drawable.b_b);
                routes.add(R.drawable.b_b);
            }
            if (products.get(0)==3){
                routes.add(R.drawable.c_c);
                routes.add(R.drawable.c_c);
            }
            if (products.get(0)==4){
                routes.add(R.drawable.d_d);
                routes.add(R.drawable.d_d);
            }
            if (products.get(0)==5){
                routes.add(R.drawable.e_e);
                routes.add(R.drawable.e_e);
            }
        }
    }

    public void buttonclick() {

        imageView = (ImageView) findViewById(R.id.imageView3);
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_image++;
                current_image = current_image % routes.size();
                imageView.setImageResource(routes.get(current_image));
            }
        });
    }
}