package com.datapirates.ins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rssireader.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

                    for (int i = 0; i < multiAdapter.getSelected().size(); i++) {
                        stringBuilder.append(multiAdapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");

                    }

                    ShowToast(stringBuilder.toString().trim());
                } else
                    ShowToast("No Selection");
                gotoMap();
            }

        });

    }

    private void ShowToast (String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void gotoMap () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
