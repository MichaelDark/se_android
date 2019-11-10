package ua.temnokhud.lab1.utils.listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

    private RecyclerView recyclerView;

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        int position = recyclerView.getChildLayoutPosition(view);

        // handle single tap

        return super.onSingleTapConfirmed(e);
    }

    public void onLongPress(MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        int position = recyclerView.getChildLayoutPosition(view);

        // handle long press

        super.onLongPress(e);
    }
}