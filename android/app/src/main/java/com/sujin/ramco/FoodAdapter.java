package com.sujin.ramco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter  extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private ArrayList<FoodItem> foodItems;
    int total = 0;
    TextView textView;

    public FoodAdapter(Context context, ArrayList<FoodItem> foodItems, TextView textView) {
        this.foodItems = foodItems;
        this.textView = textView;

    }
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View foodItem;

            foodItem = layoutInflater.inflate(R.layout.food_item, parent, false);


        return new FoodAdapter.FoodViewHolder(foodItem,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, final int position) {
        holder.food_name.setText(foodItems.get(position).getName()+"\nRs. "+Integer.toString(foodItems.get(position).getCost()));
        holder.quantityTV.setText(Integer.toString(foodItems.get(position).getQuantity()));
        holder.foodImage.setImageResource(foodItems.get(position).getImage());

        holder.leftIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(foodItems.get(position).getQuantity()!=0) {
                    foodItems.get(position).setQuantity(foodItems.get(position).getQuantity() - 1);
                    holder.quantityTV.setText(Integer.toString(foodItems.get(position).getQuantity()));
                    total-=foodItems.get(position).getCost();
                    textView.setText("Total : "+Integer.toString(total));
                }
            }
        });

        holder.rightIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodItems.get(position).setQuantity(foodItems.get(position).getQuantity()+1);
                holder.quantityTV.setText(Integer.toString(foodItems.get(position).getQuantity()));
                total+=foodItems.get(position).getCost();
                textView.setText("Total : "+Integer.toString(total));
            }
        });
    }



    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        public TextView food_name,quantityTV;
        ImageView leftIV,rightIV,foodImage;

        public FoodViewHolder(View itemView, int viewType) {
            super(itemView);

            food_name = itemView.findViewById(R.id.food_name);
            quantityTV = itemView.findViewById(R.id.quantityTV);
            leftIV = itemView.findViewById(R.id.leftIV);
            rightIV = itemView.findViewById(R.id.rightIV);
            foodImage = itemView.findViewById(R.id.food_image);

        }
    }

}
