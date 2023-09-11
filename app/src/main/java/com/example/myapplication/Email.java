package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Email extends AppCompatActivity {
    Button btn_previous, btn_next;
    EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        et_email = findViewById(R.id.et_email);

        // button listener
        btn_previous.setOnClickListener(view -> {
            Intent intent = new Intent(Email.this, MainActivity.class); // Move from MainActivity class to Height class
            startActivity(intent);
        });

        // button listener
        btn_next.setOnClickListener(view -> {
            String email = et_email.getText().toString(); // Get the entered name
            Intent intent = new Intent(Email.this, Name.class); // Move from MainActivity class to Height class
            intent.putExtra("email", email); // Pass the name to the next intent
            startActivity(intent);
        });

    }
}
