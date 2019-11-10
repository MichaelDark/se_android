package ua.temnokhud.basecomponets;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
    protected abstract int getContentViewLayoutRes();

    protected abstract void onCreateViews(Bundle savedInstanceState);

    protected abstract void onCreateListeners();

    protected String getActionBarTitle() {
        return getString(getActionBarTitleStringRes());
    }

    @StringRes
    protected int getActionBarTitleStringRes() {
        return R.string.app_name;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onRestoreState(savedInstanceState);
    }

    protected abstract void onRestoreState(@NonNull Bundle savedInstanceState);

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        onSaveState(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    protected abstract void onSaveState(@NonNull Bundle outState);

}
