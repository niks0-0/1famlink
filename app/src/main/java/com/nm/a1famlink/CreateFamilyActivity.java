package com.nm.a1famlink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class CreateFamilyActivity extends AppCompatActivity {

    EditText etFamilyName;
    Button btnCreateFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_family);

        etFamilyName = findViewById(R.id.etFamilyName);
        btnCreateFamily = findViewById(R.id.btnCreateFamily);

        btnCreateFamily.setOnClickListener(v -> {
            String familyName = etFamilyName.getText().toString().trim();

            if (familyName.isEmpty()) {
                etFamilyName.setError("Enter family name");
                return;
            }

            // TEMP: generate family code (backend later)
            String familyCode = generateFamilyCode();

            Toast.makeText(
                    this,
                    "Family created! Code: " + familyCode,
                    Toast.LENGTH_LONG
            ).show();

            // TODO later: save to backend

            // Go to dashboard
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private String generateFamilyCode() {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder("FAM-");

        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString().toUpperCase(Locale.US);
    }
}
