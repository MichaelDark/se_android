package ua.temnokhud.app.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ua.temnokhud.R;
import ua.temnokhud.app.model.InputColor;
import ua.temnokhud.app.util.listeners.SeekBarProgressChangedListener;

public class Practice1Activity extends AppCompatActivity {

    private LinearLayout lltRoot;
    private View vPalette;
    private SeekBar skbRed;
    private SeekBar skbGreen;
    private SeekBar skbBlue;

    private InputColor inputColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_1);
        onCreateViews(savedInstanceState);
        onCreateListeners();
    }

    private void onCreateViews(Bundle savedInstanceState) {
        lltRoot = findViewById(R.id.activity_practice_1_llt_root);
        vPalette = findViewById(R.id.activity_practice_1_v_palette);
        skbRed = findViewById(R.id.activity_practice_1_skb_red);
        skbGreen = findViewById(R.id.activity_practice_1_skb_green);
        skbBlue = findViewById(R.id.activity_practice_1_skb_blue);

        inputColor = new InputColor();
        setInputColor(inputColor);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        lltRoot.setOrientation(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
                ? LinearLayout.HORIZONTAL
                : LinearLayout.VERTICAL);
    }

    private void onCreateListeners() {
        skbRed.setOnSeekBarChangeListener((SeekBarProgressChangedListener)
                (seekBar, i, b) -> setInputColor(inputColor.withRed(255 * i / 100)));
        skbGreen.setOnSeekBarChangeListener((SeekBarProgressChangedListener)
                (seekBar, i, b) -> setInputColor(inputColor.withGreen(255 * i / 100)));
        skbBlue.setOnSeekBarChangeListener((SeekBarProgressChangedListener)
                (seekBar, i, b) -> setInputColor(inputColor.withBlue(255 * i / 100)));
    }

    private void setInputColor(InputColor inputColor) {
        this.inputColor = inputColor;
        Log.d("InputColor", inputColor.toString());
        vPalette.setBackgroundColor(inputColor.getIntColor());
    }


}
