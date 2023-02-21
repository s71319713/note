package com.example.note.callback;

import com.example.note.Model.Note;

public interface EditFragmentCallback {
    void deleteNote(Note note);
    void refresh();
    void updateNote(Note note);
}
