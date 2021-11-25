package com.example.restaurant.vm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.restaurant.pojos.Dish;
import com.example.restaurant.db.DishDAO;
import com.example.restaurant.db.DishDatabase;

import java.util.List;

public class Repository {

    private final LiveData<List<Dish>> products;
    private final DishDAO dao;

    public Repository(Application application) {
        dao = DishDatabase.getDatabase(application.getApplicationContext()).dishDAO();
        products = dao.getAllDish();
    }

    public LiveData<List<Dish>> getDish() {
        return products;
    }

    void insertDish(final Dish products) {
        DishDatabase.dbWriteExecutor.execute(() -> dao.insertDish(products));
    }

    void deleteDish() {
        dao.deleteDish();
    }
}
