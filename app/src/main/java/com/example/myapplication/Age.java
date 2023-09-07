package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Age extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_age = findViewById(R.id.et_age);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            // Extract the entered age
            String enteredAge = et_age.getText().toString();

            // Retrieve the name and gender from the previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");

            // Prepare to proceed to the Height activity
            Intent intent = new Intent(Age.this, Gender.class);

            // Pass the name, gender, and age information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", enteredAge);

            // Initiate the transition to the next activity
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            // Extract the entered age
            String enteredAge = et_age.getText().toString();

            // Retrieve the name and gender from the previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");

            // Prepare to proceed to the Height activity
            Intent intent = new Intent(Age.this, Height.class);

            // Pass the name, gender, and age information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", enteredAge);

            // Initiate the transition to the next activity
            startActivity(intent);
        });
    }
}