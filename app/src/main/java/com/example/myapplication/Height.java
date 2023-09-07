package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Height extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_height = findViewById(R.id.et_height);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            // Extract the entered height
            String enteredHeight = et_height.getText().toString();

            // Retrieve the name, gender, and age from the previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");

            // Prepare to proceed to the Weight activity
            Intent intent = new Intent(Height.this, Age.class);

            // Pass the name, gender, age, and height information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", enteredHeight);

            // Initiate the transition to the next activity
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            // Extract the entered height
            String enteredHeight = et_height.getText().toString();

            // Retrieve the name, gender, and age from the previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");

            // Prepare to proceed to the Weight activity
            Intent intent = new Intent(Height.this, Weight.class);

            // Pass the name, gender, age, and height information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", enteredHeight);

            // Initiate the transition to the next activity
            startActivity(intent);
        });
    }
}