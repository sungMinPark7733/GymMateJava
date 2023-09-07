package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Name extends AppCompatActivity {

    Button btn_cancel, btn_next;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_next = findViewById(R.id.btn_next);
        et_name = findViewById(R.id.et_name);

        // button listener
        btn_cancel.setOnClickListener(view -> {
            Intent intent = new Intent(Name.this, MainActivity.class); // Move from MainActivity class to Height class
            startActivity(intent);
        });

        // button listener
        btn_next.setOnClickListener(view -> {
            String name = et_name.getText().toString(); // Get the entered name
            Intent intent = new Intent(Name.this, Gender.class); // Move from MainActivity class to Height class
            intent.putExtra("name", name); // Pass the name to the next intent
            startActivity(intent);
        });

    }
}