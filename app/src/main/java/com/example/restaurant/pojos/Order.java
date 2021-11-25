package com.example.restaurant.pojos;

import java.util.List;

public class Order {

    String fio;

    String phone;

    String count;

    String time;

    String table;

    String culture;

    String amount;

    List<String> dishes;

    public Order(String fio, String phone, String count, String time, String table, String culture, String amount, List<String> dishes) {
        this.fio = fio;
        this.phone = phone;
        this.count = count;
        this.time = time;
        this.table = table;
        this.culture = culture;
        this.amount = amount;
        this.dishes = dishes;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public void setDishes(List<String> dishes) {
        this.dishes = dishes;
    }
}
