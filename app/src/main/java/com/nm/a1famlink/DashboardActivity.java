package com.nm.a1famlink;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;
import androidx.cardview.widget.CardView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    TextView tvFamilyName;
    ImageView imgLock;
    RecyclerView rvMembers;
    Button btnLocation, btnMembers, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Views
        tvFamilyName = findViewById(R.id.tvFamilyName);
        imgLock = findViewById(R.id.imgLock);
        rvMembers = findViewById(R.id.rvMembers);
        btnLocation = findViewById(R.id.btnLocation);
        btnMembers = findViewById(R.id.btnMembers);
        btnSettings = findViewById(R.id.btnSettings);
        CardView cardSOS = findViewById(R.id.cardSOS);

        final Handler handler = new Handler(Looper.getMainLooper());
        final boolean[] isLongPressed = {false};

        Runnable sosRunnable = () -> {
            isLongPressed[0] = true;
            triggerSOS();
        };

        cardSOS.setOnLongClickListener(v -> {
            handler.postDelayed(sosRunnable, 1500); // 1.5 sec
            return true;
        });

        cardSOS.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case android.view.MotionEvent.ACTION_UP:
                case android.view.MotionEvent.ACTION_CANCEL:
                    handler.removeCallbacks(sosRunnable);
                    if (!isLongPressed[0]) {
                        Toast.makeText(this, "Hold SOS to send alert", Toast.LENGTH_SHORT).show();
                    }
                    isLongPressed[0] = false;
                    break;
            }
            return false;
        });

        

        // TEMP: Set family name (later from backend)
        tvFamilyName.setText("My Family");

        // RecyclerView setup
        rvMembers.setLayoutManager(new LinearLayoutManager(this));
        rvMembers.setAdapter(new MembersAdapter(getDummyMembers()));

        // Button actions (temporary)
        btnLocation.setOnClickListener(v ->
                Toast.makeText(this, "Location feature coming soon", Toast.LENGTH_SHORT).show()
        );

        btnMembers.setOnClickListener(v ->
                Toast.makeText(this, "Members list", Toast.LENGTH_SHORT).show()
        );

        btnSettings.setOnClickListener(v ->
                Toast.makeText(this, "Settings screen", Toast.LENGTH_SHORT).show()
        );
    }

    private void triggerSOS() {
        // TEMP: local confirmation
        Toast.makeText(this, "🚨 SOS Sent to your family", Toast.LENGTH_LONG).show();

        // TODO (next):
        // 1. Send SOS to backend (PHP API)
        // 2. Save timestamp + user_id
        // 3. Push notification to family
    }


    // Dummy data for now
    private List<String> getDummyMembers() {
        List<String> members = new ArrayList<>();
        members.add("Dad");
        members.add("Mom");
        members.add("Me");
        members.add("Sister");
        return members;
    }
}
