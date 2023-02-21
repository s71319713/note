package com.example.note.callback;

import com.example.note.Model.Note;

public interface NoteCallback {
    void openEditView(Note note);
    void getAll();
}
