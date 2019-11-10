package ua.temnokhud.practice1;

import android.os.Bundle;
import android.widget.CheckBox;

import androidx.annotation.NonNull;

import ua.temnokhud.basecomponets.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String STATE_CHECKBOX_CHECKED = "STATE_CHECKBOX_CHECKED";

    private CheckBox cbxFirst;

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getActionBarTitleStringRes() {
        return R.string.text_practice_1;
    }

    @Override
    protected void onRestoreState(@NonNull Bundle savedInstanceState) {
        cbxFirst.setChecked(savedInstanceState.getBoolean(STATE_CHECKBOX_CHECKED, false));
    }

    @Override
    protected void onSaveState(@NonNull Bundle outState) {
        outState.putBoolean(STATE_CHECKBOX_CHECKED, cbxFirst.isChecked());
    }

    @Override
    public void onCreateViews(Bundle savedInstanceState) {
        cbxFirst = findViewById(R.id.activity_main_checkbox);
    }

    @Override
    public void onCreateListeners() {
    }

}
