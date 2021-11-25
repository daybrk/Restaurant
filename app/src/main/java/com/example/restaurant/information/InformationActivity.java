package com.example.restaurant.information;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurant.DishRV;
import com.example.restaurant.MainActivity;
import com.example.restaurant.R;
import com.squareup.picasso.Picasso;

public class InformationActivity extends AppCompatActivity {

    ImageView imDish;
    TextView tvDishName;
    TextView tvDishPrice;
    TextView tvDishDesc;
    TextView tvAddToBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        imDish = findViewById(R.id.im_dish_inf);
        tvDishName = findViewById(R.id.tv_dish_name_inf);
        tvDishPrice = findViewById(R.id.tv_dish_price_inf);
        tvDishDesc = findViewById(R.id.tv_dish_desc);
        tvAddToBasket = findViewById(R.id.tv_add_to_basket);

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
    }
}