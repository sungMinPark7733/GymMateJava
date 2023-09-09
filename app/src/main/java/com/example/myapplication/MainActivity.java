package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_continue = findViewById(R.id.btn_continue);

        // button listener
        btn_continue.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Name.class); // Move from MainActivity class to Name class
            startActivity(intent);
        });
    }
}