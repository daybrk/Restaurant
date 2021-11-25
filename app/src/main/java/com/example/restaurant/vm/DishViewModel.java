package com.example.restaurant.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.restaurant.pojos.Dish;

import java.util.List;

public class DishViewModel extends AndroidViewModel {

    private final Repository repository;
    private final LiveData<List<Dish>> products;

    public DishViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        products = repository.getDish();

    }

    public LiveData<List<Dish>> getDish() {
        return products;
    }

    public void insertDish(final Dish dish) {
        repository.insertDish(dish);
    }

    public void deleteDish() {
        repository.deleteDish();
    }
}
