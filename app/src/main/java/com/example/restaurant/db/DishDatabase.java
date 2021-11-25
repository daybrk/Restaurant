package com.example.restaurant.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.restaurant.pojos.Dish;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Dish.class, version = 1, exportSchema = false)
abstract public class DishDatabase extends RoomDatabase {

    public abstract DishDAO dishDAO();

    private static volatile DishDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DishDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DishDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DishDatabase.class, "database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
