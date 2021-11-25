package com.example.restaurant.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.restaurant.pojos.Dish;

import java.util.List;

@Dao
public interface DishDAO {

    @Query("SELECT * FROM Dish")
    LiveData<List<Dish>> getAllDish();

    @Query("DELETE FROM Dish")
    void deleteDish();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDish(Dish dish);
}
