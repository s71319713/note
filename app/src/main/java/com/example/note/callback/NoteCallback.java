package com.example.note.callback;

import com.example.note.Note;

import java.util.List;

public interface NoteCallback {
    void openEditView(Note note);
    void getAll();
}
