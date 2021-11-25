package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.restaurant.pojos.Dish;
import com.example.restaurant.vm.DishViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button makeOrder;

    public static FragmentManager fragmentManager;

    FirebaseDatabase mDatabase;

    @SuppressLint("StaticFieldLeak")
    static Context context;

    private static DishViewModel viewModel;
    private static DatabaseReference ref;
    public static int orientation = 0;

    public static List<Dish> list;
    public static List<Integer> id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);

            orientation = 1;

            recycler = findViewById(R.id.recycler_land);
            recycler.setLayoutManager(new GridLayoutManager(this, 2));

        } else {
            setContentView(R.layout.activity_main);

            orientation = 2;

            recycler = findViewById(R.id.recycler_land);
            recycler.setLayoutManager(new LinearLayoutManager(this));

            fragmentManager = getSupportFragmentManager();
        }

        MainLifecycle mainLifecycle = new MainLifecycle();
        Lifecycle lifecycle = getLifecycle();
        lifecycle.addObserver(mainLifecycle);

        id = new ArrayList<>();

        makeOrder = findViewById(R.id.btn_make_order);
        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MakeOrder.class);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance();
        ref = mDatabase.getReference("dish");
        context = getApplicationContext();

        viewModel = new ViewModelProvider(this).get(DishViewModel.class);
        viewModel.getDish().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                if (DataFromDB.count == dishes.size()) {
                    recycler.setAdapter(new DishRV(dishes));
                    list = dishes;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ConstraintLayout constraintLayout = findViewById(R.id.constraint);
        switch(id){
            case R.id.white:
                constraintLayout.setBackgroundColor(Color.WHITE);
                return true;
            case R.id.black:
                constraintLayout.setBackgroundColor(Color.GRAY);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static DatabaseReference getRef() {
        return ref;
    }

    public static DishViewModel getViewModel() {
        return viewModel;
    }

    public static Context getAppContext() {
        return context;
    }

}