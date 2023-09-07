package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class Goal extends AppCompatActivity {
    Button btn_previous, btn_next;
    CheckBox cb_muscle, cb_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        cb_muscle = findViewById(R.id.cb_muscle);
        cb_weight = findViewById(R.id.cb_weight);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            // Prepare to capture selected goals
            String selectedGoals = "";

            // Check if the "Muscle Building" checkbox is selected
            if (cb_muscle.isChecked()) {
                selectedGoals += cb_muscle.getText().toString();
            }

            // Check if the "Weight Loss" checkbox is selected
            if (cb_weight.isChecked()) {
                // Add a comma if "Muscle Building" is also selected
                if (!selectedGoals.isEmpty()) {
                    selectedGoals += ", ";
                }
                selectedGoals += cb_weight.getText().toString();
            }

            // Retrieve data from previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");
            String weight = getIntent().getStringExtra("weight");

            // Prepare to navigate to the Days activity
            Intent intent = new Intent(Goal.this, Weight.class);

            // Pass collected data to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("selectedGoals", selectedGoals);

            // Initiate the transition to the next activity
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            // Prepare to capture selected goals
            String selectedGoals = "";

            // Check if the "Muscle Building" checkbox is selected
            if (cb_muscle.isChecked()) {
                selectedGoals += cb_muscle.getText().toString();
            }

            // Check if the "Weight Loss" checkbox is selected
            if (cb_weight.isChecked()) {
                // Add a comma if "Muscle Building" is also selected
                if (!selectedGoals.isEmpty()) {
                    selectedGoals += ", ";
                }
                selectedGoals += cb_weight.getText().toString();
            }

            // Retrieve data from previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");
            String weight = getIntent().getStringExtra("weight");

            // Prepare to navigate to the Days activity
            Intent intent = new Intent(Goal.this, Days.class);

            // Pass collected data to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("selectedGoals", selectedGoals);

            // Initiate the transition to the next activity
            startActivity(intent);
        });
    }
}