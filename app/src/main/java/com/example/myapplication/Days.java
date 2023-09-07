package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Days extends AppCompatActivity {

    Button btn_previous, btn_next;
    SeekBar sb_days;
    TextView tv_days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        sb_days = findViewById(R.id.sb_days);
        tv_days = findViewById(R.id.tv_days);
        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);

        // button listeners
        btn_previous.setOnClickListener(view -> {
            // Get the selected number of days
            int enteredDays = sb_days.getProgress();

            // Retrieve user information from previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");
            String weight = getIntent().getStringExtra("weight");
            String selectedGoals = getIntent().getStringExtra("selectedGoals");

            // Prepare to navigate to the Confirmation activity
            Intent intent = new Intent(Days.this, Goal.class);

            // Pass user information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("selectedGoals", selectedGoals);
            intent.putExtra("days", String.valueOf(enteredDays)); // Include the selected days

            // Initiate the transition to the Confirmation activity
            startActivity(intent);
        });

        btn_next.setOnClickListener(view -> {
            // Get the selected number of days
            int enteredDays = sb_days.getProgress();

            // Retrieve user information from previous intent
            String name = getIntent().getStringExtra("name");
            String gender = getIntent().getStringExtra("gender");
            String age = getIntent().getStringExtra("age");
            String height = getIntent().getStringExtra("height");
            String weight = getIntent().getStringExtra("weight");
            String selectedGoals = getIntent().getStringExtra("selectedGoals");

            // Prepare to navigate to the Confirmation activity
            Intent intent = new Intent(Days.this, Confirmation.class);

            // Pass user information to the next activity
            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("age", age);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("selectedGoals", selectedGoals);
            intent.putExtra("days", String.valueOf(enteredDays)); // Include the selected days

            // Initiate the transition to the Confirmation activity
            startActivity(intent);
        });

        // change the output depends on the input
        sb_days.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i < 2)
                {
                    tv_days.setText(i + " Day");
                }
                else{
                    tv_days.setText(i + " Days");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}