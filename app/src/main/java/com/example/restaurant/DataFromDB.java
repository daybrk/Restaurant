package com.example.restaurant;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.restaurant.pojos.Dish;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DataFromDB {

    public static int count = 0;

    public static void getDataFromDB() {

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                MainActivity.getViewModel().deleteDish();

                count = 0;

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Dish dish = new Dish();
                    dish.setName(String.valueOf(ds.child("name").getValue()));
                    dish.setPrice(String.valueOf(ds.child("price").getValue()));
                    dish.setImage(String.valueOf(ds.child("image").getValue()));

                    MainActivity.getViewModel().insertDish(dish);
                    count++;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        MainActivity.getRef().addValueEventListener(vListener);
    }

}
