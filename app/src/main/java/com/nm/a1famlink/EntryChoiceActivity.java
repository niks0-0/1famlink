package com.nm.a1famlink;
// change if needed

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nm.a1famlink.R;

public class EntryChoiceActivity extends AppCompatActivity {

    CardView cardCreateFamily, cardJoinFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_choice);

        // Initialize views
        cardCreateFamily = findViewById(R.id.cardCreateFamily);
        cardJoinFamily = findViewById(R.id.cardJoinFamily);

        // Create Family click
        cardCreateFamily.setOnClickListener(v -> {
            Intent intent = new Intent(
                    EntryChoiceActivity.this,
                    RegisterActivity.class
            );
            intent.putExtra("flow", "create");
            startActivity(intent);
        });

        // Join Family click
        cardJoinFamily.setOnClickListener(v -> {
            Intent intent = new Intent(
                    EntryChoiceActivity.this,
                    JoinFamilyActivity.class
            );
            startActivity(intent);
        });
    }
}
