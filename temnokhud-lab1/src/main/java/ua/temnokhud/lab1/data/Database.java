package ua.temnokhud.lab1.data;

import androidx.annotation.Nullable;

import java.util.List;

import ua.temnokhud.lab1.model.Importance;
import ua.temnokhud.lab1.model.Note;

public interface Database {

    void createNote(Note note);

    void updateNote(Note note);

    @Nullable
    Note getNote(long noteId);

    List<Note> getNotes();

    List<Note> getNotes(List<Importance> importances);

    void deleteNote(long noteId);

    class Provider {

        private Provider() {
        }

        public static Database getDatabase() {
            return SingletonDatabase.getInstance();
        }

    }

}

