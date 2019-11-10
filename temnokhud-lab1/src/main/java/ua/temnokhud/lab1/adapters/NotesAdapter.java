package ua.temnokhud.lab1.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ua.temnokhud.lab1.EditNoteActivity;
import ua.temnokhud.lab1.R;
import ua.temnokhud.lab1.model.Importance;
import ua.temnokhud.lab1.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>
        implements Filterable {

    private List<Note> notes;
    private List<Note> notesFiltered;

    public NotesAdapter(@NonNull List<Note> notes) {
        this.notes = notes;
        this.notesFiltered = new ArrayList<>(notes);
    }

    public void updateNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notesFiltered.size();
    }

    @NonNull
    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tile_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {
        holder.bind(notesFiltered.get(position));
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Note> filteredList;

                if (constraint != null && constraint.length() != 0) {
                    filteredList = new ArrayList<>();
                    for (Note note : notes) {
                        if (note.getTitle().contains(constraint)) {
                            filteredList.add(note);
                        } else if (note.getDescription().contains(constraint)) {
                            filteredList.add(note);
                        }
                    }
                } else {
                    filteredList = new ArrayList<>(notes);
                }

                FilterResults results = new FilterResults();
                results.count = filteredList.size();
                results.values = filteredList;
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notesFiltered = (List<Note>) results.values;
                notifyDataSetChanged();
            }

        };
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        private AppCompatImageView imvGallery;
        private AppCompatImageView imvPriority;
        private TextView txvTitle;
        private TextView txvDate;
        private TextView txvDescription;

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

        @SuppressLint("ClickableViewAccessibility")
        private void bind(@NonNull Note note) {
//            imvGallery.setBackground();

            Importance importance = Importance.getById(note.getId());
            imvPriority.setBackgroundResource(importance.getIconDrawableRedId());

            txvTitle.setText(note.getTitle());
            txvDate.setText(note.getCreatedDateTime().toString());
            txvDescription.setText(note.getDescription());


            itemView.setOnClickListener(v -> onNoteClick(note));
        }

        private void onNoteClick(@NonNull Note note) {
            Activity activity = (Activity) itemView.getContext();
            EditNoteActivity.openEditActivity(activity, note);

//            new AlertDialog.Builder(getContext())
//                    .setNeutralButton("OK", (dialog, which) -> dialog.dismiss())
//                    .show();
        }

    }

}
