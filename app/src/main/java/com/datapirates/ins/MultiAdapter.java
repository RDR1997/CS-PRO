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

import java.util.ArrayList;

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MultiViewHolder>{


    private Context context;
    private ArrayList<Product> products;

    public MultiAdapter(Context context, ArrayList<Product> products){
        this.context = context;
        this.products = products;
    }

    public void setProducts(ArrayList<Product> products){
        this.products = new ArrayList<>();
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MultiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,
        parent, false);
        return new MultiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    //2- ViewHolder class
    class MultiViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.emp_name);
            imageView = itemView.findViewById(R.id.imageViewy);

        }

        //Getting selected items
        void bind (final Product product){
            imageView.setVisibility(product.isChecked() ? View.VISIBLE : View.GONE);
            textView.setText(product.getName());

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
    public ArrayList<Product> getAll(){ return products;}

    //Getting selected when btn clicked
    public ArrayList<Product> getSelected() {

        ArrayList<Product> selected = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).isChecked()) {
                selected.add(products.get(i));
            }
        }
        return selected;
    }

}
