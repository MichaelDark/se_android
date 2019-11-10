package ua.temnokhud.lab1.data;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ua.temnokhud.lab1.model.Importance;
import ua.temnokhud.lab1.model.Note;

public class SingletonDatabase implements Database {

    private static SingletonDatabase instance = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return instance;
    }

    private List<Note> notes = new ArrayList<>();

    private SingletonDatabase() {
        notes = new ArrayList<>(Arrays.asList(
                new Note(1, Importance.MINOR.getId(), new Date(), null, "Title 1", "Description 1"),
                new Note(2, Importance.MAJOR.getId(), new Date(), null, "Title 2", "Description 2"),
                new Note(3, Importance.CRITICAL.getId(), new Date(), null, "Title 3", "Description 3"),
                new Note(4, Importance.MINOR.getId(), new Date(), null, "Title 4", "Description 4")
        ));
    }

    @Override
    public void createNote(Note note) {
        long lastId = 1;
        for (Note existingNote : notes) {
            if (existingNote.getId() == note.getId()) {
                return;
            } else if (existingNote.getId() > lastId) {
                lastId = existingNote.getId() + 1;
            }
        }
        note.setId(lastId);
        notes.add(note);
    }

    @Override
    public void updateNote(Note note) {
        deleteNote(note.getId());
        notes.add(note);
    }

    @Nullable
    @Override
    public Note getNote(long noteId) {
        for (Note existingNote : notes) {
            if (existingNote.getId() == noteId) {
                return existingNote;
            }
        }
        return null;
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public List<Note> getNotes(List<Importance> importances) {
        if (importances != null) {
            List<Note> filteredNotes = new ArrayList<>();
            for (Note note : notes) {
                for (Importance importance : importances) {
                    if (note.getImportance() == importance.getId()) {
                        filteredNotes.add(note);
                    }
                }
            }
            return filteredNotes;
        } else {
            return getNotes();
        }
    }

    @Override
    public void deleteNote(long noteId) {
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.getId() == noteId) {
                notes.remove(i);
                return;
            }
        }
    }

}
