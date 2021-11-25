package com.example.restaurant;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.firebase.database.FirebaseDatabase;

public class MainLifecycle implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void init() {
        DataFromDB.getDataFromDB();
    }
}
