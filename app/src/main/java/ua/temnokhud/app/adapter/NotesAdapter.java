package ua.temnokhud.app.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.temnokhud.R;
import ua.temnokhud.app.model.Importance;
import ua.temnokhud.app.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notes;

    public NotesAdapter(@NonNull List<Note> notes) {
        this.notes = notes;
    }

    public void updateNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @NonNull
    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tile_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        private AppCompatImageView imvGallery;
        private AppCompatImageView imvPriority;
        private TextView txvTitle;
        private TextView txvDate;
        private TextView txvDescription;
        private GestureDetector gestureDetector;

        private Note note;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            imvGallery = itemView.findViewById(R.id.list_tile_note_imv_gallery);
            imvPriority = itemView.findViewById(R.id.list_tile_note_imv_priority);
            txvTitle = itemView.findViewById(R.id.list_tile_note_txv_title);
            txvDate = itemView.findViewById(R.id.list_tile_note_txv_date);
            txvDescription = itemView.findViewById(R.id.list_tile_note_txv_description);
        }

        private Context getContext() {
            return itemView.getContext();
        }

        @SuppressLint("ClickableViewAccessibility")
        private void bind(@NonNull Note note) {
//            imvGallery.setBackground();

            Importance importance = Importance.getById(note.getId());
            imvPriority.setBackgroundResource(importance.getIconDrawableRedId());

            txvTitle.setText(note.getTitle());
            txvDate.setText(note.getCreatedDateTime().toString());
            txvDescription.setText(note.getDescription());

            gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                public void onLongPress(MotionEvent e) {
                    onNoteLongPress(note);
                }
            });

            itemView.setOnTouchListener((view, event) -> gestureDetector.onTouchEvent(event));
        }

        private void onNoteLongPress(@NonNull Note note) {
            new AlertDialog.Builder(getContext())
                    .setNeutralButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
        }

    }

}
