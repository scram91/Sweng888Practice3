package edu.psu.sweng888.sweng888practice4;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    //Full list oif items displayed in main activity
    private List<Products> products;
    boolean isSelectMode = false;
    ArrayList<Products> selectedItems = new ArrayList<>();
    //Constructor
    public ProductAdapter(List<Products> products) {
        this.products = products;
    }

    @Override
    @NonNull
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Products product = products.get(position);
        holder.nameTextView.setText(product.getName());
        holder.sellerTextView.setText(product.getSeller());
        holder.priceTextView.setText(product.getPrice());
        holder.descriptionTextView.setText(product.getDescription());
    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, sellerTextView, priceTextView, descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            sellerTextView = itemView.findViewById(R.id.sellerTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    isSelectMode = true;
                    if(selectedItems.contains(products.get(getAdapterPosition()))){
                        itemView.setBackgroundColor(Color.TRANSPARENT);
                        selectedItems.remove(products.get(getAdapterPosition()));
                    }else{
                        itemView.setBackgroundColor(Color.LTGRAY);
                        selectedItems.add(products.get(getAdapterPosition()));
                    }
                    if (selectedItems.size()==0)
                        isSelectMode=false;
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelectMode){
                        if (selectedItems.contains(products.get(getAdapterPosition()))){
                            itemView.setBackgroundColor(Color.TRANSPARENT);
                            selectedItems.remove(products.get(getAdapterPosition()));
                        }
                        else{
                            itemView.setBackgroundColor(Color.LTGRAY);
                            selectedItems.add(products.get(getAdapterPosition()));
                        }
                        if(selectedItems.size()==0){
                            isSelectMode=false;
                        }
                    }
                }
            });

        }
    }
}
