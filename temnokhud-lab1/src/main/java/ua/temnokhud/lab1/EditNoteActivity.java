package ua.temnokhud.lab1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;

import java.util.Arrays;

import ua.temnokhud.basecomponets.BaseActivity;
import ua.temnokhud.lab1.data.Database;
import ua.temnokhud.lab1.model.Importance;
import ua.temnokhud.lab1.model.Note;

public class EditNoteActivity extends BaseActivity {

    private static final String KEY_NOTE = "KEY_NOTE";

    private TextView txvTitle;
    private AppCompatEditText edtTitle;
    private AppCompatEditText edtDescription;
    private AppCompatSpinner spnImportance;
    private AppCompatImageView imvImage;
    private AppCompatButton btnSubmit;
    private AppCompatButton btnDelete;

    public static void openCreateActivity(Activity activity) {
        Intent intent = new Intent(activity, EditNoteActivity.class);
        activity.startActivityForResult(intent, 0);
    }

    public static void openEditActivity(Activity activity, Note note) {
        Intent intent = new Intent(activity, EditNoteActivity.class);
        intent.putExtra(KEY_NOTE, note);
        activity.startActivityForResult(intent, 0);
    }

    private Note note;

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.activity_edit_note;
    }

    @Override
    protected int getActionBarTitleStringRes() {
        return R.string.app_name;
    }

    @Override
    protected void onRestoreState(@NonNull Bundle savedInstanceState) {
        note = savedInstanceState.getParcelable(KEY_NOTE);
        if (note == null) {
            note = getIntent().getParcelableExtra(KEY_NOTE);
        }
    }

    @Override
    protected void onSaveState(@NonNull Bundle outState) {
        outState.putParcelable(KEY_NOTE, note);
    }

    @Override
    protected void onCreateViews(Bundle savedInstanceState) {
        note = getIntent().getParcelableExtra(KEY_NOTE);
        if (note == null) {
            note = new Note();
        }

        txvTitle = findViewById(R.id.activity_edit_note_txv_title);
        edtTitle = findViewById(R.id.activity_edit_note_edt_title);
        edtDescription = findViewById(R.id.activity_edit_note_edt_description);
        spnImportance = findViewById(R.id.activity_edit_note_spn_importance);
        imvImage = findViewById(R.id.activity_edit_note_imv_image);
        btnSubmit = findViewById(R.id.activity_edit_note_btn_create);
        btnDelete = findViewById(R.id.activity_edit_note_btn_delete);


        spnImportance.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(Importance.values())));

        if (note.getId() == 0) {
            txvTitle.setText(R.string.text_add_note);
            btnSubmit.setText(R.string.text_create);
            btnDelete.setVisibility(View.GONE);
        } else {
            edtTitle.setText(note.getTitle());
            edtDescription.setText(note.getDescription());

            Importance importance = Importance.getById(note.getId());
            spnImportance.setSelection((int) (importance.getId() - 1));

            txvTitle.setText(R.string.text_update_note);
            btnSubmit.setText(R.string.text_update);
            btnDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreateListeners() {
        btnSubmit.setOnClickListener(v -> {
            String title = edtTitle.getText() == null
                    ? ""
                    : edtTitle.getText().toString();
            note.setTitle(title);
            String description = edtDescription.getText() == null
                    ? ""
                    : edtDescription.getText().toString();
            note.setDescription(description);

            int selectedPosition = spnImportance.getSelectedItemPosition();
            Importance importance = Importance.values()[selectedPosition];
            note.setImportance(importance.getId());

            if (note.getId() == 0) {
                Database.Provider.getDatabase().createNote(note);
            } else {
                Database.Provider.getDatabase().updateNote(note);
            }

            finish();
        });
        btnDelete.setOnClickListener(v -> {
            if (note.getId() != 0) {
                Database.Provider.getDatabase().deleteNote(note.getId());
            }
            finish();
        });
    }

}

