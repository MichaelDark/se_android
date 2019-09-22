package ua.temnokhud.app.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import ua.temnokhud.R;

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutRes());

        onCreateViews(savedInstanceState);
        onCreateListeners();

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getActionBarTitle());
        }
    }

    @LayoutRes
    abstract int getContentViewLayoutRes();

    abstract void onCreateViews(Bundle savedInstanceState);

    abstract void onCreateListeners();

    String getActionBarTitle() {
        return getString(getActionBarTitleStringRes());
    }

    @StringRes
    int getActionBarTitleStringRes() {
        return R.string.app_name;
    }

}
