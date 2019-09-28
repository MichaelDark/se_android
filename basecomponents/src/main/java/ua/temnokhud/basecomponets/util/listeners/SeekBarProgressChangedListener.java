package ua.temnokhud.basecomponets.util.listeners;

import android.widget.SeekBar;

public interface SeekBarProgressChangedListener extends SeekBar.OnSeekBarChangeListener {

    default void onStartTrackingTouch(SeekBar var1) {
    }

    default void onStopTrackingTouch(SeekBar var1) {
    }

}
