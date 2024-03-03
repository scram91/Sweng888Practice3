package edu.psu.sweng888.sweng888practice4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedProductAdapter extends RecyclerView.Adapter<SelectedProductAdapter.ViewHolder> {

    private ArrayList<Products> products;

    //Constructor
    public SelectedProductAdapter(ArrayList<Products> products) {this.products=products;}

    //Creating new ViewHolder
    @NonNull
    @Override
    public SelectedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    //Binding attributes from the Product to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull SelectedProductAdapter.ViewHolder holder, int position) {
        Products product = products.get(position);

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(product.getPrice());
        holder.seller.setText(product.getSeller());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,description,price,seller;

        public ViewHolder(View itemView) {
            super(itemView);
            //assigning TextView items to corresponding view id
            name = itemView.findViewById(R.id.nameTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            price = itemView.findViewById(R.id.priceTextView);
            seller = itemView.findViewById(R.id.sellerTextView);


        }
    }

    public void removeAll(ArrayList<Products> product){
        product.clear();
        notifyDataSetChanged();
    }

}
