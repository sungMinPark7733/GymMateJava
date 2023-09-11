package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_continue, btn_newaccount;
    EditText et_email;
    CheckBox checkBox;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_continue = findViewById(R.id.btn_continue);
        btn_newaccount = findViewById(R.id.btn_newaccount);
        et_email = findViewById(R.id.et_email);
        checkBox = findViewById(R.id.checkBox);

        preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Check if the user has previously checked the CheckBox
        boolean rememberLogin = preferences.getBoolean("remember_login", false);
        if (rememberLogin) {
            String savedEmail = preferences.getString("user_email", "");
            if (!savedEmail.isEmpty()) {
                et_email.setText(savedEmail);
                checkBox.setChecked(true);
            }
        }

        // Button listeners
        btn_continue.setOnClickListener(view -> {
            // Get the entered email from the EditText
            String enteredEmail = et_email.getText().toString().trim();

            // Check if the email exists in the database
            UserModel user = getUserByEmail(enteredEmail);

            if (user != null) {
                // If a matching user is found, remember the login if CheckBox is checked
                if (checkBox.isChecked()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("remember_login", true);
                    editor.putString("user_email", enteredEmail);
                    editor.apply();
                }

                // Pass the user data to the Calories activity
                Intent intent = new Intent(MainActivity.this, Calories.class);
                intent.putExtra("user", user);
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
