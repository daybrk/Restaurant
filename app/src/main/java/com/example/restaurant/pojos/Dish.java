package com.example.restaurant.pojos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Dish")
public class Dish {

    @PrimaryKey(autoGenerate = true)
    int id;

    String image;

    String name;

    String price;

    String description;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
