package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Weight extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        btn_previous = findViewById(R.id. btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_weight = findViewById(R.id.et_weight);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            // Extract the entered weight
            String enteredWeight = et_weight.getText().toString();

            // Retrieve the name, gender, age, and height from the previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");

            // Prepare to navigate to the Goal activity
            Intent intent = new Intent(Weight.this, Height.class);

            // Pass the name, gender, age, height, and weight information to the next intent
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", enteredWeight);

            // Initiate the transition to the next activity
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            // Extract the entered weight
            String enteredWeight = et_weight.getText().toString();

            // Retrieve the name, gender, age, and height from the previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");

            // Prepare to navigate to the Goal activity
            Intent intent = new Intent(Weight.this, Goal.class);

            // Pass the name, gender, age, height, and weight information to the next intent
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", enteredWeight);

            // Initiate the transition to the next activity
            startActivity(intent);
        });
    }
}