package ua.temnokhud.practice2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import ua.temnokhud.basecomponets.BaseActivity;
import ua.temnokhud.basecomponets.util.listeners.SeekBarProgressChangedListener;
import ua.temnokhud.practice2.model.InputColor;

public class MainActivity extends BaseActivity {

    private static final String STATE_RED_COLOR = "STATE_RED_COLOR";
    private static final String STATE_BLUE_COLOR = "STATE_BLUE_COLOR";
    private static final String STATE_GREEN_COLOR = "STATE_GREEN_COLOR";

    private LinearLayoutCompat lltRoot;
    private View vPalette;
    private SeekBar skbRed;
    private SeekBar skbGreen;
    private SeekBar skbBlue;

    private InputColor inputColor;

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getActionBarTitleStringRes() {
        return R.string.text_practice_2;
    }

    @Override
    protected void onRestoreState(@NonNull Bundle savedInstanceState) {
        inputColor = new InputColor(
                savedInstanceState.getDouble(STATE_RED_COLOR, 0),
                savedInstanceState.getDouble(STATE_GREEN_COLOR, 0),
                savedInstanceState.getDouble(STATE_BLUE_COLOR, 0));
    }

    @Override
    protected void onSaveState(@NonNull Bundle outState) {
        outState.putDouble(STATE_RED_COLOR, inputColor.getRed());
        outState.putDouble(STATE_GREEN_COLOR, inputColor.getGreen());
        outState.putDouble(STATE_BLUE_COLOR, inputColor.getBlue());
    }

    @Override
    public void onCreateViews(Bundle savedInstanceState) {
        lltRoot = findViewById(R.id.activity_main_llt_root);
        vPalette = findViewById(R.id.activity_main_v_palette);
        skbRed = findViewById(R.id.activity_main_skb_red);
        skbGreen = findViewById(R.id.activity_main_skb_green);
        skbBlue = findViewById(R.id.activity_main_skb_blue);

        inputColor = new InputColor();
        setInputColor(inputColor);
    }

    @Override
    public void onCreateListeners() {
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

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        lltRoot.setOrientation(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
                ? LinearLayoutCompat.HORIZONTAL
                : LinearLayoutCompat.VERTICAL);
    }

}
