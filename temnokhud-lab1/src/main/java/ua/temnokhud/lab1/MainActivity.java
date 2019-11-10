package ua.temnokhud.lab1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.var;
import ua.temnokhud.basecomponets.BaseActivity;
import ua.temnokhud.lab1.adapters.NotesAdapter;
import ua.temnokhud.lab1.data.Database;
import ua.temnokhud.lab1.model.Importance;
import ua.temnokhud.lab1.utils.listeners.SearchViewQueryTextChangedListener;

public class MainActivity extends BaseActivity {


    private RecyclerView rcvNotes;
    private NotesAdapter rcvNotesAdapter;
    private Menu menu;

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getActionBarTitleStringRes() {
        return R.string.app_name;
    }

    @Override
    protected void onRestoreState(@NonNull Bundle savedInstanceState) {
    }

    @Override
    protected void onSaveState(@NonNull Bundle outState) {
    }

    @Override
    protected void onCreateViews(Bundle savedInstanceState) {
        rcvNotes = findViewById(R.id.activity_lab_1_rcv_notes);

        var notes = Database.Provider.getDatabase().getNotes();
        rcvNotesAdapter = new NotesAdapter(notes);
        rcvNotes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcvNotes.setAdapter(rcvNotesAdapter);
    }

    @Override
    protected void onCreateListeners() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search_notes).getActionView();
            searchView.setOnSearchClickListener(v -> onSearchTextChanged(searchView.getQuery().toString()));
            searchView.setOnQueryTextListener((SearchViewQueryTextChangedListener) this::onSearchTextChanged);
            searchView.setIconifiedByDefault(true);
        }

        this.menu = menu;

        return true;
    }

    private void onSearchTextChanged(String queryText) {
        Log.d("Lab1Activity", queryText);
        rcvNotesAdapter.getFilter().filter(queryText);
    }

    private List<Importance> getFilterImportances() {
        List<Importance> importances = new ArrayList<>();
        if (menu != null) {
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.getItem(i);

                if (item.isChecked()) {
                    int itemId = item.getItemId();
                    switch (itemId) {
                        case R.id.action_filter_minor:
                            importances.add(Importance.MINOR);
                            break;
                        case R.id.action_filter_major:
                            importances.add(Importance.MAJOR);
                            break;
                        case R.id.action_filter_critical:
                            importances.add(Importance.CRITICAL);
                            break;
                    }
                }
            }
        } else {
            importances = new ArrayList<>(Arrays.asList(Importance.values()));
        }
        return importances;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_minor:
            case R.id.action_filter_major:
            case R.id.action_filter_critical:
                item.setChecked(!item.isChecked());
                updateNotes();
                return false;
            case R.id.action_add_note:
                EditNoteActivity.openCreateActivity(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateNotes() {
        var filterImportances = getFilterImportances();
        var filteredNotes = Database.Provider.getDatabase().getNotes(filterImportances);

        var adapter = rcvNotes.getAdapter();
        if (adapter instanceof NotesAdapter) {
            NotesAdapter notesAdapter = (NotesAdapter) adapter;
            notesAdapter.updateNotes(filteredNotes);

            String query = "";
            if (menu != null) {
                SearchView searchView = (SearchView) menu.findItem(R.id.action_search_notes).getActionView();
                query = searchView.getQuery().toString();
            }
            rcvNotesAdapter.getFilter().filter(query);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateNotes();
    }

}
