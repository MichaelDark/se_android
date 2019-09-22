package ua.temnokhud.app.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ua.temnokhud.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main_btn_practice_1).setOnClickListener(v -> {
            Intent activityIntent = new Intent(this, Practice1Activity.class);
            startActivity(activityIntent);
        });
    }

}

