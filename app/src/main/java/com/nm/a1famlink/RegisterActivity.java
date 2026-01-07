package com.nm.a1famlink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etConfirmPassword;
    Button btnContinue;
    TextView tvTitle;

    String flow; // create or join

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnContinue = findViewById(R.id.btnContinue);
        tvTitle = findViewById(R.id.tvTitle);

        // Get flow
        flow = getIntent().getStringExtra("flow");

        if ("join".equals(flow)) {
            tvTitle.setText("Join your family");
        }

        btnContinue.setOnClickListener(v -> {
            if (validateInputs()) {
                proceedNext();
            }
        });
    }

    private boolean validateInputs() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (name.isEmpty()) {
            etName.setError("Enter your name");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter valid email");
            return false;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            return false;
        }

        return true;
    }

    private void proceedNext() {
        Intent intent;

        if ("create".equals(flow)) {
            intent = new Intent(this, CreateFamilyActivity.class);
        } else {
            intent = new Intent(this, JoinFamilyActivity.class);
        }

        startActivity(intent);
    }
}
