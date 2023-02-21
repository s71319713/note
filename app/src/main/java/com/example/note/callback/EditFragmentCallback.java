package com.example.note.callback;

import com.example.note.Model.Note;

public interface EditFragmentCallback {
    void deleteNote(Note note);
    void updateNote(Note note);
}
