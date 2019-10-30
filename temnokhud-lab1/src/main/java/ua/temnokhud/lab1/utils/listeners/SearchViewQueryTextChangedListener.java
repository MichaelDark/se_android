package ua.temnokhud.lab1.utils.listeners;

import androidx.appcompat.widget.SearchView;

public interface SearchViewQueryTextChangedListener extends SearchView.OnQueryTextListener {

    void onTextChanged(String query);

    default boolean onQueryTextSubmit(String query) {
        onTextChanged(query);
        return true;
    }

    default boolean onQueryTextChange(String newText) {
        onTextChanged(newText);
        return true;
    }

}
