package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_continue, btn_newaccount;
    EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_continue = findViewById(R.id.btn_continue);
        btn_newaccount = findViewById(R.id.btn_newaccount);
        et_email = findViewById(R.id.et_email);

        // button listener
        btn_continue.setOnClickListener(view -> {
            // Get the entered email from the EditText
            String enteredEmail = et_email.getText().toString().trim();

            // Check if the email exists in the database
            UserModel user = getUserByEmail(enteredEmail);

            if (user != null) {
                // If a matching user is found, pass the user data to the Calories activity
                Intent intent = new Intent(MainActivity.this, Calories.class);
                intent.putExtra("user", user); // Pass the user object
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
            }
        });


        btn_newaccount.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Email.class); // Move from MainActivity class to Name class
            startActivity(intent);
        });
    }

    private UserModel getUserByEmail(String email) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.getUserByEmail(email);

    }
}