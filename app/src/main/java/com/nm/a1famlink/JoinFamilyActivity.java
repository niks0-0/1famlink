package com.nm.a1famlink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinFamilyActivity extends AppCompatActivity {

    EditText etFamilyCode;
    Button btnJoinFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_family);

        etFamilyCode = findViewById(R.id.etFamilyCode);
        btnJoinFamily = findViewById(R.id.btnJoinFamily);

        btnJoinFamily.setOnClickListener(v -> {
            String familyCode = etFamilyCode.getText().toString().trim();

            if (familyCode.isEmpty()) {
                etFamilyCode.setError("Enter family code");
                return;
            }

            // TEMP: assume code is valid (backend later)
            Toast.makeText(
                    this,
                    "Joined family successfully",
                    Toast.LENGTH_SHORT
            ).show();

            // TODO later: verify family code with backend

            Intent intent = new Intent(this, EntryChoiceActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
