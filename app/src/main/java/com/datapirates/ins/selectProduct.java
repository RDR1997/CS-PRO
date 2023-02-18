package com.datapirates.ins;

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

import java.util.ArrayList;

public class selectProduct extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<Product> products = new ArrayList<>();
    private MultiAdapter adapter;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        recyclerView = findViewById(R.id.mutilple_rv);
        btn = findViewById(R.id.toMap);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        adapter = new MultiAdapter(this, products);
        recyclerView.setAdapter(adapter);

        CreateListofData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected().size()>0){
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i =0; i<adapter.getSelected().size(); i++) {
                        stringBuilder.append(adapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");

                    }

                    ShowToast(stringBuilder.toString().trim());
                }else
                    ShowToast("No Selection");
                gotoMap();
            }

        });


    }

    private void CreateListofData() {
        products = new ArrayList<>();

        for(int i=0; i<10; i++){
            Product product = new Product();
            product.setName("Products "+ (i+1));

            if (i==0){
                product.setChecked(true);
            }
            products.add(product);
        }
        adapter.setProducts(products);
    }
    
    private void ShowToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void gotoMap(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}