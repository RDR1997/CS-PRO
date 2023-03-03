package com.datapirates.ins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rssireader.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

//    private ArrayList<Product> products = new ArrayList<>();


//--------------------------------------------------------------------------------------------------
public class selectProduct extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MultiAdapter multiAdapter;
    ArrayList<Product> list;
    ArrayList<String> sele;
    ArrayList<String> seleName;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        recyclerView = findViewById(R.id.mutilple_rv);
        database = FirebaseDatabase.getInstance().getReference("Product");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        btn = findViewById(R.id.toMap);

        list = new ArrayList<>();
        multiAdapter = new MultiAdapter(this,list);
        recyclerView.setAdapter(multiAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Product product = dataSnapshot.getValue(Product.class);
                    list.add(product);
                }

                multiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (multiAdapter.getSelected().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    sele = new ArrayList<>();
                    seleName = new ArrayList<>();
                    for (int i = 0; i < multiAdapter.getSelected().size(); i++) {
                        stringBuilder.append(multiAdapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");

                        sele.add(multiAdapter.getSelected().get(i).getItem_number());
                        seleName.add(multiAdapter.getSelected().get(i).getName());

                        gotoMap();
                    }
                    ShowToast(stringBuilder.toString().trim());

                }
                else {
                    ShowToast("No Selection");

                }
            }

        });


    }

    private void ShowToast (String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void gotoMap () {

            Intent intent = new Intent(selectProduct.this, MainActivity.class);
            intent.putExtra("key", sele);
            intent.putExtra("keyName", seleName);
            startActivity(intent);

       }
}
