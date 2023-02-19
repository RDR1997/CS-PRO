package com.datapirates.ins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssireader.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MyViewHolder>{

    Context context;
    ArrayList<Product> list;

    public MultiAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    public void setProducts(ArrayList<Product> list){
        this.list = new ArrayList<>();
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Product product = list.get(position);
//        holder.name.setText(product.getName());
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.emp_name);
            imageView = itemView.findViewById(R.id.imageViewy);

        }

        //Getting selected items
        void bind (final Product product){
            imageView.setVisibility(product.isChecked() ? View.VISIBLE : View.GONE);
            name.setText(product.getName());


            itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        product.setChecked(!product.isChecked());
                        imageView.setVisibility(product.isChecked() ? View.VISIBLE : View.GONE);
                    }
                });
            }

        }

    //Getting All Items selected
    public ArrayList<Product> getAll(){ return list;}

    //Getting selected when btn clicked
    public ArrayList<Product> getSelected() {

        ArrayList<Product> selected = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                selected.add(list.get(i));
            }
        }
        return selected;
    }
}




