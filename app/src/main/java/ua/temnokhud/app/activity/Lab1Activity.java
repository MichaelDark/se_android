package ua.temnokhud.app.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ua.temnokhud.R;
import ua.temnokhud.app.adapter.NotesAdapter;
import ua.temnokhud.app.model.Importance;
import ua.temnokhud.app.model.Note;
import ua.temnokhud.app.util.listeners.SearchViewQueryTextChangedListener;

public class Lab1Activity extends BaseActivity {

    private RecyclerView rcvNotes;
    private NotesAdapter rcvNotesAdapter;

    private List<Note> notes;

    @Override
    int getContentViewLayoutRes() {
        return R.layout.activity_lab_1;
    }

    @Override
    int getActionBarTitleStringRes() {
        return R.string.text_lab_1;
    }

    @Override
    void onCreateViews(Bundle savedInstanceState) {
        rcvNotes = findViewById(R.id.activity_lab_1_rcv_notes);

//        notes = new ArrayList<>();
        notes = Arrays.asList(
                new Note(1, Importance.MINOR.getId(), new Date(), null, "Title 1", "Description 1"),
                new Note(2, Importance.MAJOR.getId(), new Date(), null, "Title 2", "Description 2"),
                new Note(3, Importance.CRITICAL.getId(), new Date(), null, "Title 3", "Description 3"),
                new Note(4, Importance.MINOR.getId(), new Date(), null, "Title 4", "Description 4")
        );

        rcvNotesAdapter = new NotesAdapter(notes);
        rcvNotes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcvNotes.setAdapter(rcvNotesAdapter);
    }

    @Override
    void onCreateListeners() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_lab_1_action_bar_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search_notes).getActionView();
            searchView.setOnSearchClickListener(v -> onSearchTextChanged(searchView.getQuery().toString()));
            searchView.setOnQueryTextListener((SearchViewQueryTextChangedListener) this::onSearchTextChanged);
            searchView.setIconifiedByDefault(true);
        }

        return true;
    }

    private void onSearchTextChanged(String queryText) {
        Log.d("Lab1Activity", queryText);
//        rcvNotesAdapter.updateNotes(newNotes);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_notes:
                Toast.makeText(this, "Filter", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_add_note:
                Toast.makeText(this, "Add note", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
