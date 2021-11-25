package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.restaurant.pojos.Dish;
import com.example.restaurant.pojos.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MakeOrder extends AppCompatActivity {

    EditText edFIO;
    EditText edPhone;
    EditText edCount;
    EditText edTime;

    TextView tvAmount;

    Button confirmButton;

    Spinner spinner;
    CheckBox checkBox;

    ListView listView;

    String[] data = {"Столик 1", "Столик 2", "Столик 3", "Столик 4", "Столик 5"};
    String table;
    boolean checked;
    List<String> dataForList;

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference refOrder;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);


        edFIO = findViewById(R.id.ed_fio);
        edPhone = findViewById(R.id.ed_phone);
        edCount = findViewById(R.id.ed_count);
        edTime = findViewById(R.id.ed_time);

        tvAmount = findViewById(R.id.tv_amount);

        listView = findViewById(R.id.listView);

        confirmButton = findViewById(R.id.btn_confirm);
        confirmButton.setOnClickListener(v -> {
            String fio = String.valueOf(edFIO.getText());
            String phone = String.valueOf(edPhone.getText());
            String count = String.valueOf(edCount.getText());
            String time = String.valueOf(edTime.getText());
            String culture;
            String amount = String.valueOf(tvAmount.getText());

            if (checked) {
                 culture = "Да";
            } else {
                 culture = "Нет";
            }

            Order order = new Order(fio, phone, count, time, table, culture, amount, dataForList);
            refOrder.push().setValue(order);
        });


        checkBox = findViewById(R.id.cb_culture);
        checked = checkBox.isChecked();

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setSelection(2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                table = data[position];

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        refOrder = mDatabase.getReference("orders");

        dataForList = new ArrayList<>();

        int amount = 0;

        for (int i = 0; i < MainActivity.id.size(); i++) {
            dataForList.add(MainActivity.list.get(MainActivity.id.get(i)).getName());
            amount = amount + Integer.parseInt(MainActivity.list.get(MainActivity.id.get(i)).getPrice());
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dataForList);

        listView.setAdapter(listAdapter);

        tvAmount.setText("Счёт: " + amount + " руб.");
    }
}