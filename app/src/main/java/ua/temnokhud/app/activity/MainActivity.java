package ua.temnokhud.app.activity;

import android.os.Bundle;
import android.widget.Button;

import ua.temnokhud.R;
import ua.temnokhud.app.util.ActivityUtils;

public class MainActivity extends BaseActivity {

    private Button btnPractice1;

    @Override
    int getContentViewLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    void onCreateViews(Bundle savedInstanceState) {
        btnPractice1 = findViewById(R.id.activity_main_btn_practice_1);
    }

    @Override
    void onCreateListeners() {
        btnPractice1.setOnClickListener(v -> ActivityUtils.startActivity(this, Practice1Activity.class));
    }

}

