package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gender extends AppCompatActivity {

    TextView tv_gender;
    Button btn_male, btn_female, btn_previous, btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        tv_gender = findViewById(R.id.tv_gender);
        btn_male = findViewById(R.id.btn_male);
        btn_female = findViewById(R.id.btn_female);
        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);

        // button listeners
        btn_male.setOnClickListener(view ->{
            tv_gender.setText("Male");
        });

        btn_female.setOnClickListener(view ->{
            tv_gender.setText("Female");
        });

        btn_previous.setOnClickListener(view -> {
            // Get the entered gender from the TextView
            String enteredGender = tv_gender.getText().toString();

            // Get the name from the previous intent
            String name = getIntent().getStringExtra("name");

            // Prepare to navigate to the Age activity
            Intent intent = new Intent(Gender.this, MainActivity.class);

            // Pass the name and gender information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", enteredGender);

            // Initiate the activity transition
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            // Get the entered gender from the TextView
            String enteredGender = tv_gender.getText().toString();

            // Get the name from the previous intent
            String name = getIntent().getStringExtra("name");

            // Prepare to navigate to the Age activity
            Intent intent = new Intent(Gender.this, Age.class);

            // Pass the name and gender information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", enteredGender);

            // Initiate the activity transition
            startActivity(intent);
        });
    }
}