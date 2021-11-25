package com.example.restaurant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.information.InformationActivity;
import com.example.restaurant.information.InformationFragment;
import com.example.restaurant.pojos.Dish;
import com.squareup.picasso.Picasso;

import java.util.EventListener;
import java.util.List;

public class DishRV extends RecyclerView.Adapter<DishRV.ViewHolder> {

    final List<Dish> mValues;

    public static int sendPosition;

    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;

    public DishRV(List<Dish> mValues) {
        this.mValues = mValues;
    }

    @NonNull
    @Override
    public DishRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM1) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu_portrait, parent, false);
            return new ViewHolder(view);
        } else if (viewType == TYPE_ITEM2) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu_landscape, parent, false);
            return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DishRV.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int viewType = getItemViewType(position);

        if (viewType == TYPE_ITEM1) {
            holder.tvDishName.setText(mValues.get(position).getName());
            holder.tvDishPrice.setText(mValues.get(position).getPrice());
            Picasso.with(MainActivity.getAppContext())
                    .load(mValues.get(position).getImage())
                    .into(holder.imDish);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, InformationActivity.class);
                    context.startActivity(intent);
                    sendPosition = position;
                }
            });

        } else if (viewType == TYPE_ITEM2) {
            holder.tvDishName.setText(mValues.get(position).getName());
            holder.tvDishPrice.setText(mValues.get(position).getPrice());
            Picasso.with(MainActivity.getAppContext())
                    .load(mValues.get(position).getImage())
                    .into(holder.imDish);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, new InformationFragment())
                            .commit();
                    sendPosition = position;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (MainActivity.orientation == 1) {
            return TYPE_ITEM1;
        } else {
            return TYPE_ITEM2;
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imDish;
        TextView tvDishName;
        TextView tvDishPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imDish = itemView.findViewById(R.id.im_dish);
            tvDishName = itemView.findViewById(R.id.tv_dish_name);
            tvDishPrice = itemView.findViewById(R.id.tv_dish_price);
        }
    }
}
