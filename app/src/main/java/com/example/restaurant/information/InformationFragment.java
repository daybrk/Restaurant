package com.example.restaurant.information;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurant.DishRV;
import com.example.restaurant.MainActivity;
import com.example.restaurant.R;
import com.example.restaurant.pojos.Dish;
import com.squareup.picasso.Picasso;

public class InformationFragment extends Fragment {

    ImageView imDish;
    TextView tvDishName;
    TextView tvDishPrice;
    TextView tvDishDesc;
    TextView tvAddToBasket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_information, container, false);

        imDish = view.findViewById(R.id.im_dish_inf);
        tvDishName = view.findViewById(R.id.tv_dish_name_inf);
        tvDishPrice = view.findViewById(R.id.tv_dish_price_inf);
        tvDishDesc = view.findViewById(R.id.tv_dish_desc);
        tvAddToBasket = view.findViewById(R.id.tv_add_to_basket);

        int position = DishRV.sendPosition;

        Picasso.with(MainActivity.getAppContext())
                .load(MainActivity.list.get(position).getImage())
                .into(imDish);
        tvDishName.setText(MainActivity.list.get(position).getName());
        tvDishPrice.setText(MainActivity.list.get(position).getPrice());
        tvDishDesc.setText("Очень вкусная еда (или нет)");

        tvAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.id.add(position);
            }
        });

        return view;
    }
}